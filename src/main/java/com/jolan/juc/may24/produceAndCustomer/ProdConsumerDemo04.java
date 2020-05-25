package com.jolan.juc.may24.produceAndCustomer;

/**
 * @author jolan80
 * @date 2020-05-24 15:22
 *
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 *  实现一个线程对该变量加1，一个线程对该变量减1，
 *  实现交替，来10轮，变量初始值为零。
 *
 *  1. 高内聚，低耦合前提下，线程操作资源类
 *  2. 判断 + 干活 + 通知
 *  3. 防止线程的虚假唤醒，只要有wait需要while判断
 *
 *  知识小总结 = 多线程编程 + while判断 + 新版写法
 */
public class ProdConsumerDemo04{
    public static void main(String[] args) throws Exception{
        Aircondition aircondition = new Aircondition();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}


class Aircondition{
    private int number = 0;
    public synchronized void increment() throws Exception{
        //判断
        while(number != 0){
            this.wait();
        }
        //干活
        number ++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();

    }

    public synchronized void decrement() throws Exception{
        //判断
        while(number == 0){
            this.wait();
        }
        //干活
        number --;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();
    }
}
