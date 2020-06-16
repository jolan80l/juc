package com.jolan.juc.may29.threadPoll;

import java.util.concurrent.*;

/**
 * @author jolan80
 * @date 2020-05-30 8:54
 */
public class ThreadPollExecutorDemo {
    public static void main(String[] args) {
        /**
         * int corePoolSize 线程池中常驻核心线程数
         * int maximumPoolSize 线程池中能够容纳同事执行的最大线程数，此值必须大于1
         * long keepAliveTime
         *      多余的空闲线程的存活时间。当前线程池中线程数超过corePollSize时，当空闲时间达到keepAliveTime时，多余线程会被销毁直到剩下corePoolSize个线程为止。
         *      maximumPoolSize - corePoolSize = 即空闲线程，就是说线程数达到了maximumPoolSize之后，由于并发量下降，会回收多余的空闲线程，空闲线程的存活时间是keepAliveTime
         * TimeUnit unit keepAliveTime的时间单位
         * BlockingQueue<Runnable> workQueue 任务队列，被提交但尚未被执行的任务。换言之，如果当前并发的线程数超过了maximumPoolSize，多出来的线程在任务队列中等待。
         * ThreadFactory threadFactory 表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可。
         * RejectedExecutionHandler handler
         *      拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）时如何拒绝请求执行的runnable的策略。也就是maximumPoolSize和workQueue都满了。
         *
         */

        System.out.println(Runtime.getRuntime().availableProcessors());//获取cpu核数

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(3);
        ExecutorService threadPoll = new ThreadPoolExecutor(
                2, 5, 2L, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());//直接抛出RejectedExecutionException异常阻止系统正常运行。这是默认策略。
//                new ThreadPoolExecutor.CallerRunsPolicy());//该策略不会抛弃任务，也不会抛出异常，而是将任务回退到调用者，从而降低新任务的流程
//                new ThreadPoolExecutor.DiscardPolicy());//抛弃多余的线程，不会抛出异常
                new ThreadPoolExecutor.DiscardOldestPolicy());//抛弃最老的线程，不会抛出异常
        try{
            //模拟有十个顾客来银行办理业务，目前池中有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                threadPoll.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPoll.shutdown();
        }
    }
}
