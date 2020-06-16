package com.jolan.juc.may29.threadPoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jolan80
 * @date 2020-05-29 22:56
 */
public class MyThreadPollDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);//1池5受理线程
        try{
            //模拟有十个顾客来银行办理业务，目前池中有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            executorService.shutdown();
        }

        System.out.println("------------------------------------");

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();//1池1受理线程
        try{
            //模拟有十个顾客来银行办理业务，目前池中有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                executorService2.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            executorService2.shutdown();
        }

        System.out.println("------------------------------------");

        ExecutorService executorService3 = Executors.newCachedThreadPool();//1池n线程，可扩容的
        try{
            //模拟有十个顾客来银行办理业务，目前池中有5个工作人员提供服务
            for (int i = 1; i <= 10; i++) {
                executorService3.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            executorService3.shutdown();
        }
    }
}
