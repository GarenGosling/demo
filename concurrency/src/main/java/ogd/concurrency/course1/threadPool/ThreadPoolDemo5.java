package ogd.concurrency.course1.threadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * <p>
 * 功能描述 : Executors.newCachedThreadPool()
 * </p>
 *
 * @author : Garen Gosling 2020/4/15 下午3:06
 */
@Slf4j
public class ThreadPoolDemo5 {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(10, 100,
                3600L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        for(int i = 0; i < 10; i++){
            final int index = i;
            executorService.execute(() -> log.info("task:{}", index));
        }

        executorService.shutdown();
    }

}
