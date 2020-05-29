package com.jolan.juc.may28.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jolan80
 * @date 2020-05-28 22:31
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
//        BlockingQueue blockingQueue1 = new ArrayBlockingQueue(10);//由数组结构组成的有界阻塞对垒
//        BlockingQueue blockingQueue2 = new LinkedBlockingQueue();//有链表组成的有界（大小默认为integer.max_value）组设队列
//        BlockingQueue blockingQueue3 = new PriorityBlockingQueue();//支持优先级排序的无界阻塞队列
//        BlockingQueue blockingQueue4 = new DelayQueue();//使用优先级队列实现的延迟无界阻塞队列
//        BlockingQueue blockingQueue5 = new SynchronousQueue();//不存储元素的阻塞队列，也即单个元素的队列
//        BlockingQueue blockingQueue6 = new LinkedTransferQueue();//由链表组成的无界阻塞队列
//        BlockingQueue blockingQueue7 = new LinkedBlockingDeque();//由链表组成的双向阻塞队列

        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue1.add("a"));
        System.out.println(blockingQueue1.add("b"));
        System.out.println(blockingQueue1.add("c"));
//        System.out.println(blockingQueue1.add("d"));//java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue1.remove());//a
        System.out.println(blockingQueue1.remove());//b
        System.out.println(blockingQueue1.remove());//c
//        System.out.println(blockingQueue1.remove());//java.util.NoSuchElementException

        System.out.println("---------------------------------");

        System.out.println(blockingQueue1.add("a"));
        System.out.println(blockingQueue1.add("b"));
        System.out.println(blockingQueue1.element());//a,检查队首

        System.out.println("---------------------------------");

        System.out.println(blockingQueue1.offer("x"));
        System.out.println(blockingQueue1.offer("y"));
        System.out.println(blockingQueue1.offer("z"));
        System.out.println(blockingQueue1.offer("v"));//false

        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());
        System.out.println(blockingQueue1.poll());//null

        System.out.println("---------------------------------");

        try {
            blockingQueue1.put("u");
            blockingQueue1.put("v");
            blockingQueue1.put("w");
//            blockingQueue1.put("m");//队列已满，一直阻塞

            System.out.println(blockingQueue1.take());
            System.out.println(blockingQueue1.take());
            System.out.println(blockingQueue1.take());
//            System.out.println(blockingQueue1.take());//队列已空，一直阻塞等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------");

        try {
            System.out.println(blockingQueue1.offer("a"));
            System.out.println(blockingQueue1.offer("b"));
            System.out.println(blockingQueue1.offer("c"));
            System.out.println( blockingQueue1.offer("d", 3L, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
