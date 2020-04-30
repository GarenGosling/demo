package ogd.concurrency.course1.threadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 功能描述 : Executors.newSingleThreadExecutor()
 * </p>
 *
 * @author : Garen Gosling 2020/4/15 下午3:06
 */
@Slf4j
public class ThreadPoolDemo3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });
        }

        executorService.shutdown();
    }

}
