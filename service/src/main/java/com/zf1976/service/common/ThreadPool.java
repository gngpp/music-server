package com.zf1976.service.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author mac
 * Create by Ant on 2020/6/12 8:41 下午
 */
@Slf4j
@Component
public class ThreadPool {

    /**
     * 实际线程池
     */
    private ThreadPoolExecutor pool;

    /**
     *  项目启动时创建线程池
     */
    @PostConstruct
    public void initPool(){
        /**
         * 设置线程池：
         *      线程池核心线程数
         *      线程池最大线程数
         *      空闲线程存活时间（过期回收）
         *      LinkedBlockingQueue: 阻塞队列
         *      拒绝处理策略：
         *          ThreadPoolExecutor.AbortPolicy()：被拒绝后抛出RejectedExecutionException异常
         *          ThreadPoolExecutor.CallerRunsPolicy()：被拒绝后给调用线程池的线程处理
         *          ThreadPoolExecutor.DiscardOldestPolicy()：被拒绝后放弃队列中最旧的未处理的任务
         *          ThreadPoolExecutor.DiscardPolicy()：被拒绝后放弃被拒绝的任务(当前新添加的任务)
         *
         */
        int poolCoreSize = 1;
        int poolMaxSize = Runtime.getRuntime().availableProcessors();
        pool = new ThreadPoolExecutor(
                poolCoreSize, poolMaxSize, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.DiscardOldestPolicy());
        log.info("The project starts and the thread pool initialization is complete！");
    }

    /**
     *  提交线程任务
     * @param runnable 线程
     */
    public void submit(Runnable runnable){
        pool.submit(runnable);
    }
}
