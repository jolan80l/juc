package com.jolan.juc.may26.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/26 0026 8:42
 *
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下。
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来十轮
 */
public class ConditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.print5();
            }
        }, "A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.print10();
            }
        }, "B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.print15();
            }
        }, "C").start();
    }
}

class ShareData{
    private int number = 1;//A：1, B:2, C:3

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //1判断
            while(number != 1){
                c1.await();
            }
            //2干活
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 2;//修改标志位
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //1判断
            while(number != 2){
                c2.await();
            }
            //2干活
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 3;//修改标志位
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //1判断
            while(number != 3){
                c3.await();
            }
            //2干活
            for(int i=0;i<15;i++){
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3通知
            number = 1;//修改标志位
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}
