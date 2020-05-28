package com.jolan.juc.may28.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/28 0028 8:51
 *
 *  循环栅栏。在第一个线程完成时，会在栅栏钱等待，最后一个线程完成时会完成栅栏构造方法里面的runnable接口。可以用于多线程计算数据，最后合并计算结果的场景。
 *  是可以循环使用的。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("*****召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t收集到第：" + tempInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
