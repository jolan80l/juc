package com.jolan.juc.may21.saleTicket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/21 0021 8:57
 *
 * 题目：三个售票员卖出30张票
 * 企业级的多线程代码编程套路
 * 1 在高内聚低耦合的前提下，处理线程、操作、资源类
 *  1.1 先创建一个资源类
 */
public class SaleTicketDemo1 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        /*原始写法：new Thread(Runnable target, String name);*/
//        Thread t1 = new Thread();
//        Thread t2 = new Thread();
//        Thread t3 = new Thread();
        /*匿名内部类写法*/
//        new Thread(new Runnable() {
//            public void run() {
//                for(int i=1;i<=40;i++){
//                    ticket.sale();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                for(int i=1;i<=40;i++){
//                    ticket.sale();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                for(int i=1;i<=40;i++){
//                    ticket.sale();
//                }
//            }
//        }, "C").start();

        /*lambda表达式写法*/
        new Thread(() -> {for (int i = 0; i < 40; i++)    ticket.sale(); },"A").start();
        new Thread(() -> {for (int i = 0; i < 40; i++)    ticket.sale(); },"B").start();
        new Thread(() -> {for (int i = 0; i < 40; i++)    ticket.sale(); },"C").start();


    }
}

class Ticket{//资源类 = 实例变量 + 实例方法
    private int number = 30;

    Lock lock = new ReentrantLock();


    public void sale(){

        lock.lock();

        try{
            if(number > 0){
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "，\t还剩下：" + number);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
