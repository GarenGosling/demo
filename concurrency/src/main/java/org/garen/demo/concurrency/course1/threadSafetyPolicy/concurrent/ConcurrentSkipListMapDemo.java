package org.garen.demo.concurrency.course1.threadSafetyPolicy.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.garen.demo.concurrency.annotation.ThreadSafe;

import java.util.Map;
import java.util.concurrent.*;

/**
 * <p>
 * 功能描述 : ConcurrentSkipListMap
 * </p>
 *
 * @author : Garen Gosling 2020/4/13 下午4:52
 */
@Slf4j
@ThreadSafe
public class ConcurrentSkipListMapDemo {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = new ConcurrentSkipListMap<>();

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
        log.info("size: {}", map.size());
    }

    private static void update(int i) {
        map.put(i, i);
    }
}
