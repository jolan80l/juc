package com.jolan.jmm.jun21;

/**
 * @author jolan80
 * @date 2020-06-21 9:20
 *
 *  1 volatile是Java虚拟机提供的轻量级的同步机制
 *      1.1 保证可见性
 *      1.2 不保证原子性
 *      1.3 禁止指令重排序
 *  2 JMM: JMM（Java内存模型Java Memory Model,简称JMM）本身是一种抽象的概念并不真实存在，它描述的是一组规则或规范，
 *      用过这组规范定义了程序中各个变量（包括实例字段，静态字段和构成数组对象的元素）的访问方式
 *    JMM关于同步规范的规定：
 *      1 线程解锁前，必须把共享变量的值刷新回主内存
 *      2 线程加锁前，必须读取主内存的最新值到自己的工作内存
 *      3 加锁解锁是同一把锁
 *      线程间的通信（传值）必须通过主内存来完成。
 *
 *      2.1 可见性
 *      2.2 原子性
 *      2.3 有序性
 */
public class JMMNote {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "********** come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.addTo18();
            System.out.println(Thread.currentThread().getName() + "\t update number, number value : " + myNumber.number);
        }, "AAA").start();

        while(myNumber.number == 10){
            //需要有一种通知机制通知main线程，number已经修改为18，跳出whlie
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}

class MyNumber{
//    int number = 10;
    volatile int number = 10;
    public void addTo18(){
        this.number = 18;
    }
}
