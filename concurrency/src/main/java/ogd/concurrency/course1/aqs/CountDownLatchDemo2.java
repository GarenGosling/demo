package ogd.concurrency.course1.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 功能描述 : CountDownLatch
 *          指定执行时间后，不管是不是所有线程都执行完，执行后续代码
 * </p>
 *
 * @author : Garen Gosling 2020/4/14 下午2:24
 */
@Slf4j
public class CountDownLatchDemo2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10, TimeUnit.MILLISECONDS);    // 10ms后结束
        log.info("finish");
        exec.shutdown();
    }

    private static void test(int threadNumb) throws Exception {
        Thread.sleep(100);
        log.info("{}", threadNumb);
    }
}
