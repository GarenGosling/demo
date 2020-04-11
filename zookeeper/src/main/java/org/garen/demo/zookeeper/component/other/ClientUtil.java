package org.garen.demo.zookeeper.component.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * <p>
 * 功能描述 : zk连接工具类
 *
 * 懒汉模式 - 双重同步锁单例模式
 * 线程安全，原因：volatile禁止指令重排
 * </p>
 *
 * @author : Garen Gosling 2020/4/11 上午9:30
 */
@Slf4j
public class ClientUtil {

    // 私有构造函数
    private ClientUtil() {}

    // 单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private volatile static CuratorFramework client = null;

    // 静态工厂方法
    public static CuratorFramework getClientSingle(String connectionString) {
        if(client == null
                || client.getState() == null
                || !"STARTED".equals(client.getState().name())
                || !client.getZookeeperClient().isConnected()){     // 双重检测机制 第一重
            synchronized (ClientUtil.class) {
                if(client == null
                        || client.getState() == null
                        || !"STARTED".equals(client.getState().name())
                        || !client.getZookeeperClient().isConnected()) {   // 双重检测机制 第二重
                    CloseableUtils.closeQuietly(client);    // 关闭连接，源码有对client判空，所以放心传null
                    client = getClient(connectionString);
                    client.start();
                    // 循环检测是否已连接，如果20s后还是没有连接，则抛出异常
                    for(int i=1;i<=200;i++){
                        if(client.getZookeeperClient().isConnected()) {    // 已连接
                            log.info("== zk connection success ==");
                            break;
                        }
                        try {
                            Thread.sleep(100);
                            log.info("wait connect {} ms", 100 * i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(!client.getZookeeperClient().isConnected()) {    // 未连接
                        throw new RuntimeException("== zk connection fails ==");
                    }
                }
            }
        }
        return client;
    }

    /**
     * <p>
     * 功能描述 : 获取连接zk的Curator客户端
     * </p>
     *
     * @author : Garen Gosling   2020/4/11 上午10:09
     *
     * @param connectionString 连接参数
     * @Return org.apache.curator.framework.CuratorFramework
     **/
    private static CuratorFramework getClient(String connectionString){
        return CuratorFrameworkFactory
                .builder()
                .connectString(connectionString)
                .sessionTimeoutMs(10 * 1000)
                .connectionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

}
