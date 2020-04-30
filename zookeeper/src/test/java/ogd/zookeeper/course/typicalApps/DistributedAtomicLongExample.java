package ogd.zookeeper.course.typicalApps;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import ogd.zookeeper.course.Config;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DistributedAtomicLongExample {

    private static final int CLIENT_COUNT = 5;
    private static CuratorFramework client;

    static {
        client = CuratorFrameworkFactory.newClient(Config.CONNECTION_STRING,3000,3000, new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE));
        client.getCuratorListenable().addListener((curatorFramework, curatorEvent) -> System.out.println("curator event : " + curatorEvent.getType().name()));
        client.start();
    }

    private static CuratorFramework getClient() {
        if(client == null || !"STARTED".equals(client.getState().name())){
            client = CuratorFrameworkFactory.newClient(Config.CONNECTION_STRING,3000,3000, new ExponentialBackoffRetry(1000, 3,Integer.MAX_VALUE));
            client.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    private static void counter() {
        try{
            ExecutorService service = Executors.newFixedThreadPool(CLIENT_COUNT);
            for (int i = 0; i < CLIENT_COUNT; ++i) {
                final DistributedAtomicLong count = new DistributedAtomicLong(getClient(), Config.getNodePath("counter"), new RetryNTimes(10, 10));

                service.submit((Callable<Void>) () -> {
                    try {
                        AtomicValue<Long> value = count.increment();
                        System.out.println("操作是否成功: " + value.succeeded());
                        if (value.succeeded()){
                            System.out.println("操作成功: 操作前的值为： " + value.preValue() + " 操作后的值为： " + value.postValue());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testMethod1() {
        for (int i = 0; i < 10; i++) {
            counter();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
