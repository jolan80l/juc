package com.jolan.juc.may28.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jolan80
 * @date 2020-05-28 21:52
 * 多个线程同时读写一个资源类没有任何问题，所以为了满足并发量，读写共享资源应该可以同时进行。
 * 但是
 * 如果一个线程想去写共享资源，就不应该再有其他线程可以对该该资源进行度或者写
 * 小总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=5 ; i++) {
            final int temint = i;
            new Thread(()->{
                myCache.put(temint + "", temint + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <=5 ; i++) {
            final int temint = i;
            new Thread(()->{
                myCache.get(temint + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache{
    private volatile Map<String, Object> map = new HashMap<String, Object>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key, Object value){
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t ------写入数据" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t ------写入完成" + key);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(String key){
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 读取数据" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成" + key);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.readLock().unlock();
        }

    }
}
