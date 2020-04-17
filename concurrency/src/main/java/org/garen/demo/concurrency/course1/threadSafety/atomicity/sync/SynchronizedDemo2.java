package org.garen.demo.concurrency.course1.threadSafety.atomicity.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 功能描述 : synchronized修饰【类】和【静态方法】
 * </p>
 *
 * @author : Garen Gosling 2020/4/9 上午9:16
 */
@Slf4j
public class SynchronizedDemo2 {

    /**
     * <p>
     * 功能描述 : 修饰一个代码块
     * </p>
     *
     * @author : Garen Gosling   2020/4/9 上午9:18
     *
     * @param
     * @Return void
     **/
    public static void sync1(int j) {
        synchronized (SynchronizedDemo2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {} - {}", j, i);
            }
        }
    }

    /**
     * <p>
     * 功能描述 : 修饰一个方法
     * </p>
     *
     * @author : Garen Gosling   2020/4/9 上午9:19
     *
     * @param
     * @Return void
     **/
    public static synchronized void sync2(int j) {
        for (int i=0; i < 10; i++) {
            log.info("test2 - {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo2 example1 = new SynchronizedDemo2();
        SynchronizedDemo2 example2 = new SynchronizedDemo2();

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 修饰类
//        executorService.execute(() -> example1.sync1(1));
//        executorService.execute(() -> example1.sync1(2));

        // 作用于所有对象
//        executorService.execute(() -> example1.sync1(1));
//        executorService.execute(() -> example2.sync1(2));

        // 修饰静态方法
//        executorService.execute(() -> example1.sync2(1));
//        executorService.execute(() -> example1.sync2(2));

        // 作用于所有对象
        executorService.execute(() -> example1.sync2(1));
        executorService.execute(() -> example2.sync2(2));
    }

}
