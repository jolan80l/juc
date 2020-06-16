package com.jolan.juc.may31.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author jolan80
 * @date 2020-05-31 15:43
 */
public class ForkJoinPoll {
    public static void main(String[] args) {
        ForkJoinPool threadPoll = null;
        try{
            MyTask myTask = new MyTask(0, 100);
            threadPoll = new ForkJoinPool();
            ForkJoinTask<Integer> forkJoinTask = threadPoll.submit(myTask);
            System.out.println(forkJoinTask.get());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(threadPoll != null){
                threadPoll.shutdown();
            }
        }

    }
}

class MyTask extends RecursiveTask<Integer>{

    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if((end - begin) <= ADJUST_VALUE){
            for(int i=begin;i<=end;i++){
                result = result + i;
            }
        }else{
            int middle = (end + begin) /2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle + 1, end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}
