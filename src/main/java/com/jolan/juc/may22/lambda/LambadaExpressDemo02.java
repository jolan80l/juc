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
 *  函数式接口不能定义超过一个方法，或者说有且仅有一个方法。只有函数式接口才能使用Lambda表达式。
 *  函数式接口可以使用@FuncationInterface注解修饰，见Foo3
 *  java8之后接口可以使用default修饰方法，见Foo3
 *  java8之后接口可以使用static修饰方法。见Foo3
 */
public class LambadaExpressDemo02 {
    public static void main(String[] args) {
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("hello world");
//            }
//        };

//        Foo foo = () -> {System.out.println("hello Lambda Express");};
//        foo.sayHello();

//        Foo2 foo = new Foo2() {
//            @Override
//            public int add(int x, int y) {
//                return 0;
//            }
//        };
        Foo2 foo2 = (int x, int y) -> {
            System.out.println("come in add method");
            return x + y;
        };
        System.out.println(foo2.add(3, 5));

        Foo3.div(3, 5);
    }
}

interface  Foo{
    public void sayHello();
}

interface  Foo2{
    public int add(int x, int y);
}

@FunctionalInterface
interface  Foo3{
    public int add(int x, int y);

    public default int mul(int x, int y){
        return x * y;
    }

    public default int mul2(int x, int y){
        return x * y;
    }

    public static int div(int x, int y){
        return x/y;
    }

    public static int div2(int x, int y){
        return x/y;
    }
}
