package ogd.zookeeper.component.other;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午6:29
 */
public class ClientSingle {

    public CuratorFramework client;

    // 私有构造方法
    private ClientSingle () {
        client = CuratorFrameworkFactory
                .builder()
                .connectString("193.112.74.238:2181")
                .sessionTimeoutMs(10 * 1000)
                .connectionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();
    }

    // 静态工厂方法
    public static ClientSingle getInstance() {
        return Single.INSTANCE.getInstance();
    }

    private enum Single {
        INSTANCE;

        private ClientSingle single;

        Single() {
            single = new ClientSingle();
        }

        public ClientSingle getInstance() {
            return single;
        }
    }

}
