package org.garen.demo.zookeeper.component.zookeeperCounter.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * <p>
 * 功能描述 : zk客户端工具类
 * </p>
 *
 * @author : Garen Gosling 2020/4/7 上午11:26
 */
@Slf4j
public class ClientUtil {

    /**
     * 公用客户端
     */
    private static CuratorFramework client = null;

    /**
     * 是否使用公用客户端
     */
    private static boolean USE_PUBLIC_CLIENT = false;

    /**
     * <p>
     * 功能描述 : 获取客户端
     * </p>
     *
     * @author : Garen Gosling   2020/4/7 上午11:47
     *
     * @param connectionString 连接字符串
     * @Return org.apache.curator.framework.CuratorFramework
     **/
    public static CuratorFramework getClient(String connectionString) {
        return USE_PUBLIC_CLIENT ? usePublicClient(connectionString) : usePrivateClient(connectionString);
    }

    /**
     * <p>
     * 功能描述 : 获取公用客户端
     * </p>
     *
     * @author : Garen Gosling   2020/4/7 上午11:49
     *
     * @param connectionString 连接字符串
     * @Return org.apache.curator.framework.CuratorFramework
     **/
    private static CuratorFramework usePublicClient(String connectionString) {
        if(client == null){
            CuratorFramework client = CuratorFrameworkFactory
                    .builder()
                    .connectString(connectionString)
                    .sessionTimeoutMs(24 * 60 * 60 * 1000)
                    .connectionTimeoutMs(3000)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .build();
            log.debug("create client, name space : {}, hash code : {}", client.getNamespace(), client.hashCode());
        }
        CuratorFrameworkState state = client.getState();
        if(state == null){
            client.start();
        }
        return client;
    }

    /**
     * <p>
     * 功能描述 : 获取私用客户端
     * </p>
     *
     * @author : Garen Gosling   2020/4/7 上午11:49
     *
     * @param connectionString 连接字符串
     * @Return org.apache.curator.framework.CuratorFramework
     **/
    private static CuratorFramework usePrivateClient(String connectionString) {
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(connectionString)
                .sessionTimeoutMs(5 * 60 * 1000)
                .connectionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        log.debug("create client, name space : {}, hash code : {}", client.getNamespace(), client.hashCode());
        client.start();
        return client;
    }

    /**
     * <p>
     * 功能描述 : 关闭客户端
     *          私用客户端需要关闭，公用客户端不需要关闭
     * </p>
     *
     * @author : Garen Gosling   2020/4/7 上午11:23
     *
     * @param client 客户端
     * @Return void
     **/
    public static void closeClient(CuratorFramework client) {
        if(!USE_PUBLIC_CLIENT) CloseableUtils.closeQuietly(client);
    }
}
