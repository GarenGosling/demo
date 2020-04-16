package org.garen.demo.concurrency.course1.threadSafety.atomicity.atomic.atomicBoolean;

import lombok.extern.slf4j.Slf4j;
import org.garen.demo.concurrency.annotation.NotThreadSafe;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 功能描述 : boolean
 *          多线程情况下，不能保证一段代码只能被一个线程执行
 * </p>
 *
 * @author : Garen Gosling 2020/4/8 下午4:45
 */
@Slf4j
@NotThreadSafe
public class BarWorker implements Runnable {

    private static boolean exists = false;

    private String name;

    public BarWorker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (!exists) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e1) {
                // do nothing
            }
            exists = true;
            log.info("{} enter", name);
            try {
                log.info("{} working", name);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // do nothing
            }
            log.info("{} leave", name);
            exists = false;
        } else {
            System.out.println(name + " give up");
            log.info("{} give up", name);
        }
    }


    public static void main(String[] args) {
        BarWorker bar1 = new BarWorker("bar1");
        BarWorker bar2 = new BarWorker("bar2");
        BarWorker bar3 = new BarWorker("bar3");
        new Thread(bar1).start();
        new Thread(bar2).start();
        new Thread(bar3).start();
    }
}
