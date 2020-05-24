package com.jolan.juc.may24.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author jolan80
 * @date 2020-05-24 10:22
 *
 * 1. 标准访问（邮件没有加sleep），请问是先打印邮件还是短信 -> 不一定
 * 2. 暂停4s中在邮件方法，请问先打印邮件还是短信 - >先email
 * 3. 新增普通sayHello方法（替换线程B中的sendSMS方法），请问先打印邮件还是hello -> 先hello
 * 4. 两部手机，先打印邮件还是短信 -> 先打印第二手机的短信
 * 5. 两个静态同步方法， 同一部手机，先打印邮件还是短信 -> 先email
 * 6. 两个静态同步方法， 两部手机，先打印邮件还是短信 -> 先email
 * 7. 一个静态同步方法，一个普通同步方法，先打印邮件还是短信 -> 先短信
 * 8. 一个静态同步方法，一个普通方法，两步手机，先打印邮件还是短信 -> 先短信
 */
public class Lock8Demo05 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try{
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "A").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try{
//                phone.sendSMS();//测试邮件和短信的先后顺序
//                phone.sayHello();//测试邮件和sayHello的先后顺序
//                phone2.sendSMS();//测试第一步手机的邮件和第二部手机的短信的先后顺序
//                phone.sendSMS();//测试静态方法邮件和短信的先后顺序
//                phone2.sendSMS();//测试静态方法第一步手机的邮件和第二部手机的短信的先后顺序
//                phone.sendSMS();//测试邮件同步静态方法，短信普通同步方法，邮件和短信的打印顺序
                phone2.sendSMS();//测试邮件同步静态方法，短信普通同步方法，邮件和短信的打印顺序
            }catch (Exception e){
                e.printStackTrace();
            }
        }, "A").start();
    }
}

class Phone{
//    public synchronized void sendEmail() throws Exception{
//        TimeUnit.MILLISECONDS.sleep(4000);
//        System.out.println("******send email");
//    }
//
    public synchronized void sendSMS() throws Exception{
        System.out.println("******send message");
    }

    public static synchronized void sendEmail() throws Exception{
        TimeUnit.MILLISECONDS.sleep(4000);
        System.out.println("******send email");
    }

//    public static synchronized void sendSMS() throws Exception{
//        System.out.println("******send message");
//    }

    public void sayHello() throws Exception{
        System.out.println("******hello");
    }
}

/**
 *
 * 1. 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程调用其中一个synchronized方法了，其他的线程都只能等待，换句话说，某一个时刻内，
 *      只能有唯一一个线程去访问这些synchronized方法
 *    锁的是当前对象this（对象锁），被锁定后，其他的线程都不能进入到当前对象的其他synchronized方法
 *
 * 2. 加普通方法和同步锁无关
 *
 * 3. 同一个类创建的两个对象，也就是两个资源类，互相不会受synchronized的影响
 *
 * 4. static synchronized属于全局锁
 *
 *
 *
 *
 */

