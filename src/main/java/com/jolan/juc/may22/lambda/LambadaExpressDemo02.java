package com.jolan.juc.may22.lambda;

/**
 * @author FC Bayern Munich
 * @date 2020-05-2020/5/22 0022 9:11
 *
 * 1 函函数编程
 *      int age = 23;
 *      y = kx + 1;
 *      f(x) = kx + 1;
 *
 *  拷贝小括号；写死右箭头；落地大括号；
 */
public class LambadaExpressDemo02 {
    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("hello world");
//            }
//        };

        Foo foo = () -> {System.out.println("hello Lambda Express");};
        foo.sayHello();
    }
}

interface  Foo{
    public void sayHello();
}
