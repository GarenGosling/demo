package ogd.zookeeper.component.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/4/11 上午8:27
 */
@Slf4j
public class ClientSingleTest {

    public static void main(String[] args) {
        int i = 0;
        while (true) {
//            CuratorFramework client = ClientSingle.getInstance().client;
            CuratorFramework client = ClientUtil.getClientSingle("127.0.0.1:2181");
            if(client == null){
                log.info("client is null");
            }
            boolean connected = client.getZookeeperClient().isConnected();
            String tag = null;
            try {
                byte[] bytes = client.getData().forPath("/tag");
                tag = new String(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("=== is connected : {} - {} - {} - {} - {}", connected, client.hashCode(), ++i, tag, client.getState().name());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 10){
                CloseableUtils.closeQuietly(client);
            }
        }

    }

}
