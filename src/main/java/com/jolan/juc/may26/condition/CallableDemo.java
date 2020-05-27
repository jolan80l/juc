package com.jolan.juc.may26.condition;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/26 0026 9:15
 *
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Thread t1 = new Thread(new MyThread(), "A");//原始方式

        //未加泛型
//        FutureTask futureTask = new FutureTask(new MyThread2());
//        new Thread(futureTask, "A").start();
//        Integer result = (Integer)futureTask.get();
//        System.out.println(result);

        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThread2());//加泛型
        new Thread(futureTask2, "B").start();

        System.out.println("*******主线程结束");

        Integer result2 = futureTask2.get();
        System.out.println(result2);
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("**********come in call");
        try{
            TimeUnit.SECONDS.sleep(4);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return 1024;
    }
}
