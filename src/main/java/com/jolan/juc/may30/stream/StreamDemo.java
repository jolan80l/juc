package com.jolan.juc.may30.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author jolan80
 * @date 2020-05-30 10:32
 *
 * 题目：请按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足
 *      偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
 *      只输入一个用户名字
 */
public class StreamDemo {
    public static void main(String[] args) {

        /**
         * 流（Stream） :是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
         *  集合讲的是数据，流讲的是计算！
         *
         * Stream自身不会存储元素
         * Stream不会改变源对象。相反，他们会返回一个持有结果的新Stream
         * Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
         *
         * 操作过程：
         *      创建一个Stream:一个数据源（数据、集合）
         *      中间操作：一个中间操作，处理数据源数据
         *      终止操作：一个终止操作，执行中间操作链，产生结果
         *      源头 => 中间流水线 => 结果
         */

        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream()
                .filter(t -> {return t.getId() %2 ==0;})//按照id过滤
                .filter(t -> {return t.getAge() > 24;})//按照年龄过滤
                .map(t -> {return t.getUserName().toUpperCase();})//获取大写用户名，此时返回字符串数组
                .sorted()//正序排列
                .sorted((o1, o2) -> {return o2.compareTo(o1);})//倒序排列+
                .limit(1)//留一个
                .forEach(System.out::println);

        /**
         *
         * 四大函数式接口：
         *      Consumer<T> 参数类型T，返回类型void，void accept(T t)
         *      Supplier<T> 无参数，返回类型T，T get()
         *      Function<T, R> 参数类型T，返回类型R，R apply(T t);
         *      Predicate<T> 参数类型T，返回类型boolean，boolean test(T t)
         */
//        Function<String, Integer> function = new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return 1024;
//            }
//        };

        Function<String, Integer> function =  s -> {return s.length();};
        System.out.println(function.apply("abc"));

//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return false;
//            }
//        };
        Predicate<String> predicate = s -> {return s.isEmpty();};
        System.out.println(predicate.test("jolan"));

//        Consumer<String> customer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//
//            }
//        };
        Consumer<String> customer = s -> {
            System.out.println(s);
        };
        customer.accept("ymm");

//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return null;
//            }
//        };
        Supplier<String> supplier = () -> {return "supplier";};
        System.out.println(supplier.get());
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User{
    private Integer id;
    private String userName;
    private int age;

}
