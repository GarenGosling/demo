package ogd.concurrency.course1.threadSafetyPolicy.concurrent;

import lombok.extern.slf4j.Slf4j;
import ogd.concurrency.annotation.ThreadSafe;

import java.util.Set;
import java.util.concurrent.*;

/**
 * <p>
 * 功能描述 : CopyOnWriteArraySet
 * </p>
 *
 * @author : Garen Gosling 2020/4/14 上午11:17
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new CopyOnWriteArraySet<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {}", set.size());
    }

    private static void update(int i) {
        set.add(i);
    }
}
