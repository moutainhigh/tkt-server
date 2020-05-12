package com.mtl.cypw.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author tang.
 * @date 2020/3/6.
 */
@Slf4j
public class ThreadPoolTaskUtil {

    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 30;
    /**
     * 核心线程外，线程存活时间(秒)
     */
    private static final int KEEP_ALIVE_TIME = 30;
    /**
     * 缓冲队列容量
     */
    private static final int QUEUE_CAPACITY = 9999;
    /**
     * 线程名称前缀，方便查找日志
     */
    private static final String THREAD_NAME_PREFIX = "TKTSTAR_MEMBER_SEARCH_LOG";

    /**
     * 任务拒绝策略
     * 1、ThreadPoolExecutor.AbortPolicy：丢弃任务并抛出RejectedExecutionException异常。
     * 2、ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * 3、ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面（最旧）的任务，然后重新尝试执行任务（重复此过程）。
     * 4、ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务 。
     * 5、自定义策略
     */
    private static final RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();

    /**
     * spring 线程池
     */
    private static ThreadPoolTaskExecutor executor;

    static {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.setRejectedExecutionHandler(handler);
        executor.initialize();
    }

    public static void execute(Runnable runnable) {
        if (executor == null) {
            log.error("executorService is null");
            return;
        }
        executor.execute(runnable);
    }
}
