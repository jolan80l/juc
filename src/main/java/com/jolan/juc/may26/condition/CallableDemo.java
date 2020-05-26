package com.jolan.juc.may26.condition;

import java.util.concurrent.Callable;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/26 0026 9:15
 *
 *
 */
public class CallableDemo {
    public static void main(String[] args) {

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
        return 1024;
    }
}
