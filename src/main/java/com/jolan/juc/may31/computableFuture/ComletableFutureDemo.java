package com.jolan.juc.may31.computableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author jolan80
 * @date 2020-05-31 16:10
 */
public class ComletableFutureDemo {
    public static void main(String[] args) throws Exception{

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "没有返回, update mysql ok");
        });
        completableFuture.get();//无返回

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "有返回, select mysql ok");
            int age = 10/0;
            return 1024;
        });

        System.out.println(completableFuture2.whenComplete((t, u) -> {
            System.out.println("******t:" + t);
            System.out.println("******u:" + u);
        }).exceptionally(f->{
            System.out.println("******exception:" + f.getMessage());
            return 4444;
        }).get());

    }
}
