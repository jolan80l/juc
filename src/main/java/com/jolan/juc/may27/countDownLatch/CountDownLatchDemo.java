package com.jolan.juc.may27.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author jolan80
 * @date 2020-05-27 23:02
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
//        closeDoorWrong();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }

    private static void closeDoorWrong() {
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }
}
