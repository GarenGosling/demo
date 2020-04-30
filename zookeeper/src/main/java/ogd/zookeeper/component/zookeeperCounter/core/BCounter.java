package ogd.zookeeper.component.zookeeperCounter.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import ogd.zookeeper.component.zookeeperCounter.entity.CounterInfo;
import ogd.zookeeper.component.zookeeperCounter.entity.CounterResult;
import ogd.zookeeper.response.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 功能描述 : 分布式计数器，基础
 * </p>
 *
 * @author : Garen Gosling 2020/3/31 上午11:25
 */
@Slf4j
@Component
public class BCounter {

    @Value("${zookeeper.connectionString}")
    private String CONNECTION_STRING;

    @Value("${zookeeper.CounterBaseNode}")
    private String COUNTER_BASE_NODE;

    /**
     * <p>
     * 功能描述 : zk节点赋值
     * </p>
     *
     * @author : Garen Gosling   2020/4/3 上午11:30
     *
     * @param nodeName 节点名称
     * @param value 值
     * @Return void
     **/
    public void nodeSet(String nodeName, String value) throws Exception {
        String nodePath = getNodePath(nodeName);
        CuratorFramework client = ClientUtil.getClient(CONNECTION_STRING);
        Stat existsNodeStat = client.checkExists().forPath(nodePath);
        if(existsNodeStat == null){
            client.create()
                    .creatingParentsIfNeeded()//递归创建,如果没有父节点,自动创建父节点
                    .withMode(CreateMode.PERSISTENT)//节点类型,持久节点
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)//设置ACL,和原生API相同
                    .forPath(nodePath, value.getBytes());
        }else{
            client.setData().forPath(nodePath, value.getBytes());
        }
        ClientUtil.closeClient(client);
    }

    /**
     * <p>
     * 功能描述 : zk节点取值
     * </p>
     *
     * @author : Garen Gosling   2020/4/3 上午11:30
     *
     * @param nodeName 节点名称
     * @Return java.lang.String
     **/
    public String nodeGet(String nodeName) throws Exception {
        String nodePath = getNodePath(nodeName);
        CuratorFramework client = ClientUtil.getClient(CONNECTION_STRING);
        Stat existsNodeStat = client.checkExists().forPath(nodePath);
        String value;
        if(existsNodeStat == null){
            value = null;
        }else{
            value = new String(client.getData().forPath(nodePath));
        }
        ClientUtil.closeClient(client);
        return value;
    }

    /**
     * <p>
     * 功能描述 : 创建计数器
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:25
     *
     * @param counterName 计数器名称
     * @param initialize 初始化值
     * @Return boolean
     **/
    public boolean createCounter(String counterName, String counterDesc, Long initialize) throws Exception {
        ClientCounter cc = getClientCounter(counterName);
        boolean b = cc.counter.initialize(initialize);
        if(b){
            addCounterToParentNode(cc.client, counterName, counterDesc);    // 父节点保存信息
        }
        ClientUtil.closeClient(cc.client);
        return b;
    }

    /**
     * <p>
     * 功能描述 : 删除计数器
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:25
     *
     * @param counterName 计数器名称
     * @Return void
     **/
    public boolean deleteCounter(String counterName) throws Exception {
        String nodePath = getNodePath(counterName);
        CuratorFramework client = ClientUtil.getClient(CONNECTION_STRING);
        Stat stat = client.checkExists().forPath(nodePath);
        if(stat != null){
            client.delete().forPath(nodePath);
        }
        Stat stat1 = client.checkExists().forPath(nodePath);
        boolean b = false;
        if(stat1 == null){
            b = true;
            deleteCounterFromParentNode(client, counterName);   // 父节点中删除计数器信息
        }

        ClientUtil.closeClient(client);
        return b;
    }

    /**
     * <p>
     * 功能描述 : 修改计数器（只能改描述）
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午9:24
     *
     * @param counterName 计数器名称
     * @param counterDesc 计数器描述
     * @Return void
     **/
    public boolean updateCounter(String counterName, String counterDesc) throws Exception {
        CuratorFramework client = ClientUtil.getClient(CONNECTION_STRING);
        List<CounterInfo> counterInfoList = getCounterListByParentNode(client, false);
        boolean b = false;
        if(!CollectionUtils.isEmpty(counterInfoList)){
            for(CounterInfo c : counterInfoList){
                if(c.getName().equals(counterName)){
                    c.setDesc(counterDesc);
                    b = true;
                    break;
                }
            }
            counterListToParentNodeValue(client, counterInfoList);
            ClientUtil.closeClient(client);
        }
        return b;
    }

    /**
     * <p>
     * 功能描述 : 计数器列表
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午6:26
     *
     * @param
     * @Return java.util.List<ogd.zookeeper.component.zookeeperCounter.entity.CounterInfo>
     **/
    public List<CounterInfo> getCounterList() throws Exception {
        CuratorFramework client = ClientUtil.getClient(CONNECTION_STRING);
        List<CounterInfo> counterInfoList = getCounterListByParentNode(client, true);
        ClientUtil.closeClient(client);
        return counterInfoList;
    }

    /**
     * <p>
     * 功能描述 : 初始化计数器
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:24
     *
     * @param counterName 计数器名称
     * @param initialize 初始化数字
     * @Return boolean
     **/
    public boolean initialize(String counterName, Long initialize) throws Exception {
        String nodePath = getNodePath(counterName);
        ClientCounter cc = getClientCounter(counterName);
        Stat stat = cc.client.checkExists().forPath(nodePath);
        if(stat != null){
            cc.client.delete().forPath(nodePath);
            Thread.sleep(2000);
            Stat newStat = cc.client.checkExists().forPath(nodePath);
            if(newStat != null){
                throw new BusinessException("初始化节点，删除节点失败！");
            }
        }
        boolean result = cc.counter.initialize(initialize);
        ClientUtil.closeClient(cc.client);
        return result;
    }


    /**
     * <p>
     * 功能描述 : 分布式计数器，支持的方法如下
     *              increment(): 加一
     *              trySet(): 尝试设置计数值
     *              get(): 获取当前值
     *              subtract(): 减去特定的值
     *              decrement(): 减一
     *              add(): 增加特定的值
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:22
     *
     * @param counterName 计数器名称
     * @param iCounter 函数式接口
     * @Return ogd.zookeeper.component.zookeeperCounter.entity.CounterResult
     **/
    public CounterResult execute(String counterName, ICounter iCounter) throws Exception {
        ClientCounter cc = getClientCounter(counterName);
        AtomicValue<Long> value = iCounter.count(cc.counter);
        CounterResult result = new CounterResult(value.succeeded(), value.preValue(), value.postValue());
        ClientUtil.closeClient(cc.client);
        return result;
    }

    /**
     * <p>
     * 功能描述 : 查询当前值
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:24
     *
     * @param counterName 计数器名称
     * @Return java.lang.Long
     **/
    public Long get(String counterName) throws Exception {
        CounterResult result = execute(counterName, DistributedAtomicLong::get);
        return result.getPostValue();
    }

    /**
     * <p>
     * 功能描述 : 强迫设置计数值
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:26
     *
     * @param counterName 计数器名称
     * @param newValue 新值
     * @Return void
     **/
    public void forceSet(String counterName, Long newValue) throws Exception {
        ClientCounter cc = getClientCounter(counterName);
        cc.counter.forceSet(newValue);
        ClientUtil.closeClient(cc.client);
    }



    /**
     * <p>
     * 功能描述 : 获取客户端和计数器
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:29
     *
     * @param counterName 计数器名称
     * @Return ogd.zookeeper.component.zookeeperCounter.impl.DistributedCounter.ClientCounter
     **/
    private ClientCounter getClientCounter(String counterName) {
        CuratorFramework client = ClientUtil.getClient(CONNECTION_STRING);
        DistributedAtomicLong counter = new DistributedAtomicLong(client, getNodePath(counterName), new RetryNTimes(10, 10));
        return new ClientCounter(client, counter);
    }

    /**
     * <p>
     * 功能描述 : 校验节点，必需以COUNTER_BASE_NODE开始
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午4:49
     *
     * @param counterName 计数器名称
     * @Return void
     **/
    private String getNodePath(String counterName) {
        if(StringUtils.isEmpty(COUNTER_BASE_NODE) || StringUtils.isEmpty(COUNTER_BASE_NODE.trim())){
            throw new BusinessException("COUNTER_BASE_NODE is empty");
        }
        if(COUNTER_BASE_NODE.lastIndexOf("/") != 0){
            throw new BusinessException("COUNTER_BASE_NODE do not start with root");
        }
        if(COUNTER_BASE_NODE.length() < 2){
            throw new BusinessException("COUNTER_BASE_NODE can only be one level path");
        }
        if(counterName.contains("/")){
            throw new BusinessException("计数器名称不能包含[/]");
        }
        return COUNTER_BASE_NODE + "/" + counterName;
    }

    /**
     * <p>
     * 功能描述 : 从计数器父节点读取计数器信息集合
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午6:07
     *
     * @param client 客户端
     * @Return java.util.List<ogd.zookeeper.component.zookeeperCounter.entity.CounterInfo>
     **/
    private List<CounterInfo> getCounterListByParentNode(CuratorFramework client, boolean withValue) throws Exception {
        byte[] bytes = client.getData().forPath(COUNTER_BASE_NODE);
        if(bytes.length == 0){
            return null;
        }
        String str = new String(bytes);
        String[] split = str.split(";");
        if(split.length == 0){
            return null;
        }
        List<CounterInfo> counterInfoList = new ArrayList<>();
        for(String s : split){
            String[] arr = s.split("-");
            CounterInfo counterInfo = new CounterInfo(arr[0], arr[1], -1L);
            if(withValue){
                counterInfo.setValue(get(counterInfo.getName()));
            }else{
                counterInfo.setValue(-1L);
            }
            counterInfoList.add(counterInfo);
        }
        return counterInfoList;
    }

    /**
     * <p>
     * 功能描述 : 计数器集合保存为计数器父节点的值
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午9:22
     *
     * @param client 客户端
     * @param counterInfoList 计数器信息集合
     * @Return void
     **/
    private void counterListToParentNodeValue(CuratorFramework client, List<CounterInfo> counterInfoList) throws Exception {
        if(!CollectionUtils.isEmpty(counterInfoList)){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< counterInfoList.size(); i++){
                CounterInfo counterInfo = counterInfoList.get(i);
                sb.append(counterInfo.getName());
                sb.append("-");
                sb.append(counterInfo.getDesc());
                if(i< counterInfoList.size()-1){
                    sb.append(";");
                }
            }
            client.setData().forPath(COUNTER_BASE_NODE, sb.toString().getBytes());
        }
    }

    /**
     * <p>
     * 功能描述 : 添加计数器信息到父节点
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午6:20
     *
     * @param client 客户端
     * @param counterName 计数器名称
     * @param counterDesc 计数器描述
     * @Return void
     **/
    private void addCounterToParentNode(CuratorFramework client, String counterName, String counterDesc) throws Exception {
        List<CounterInfo> counterInfoList = getCounterListByParentNode(client, false);
        if(counterInfoList == null || counterInfoList.size() == 0){
            counterInfoList = new ArrayList<>();
        }
        counterInfoList.add(new CounterInfo(counterName, counterDesc, -1L));
        counterListToParentNodeValue(client, counterInfoList);
    }

    /**
     * <p>
     * 功能描述 : 从父节点删除计数器信息
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 下午2:55
     *
     * @param client 客户端
     * @param counterName 计数器名称
     * @Return void
     **/
    private void deleteCounterFromParentNode(CuratorFramework client, String counterName) throws Exception {
        // 父节点中删除计数器信息
        List<CounterInfo> counterInfoList = getCounterListByParentNode(client, false);
        if(!CollectionUtils.isEmpty(counterInfoList)){
            for(CounterInfo c : counterInfoList){
                if(c.getName().equals(counterName)){
                    counterInfoList.remove(c);
                    break;
                }
            }
            counterListToParentNodeValue(client, counterInfoList);
        }
    }

    /**
     * <p>
     * 功能描述 : 客户端 + 计数器
     * </p>
     *
     * @author : Garen Gosling 2020/3/31 下午5:51
     */
    class ClientCounter {
        private CuratorFramework client;
        private DistributedAtomicLong counter;

        ClientCounter(CuratorFramework client, DistributedAtomicLong counter) {
            this.client = client;
            this.counter = counter;
        }
    }

}
