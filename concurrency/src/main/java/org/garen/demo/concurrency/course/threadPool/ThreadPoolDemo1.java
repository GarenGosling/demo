package org.garen.demo.concurrency.course.threadPool;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 功能描述 : Executors.newCachedThreadPool()
 * </p>
 *
 * @author : Garen Gosling 2020/4/15 下午3:06
 */
@Slf4j
public class ThreadPoolDemo1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
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