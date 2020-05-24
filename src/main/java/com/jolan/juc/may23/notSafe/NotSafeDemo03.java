package com.jolan.juc.may23.notSafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author jolan80
 * @date 2020-05-23 15:53
 *
 * 写一个ArrayList线程不安全的示例
 * 1.故障现象
 *      java.util.ConcurrentModificationException
 * 2、导致原因
 *
 * 3.解决方法
 *      3.1 Vector
 *      3.2 Collections.synchronizedList
 *      3.3 CopyOnWriteArrayList
 * 4.优化建议
 */
public class NotSafeDemo03 {
    private static List<String> list = new ArrayList<String>();
    public static void main(String[] args) {
//        listNotSafe();
//        setNotSafe();
        mapNotSafe();
    }

    private static void mapNotSafe() {
//        Map<String, String> map = new HashMap<>();
//        for(int i=1; i<= 30 ; i++){
//            new Thread(() -> {
//                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(map);
//            }, String.valueOf(i)).start();
//        }
        Map<String, String> map2 = new ConcurrentHashMap<>();
        for(int i=1; i<= 30 ; i++){
            new Thread(() -> {
                map2.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map2);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
//        Set<String> set = new HashSet<String>();
//        for(int i=1; i<= 30 ; i++){
//            new Thread(() -> {
//                set.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(set);
//            }, String.valueOf(i)).start();
//        }
        Set<String> set2 = new CopyOnWriteArraySet<>();
        for(int i=1; i<= 30 ; i++){
            new Thread(() -> {
                set2.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set2);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //        Thread t1 = new Thread(() -> {for(int i=0;i<10000;i++) list.add("a");}, "");
//        Thread t2 = new Thread(() -> {for(int i=0;i<10000;i++) list.add("b");}, "");
//        t1.start();
//        t2.start();
//        System.out.println("11111111111111111");
//        while(t1.isAlive() && t2.isAlive()){
//
//        }
//        System.out.println(t1.getState());
//        System.out.println(t2.getState());
//        System.out.println(list.size());
//        System.out.println("22222222222222222");

//        List<String> list2 = new ArrayList<String>();
//        for (int i=1;i<=3;i++){
//            new Thread(()->{
//                list2.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(list2);
//            },String.valueOf(i)).start();
//        }

//        List<String>  list3 = new Vector<String>();
//        for (int i=1;i<=3;i++){
//            new Thread(()->{
//                list3.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(list3);
//            },String.valueOf(i)).start();
//        }

//        List<String>  list4 = Collections.synchronizedList(new ArrayList<>());
////        for (int i=1;i<=3;i++){
////            new Thread(()->{
////                list4.add(UUID.randomUUID().toString().substring(0, 8));
////                System.out.println(list4);
////            },String.valueOf(i)).start();
////        }

        List<String> list5 = new CopyOnWriteArrayList<>();
        for (int i=1;i<=30;i++){
            new Thread(()->{
                list5.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list5);
            },String.valueOf(i)).start();
        }
    }
}
