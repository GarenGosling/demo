package org.garen.demo.concurrency.course.visibility;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 功能描述 : volatile 适用场景
 *
 *          1、对变量的写入操作不依赖其当前值
 *          2、该变量没有包含在具有其他变量的不变式中
 *
 *          适合做为状态标识量
 * </p>
 *
 * @author : Garen Gosling 2020/4/8 下午3:07
 */
@Slf4j
public class VolatileDemo2 {
    private volatile boolean b = false;

    public static void main(String[] args) {
        VolatileDemo2 example2 = new VolatileDemo2();

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("thread1 is load");
            example2.b = true;
        });

        Thread thread2 = new Thread(() -> {
            do {
                log.info("thread2 is waiting ...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (!example2.b);
            log.info("thread2 is load");
        });

        thread1.start();
        thread2.start();
    }
}
