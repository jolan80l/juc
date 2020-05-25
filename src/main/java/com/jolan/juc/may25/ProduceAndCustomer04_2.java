package com.jolan.juc.may25;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/25 0025 8:53
 *
 * synchronized版本见com.jolan.juc.may24.produceAndCustomer.ProduceAndCustomer04
 *
 *
 */
public class ProduceAndCustomer04_2 {
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
        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}
class Aircondition{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try{
            //判断
            while(number != 0){
                condition.await();
            }
            //干活
            number ++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try{
            //判断
            while(number == 0){
                condition.await();
            }
            //干活
            number --;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
