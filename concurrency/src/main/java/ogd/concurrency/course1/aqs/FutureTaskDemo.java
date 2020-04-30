package ogd.concurrency.course1.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * <p>
 * 功能描述 : FutureTask
 *          可以拿到线程返回结果
 * </p>
 *
 * @author : Garen Gosling 2020/4/14 下午5:43
 */
@Slf4j
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result: {}", result);
    }

}
