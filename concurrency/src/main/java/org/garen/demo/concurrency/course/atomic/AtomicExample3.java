package org.garen.demo.concurrency.course.atomic;

import lombok.extern.slf4j.Slf4j;
import org.garen.demo.concurrency.annotation.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>
 * 功能描述 : LongAdder
 *
 * LongAdder在AtomicLong的基础上将单点的更新压力分散到各个节点，
 * 在低并发的时候通过对base的直接更新可以很好的保障和AtomicLong的性能基本保持一致，
 * 而在高并发的时候通过分散提高了性能。
 * 缺点是LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差。
 * </p>
 *
 * @author : Garen Gosling 2020/4/8 下午4:24
 */
@Slf4j
@ThreadSafe
public class AtomicExample3 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count.longValue());
    }

    private static void add() {count.increment();}
}
