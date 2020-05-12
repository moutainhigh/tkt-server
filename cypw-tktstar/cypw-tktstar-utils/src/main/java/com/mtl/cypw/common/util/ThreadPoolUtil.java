package com.mtl.cypw.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @author Johnathon.Yuan
 * @date 2019-11-22 18:01
 */
@Slf4j
public class ThreadPoolUtil {
    private static ExecutorService executorService;

    public static void execute(Runnable runnable) {
        if (executorService == null) {
            log.error("executorService is null");
            return;
        }
        executorService.execute(runnable);
    }

    static {
        executorService = new TktStarThreadPoolExecutor(1, 5, 30L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    log.info("shutdown executorService");
                    executorService.shutdown();
                } catch (Exception e) {
                    log.error("shutdown executorService exception", e);
                }
            }
        });
    }

    static class TktStarThreadPoolExecutor extends ThreadPoolExecutor {

        public TktStarThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

    }

}
