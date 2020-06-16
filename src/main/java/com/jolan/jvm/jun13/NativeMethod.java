package com.jolan.jvm.jun13;

/**
 * @author jolan80
 * @date 2020-06-13 10:54
 */
public class NativeMethod {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.start();//java.lang.IllegalThreadStateException

        //t1.start()调用start0()方法，start0方法是用native修饰的，表示调用操作系统或者第三方C语言函数库。
        //navite修饰的方法放在本地方法栈，即native method stack。原因是java诞生时是C/C++横行的时候，要想立足，必须有调用C/C++程序，于是专门开辟了一块区域处理标记为native方法
    }
}
