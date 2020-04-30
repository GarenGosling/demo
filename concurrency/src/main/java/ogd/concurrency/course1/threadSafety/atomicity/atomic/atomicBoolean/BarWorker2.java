package ogd.concurrency.course1.threadSafety.atomicity.atomic.atomicBoolean;

import lombok.extern.slf4j.Slf4j;
import ogd.concurrency.annotation.ThreadSafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 功能描述 : AtomicBoolean
 *          compareAndSet(false, true) 只有当预期值 == 内存值，才会更新（确保一段代码，只有一个线程可以执行）
 *          set(false) 修改内存值后，可以再次执行
 * </p>
 *
 * @author : Garen Gosling 2020/4/8 下午4:45
 */
@Slf4j
@ThreadSafe
public class BarWorker2 implements Runnable {

    private static AtomicBoolean exists = new AtomicBoolean(false); // 内存值

    private String name;

    public BarWorker2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        if (exists.compareAndSet(false, true)) {    // expect预期值 == 内存值，修改成update
            log.info("{} enter", name);
            try {
                log.info("{} working", name);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // do nothing
            }
            log.info("{} leave", name);
//            exists.set(false);  // 修改内存值
        } else {
            log.info("{} give up", name);
        }
    }


    public static void main(String[] args) {
        BarWorker2 bar1 = new BarWorker2("bar1");
        BarWorker2 bar2 = new BarWorker2("bar2");
        BarWorker2 bar3 = new BarWorker2("bar3");
        new Thread(bar1).start();
        new Thread(bar2).start();
        new Thread(bar3).start();
    }
}
