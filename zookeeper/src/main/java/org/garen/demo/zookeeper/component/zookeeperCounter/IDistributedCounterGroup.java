package org.garen.demo.zookeeper.component.zookeeperCounter;

import org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterInfo;

import java.util.List;

/**
 * <p>
 * 功能描述 : 分布式计数器组，接口
 * </p>
 *
 * @author : Garen Gosling 2020/4/1 上午9:45
 */
public interface IDistributedCounterGroup {
    /**
     * <p>
     * 功能描述 : 创建计数器
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:27
     *
     * @param counterName 计数器名称
     * @param initialize 初始化值
     * @Return boolean
     **/
    boolean createCounter(String counterName, String counterDesc, Long initialize);

    /**
     * <p>
     * 功能描述 : 删除计数器
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:27
     *
     * @param counterName 计数器名称
     * @Return void
     **/
    boolean deleteCounter(String counterName);

    /**
     * <p>
     * 功能描述 : 修改计数器（只能改描述）
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:27
     *
     * @param counterName 计数器名称
     * @param counterDesc 计数器描述
     * @Return void
     **/
    boolean updateCounter(String counterName, String counterDesc);

    /**
     * <p>
     * 功能描述 : 计数器列表
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:27
     *
     * @param
     * @Return java.util.List<org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterInfo>
     **/
    List<CounterInfo> getCounterList();

    /**
     * <p>
     * 功能描述 : 初始化计数器
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:27
     *
     * @param counterName 计数器名称
     * @param initialize 初始化数字
     * @Return boolean
     **/
    boolean initialize(String counterName, Long initialize);
}
