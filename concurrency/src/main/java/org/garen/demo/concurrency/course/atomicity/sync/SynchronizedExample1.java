package org.garen.demo.concurrency.course.atomicity.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 功能描述 : synchronized修饰【代码块】和【方法】
 * </p>
 *
 * @author : Garen Gosling 2020/4/9 上午9:16
 */
@Slf4j
public class SynchronizedExample1 {

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
    public void sync1(int j) {
        synchronized (this) {
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
    public synchronized void sync2(int j) {
        for (int i=0; i < 10; i++) {
            log.info("test2 - {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 修饰一个代码块
//        executorService.execute(() -> example1.sync1(1));
//        executorService.execute(() -> example1.sync1(2));

        // 作用于当前对象，两个对象互相不影响
//        executorService.execute(() -> example1.sync1(1));
//        executorService.execute(() -> example2.sync1(2));

        // 修饰一个方法
//        executorService.execute(() -> example1.sync2(1));
//        executorService.execute(() -> example1.sync2(2));

        // 作用于当前对象，两个对象互相不影响
//        executorService.execute(() -> example1.sync2(1));
//        executorService.execute(() -> example2.sync2(2));
    }

}
