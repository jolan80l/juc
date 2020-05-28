package com.jolan.juc.may28.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/28 0028 9:03
 *  抢车位例子
 *  Semaphore信号量，会限定线程个数，如果构造方法的值为1，相当于synchronized
 *
 *  下面简单说一下信号量和线程池的区别。
 *
 * 它们之间的区别有两点：
 *
 * 1）线程池是会开启新线程，而信号量不开启新线程，信号量所谓的新线程是使用者自己开启的。
 *
 * 2）线程池等到线程结束后会自动释放许可，信号量的许可不管是获取还是释放，都需要手动操作。
 *
 * 这就决定了它俩的使用场景上的不同，线程池的使用场景，不用多说，就是管理线程，避免线程滥用。信号量的使用场景，我认为更多的是避免一些代码同时运行太多，比如有个方法，可能会在很多线程里面使用，
 * 但是我们又不想让这个方法在太多线程里面同时调用，因为可能会耗费大量的系统资源，这个时候，你就可以增加一个全局的信号量，让这个方法在执行前获取许可，执行结束后释放许可，这样就避免了太多地方同时执行这个方法。
 * 在高并发的应用场景中，我们可能经常会有限流的操作，就是让接口同时接受的请求数量有一个上限，达到上限，其他的就要等待，这里就可以用信号量来解决。还有，假如有两个方法A和B，我们不希望这两个方法同时被执行，
 * 在同一时间，只允许一个方法被执行，那么我们仍然可以用信号量来解决。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟资源类有三个空车位

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢占到了车位");
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
