package org.garen.demo.concurrency.course1.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p>
 * 功能描述 : Semaphore
 *          控制并发量，多个许可
 * </p>
 *
 * @author : Garen Gosling 2020/4/14 下午2:24
 */
@Slf4j
public class SemaphoreDemo2 {

    private static int threadCount = 20;

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for(int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(3);    // 获取多个许可
                    test(threadNum);
                    semaphore.release(3);    // 释放多个许可
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNumb) throws Exception {
        log.info("{}", threadNumb);
        Thread.sleep(1000);
    }
}
