package com.jolan.collection.map;

import java.util.HashMap;

/**
 * @author FC Bayern Munich
 * @date 2020-07-2020/7/8 0008 12:44
 *
 * 什么情况下要重写类的hashCode和equals方法。
 * 我们自己创建的类，默认是没有重写hashCode和equals方法，模式使用Object的hashCode方法，Object的hashCode方法比较的是对象的内存地址，如果两个对象在业务逻辑上是一致的，如果要把它存储在hashMap中，
 *  我们是希望只存储一个对象，但因为两个对象的内存地址不同，如果不重写hashCode方法HashMap中就会存入两个对象。另一种情况我们需要从HashMap中获取对象时，如果使用对象2作为key获取HashMap中对象1的value，
 *  默认会获取null，因为没有重写equals方法，比较的还是两个对象的内存地址，重写equals方法后，可以按照属性比对，如果属性匹配那么认为两个对象是一致的，可以使用key2去获取key1的value值
 */
public class ReCodeHashCode {
    public static void main(String[] args) {
        Key key1 = new Key(1, "test1");
        Key key2 = new Key(1, "test1");
        HashMap<Key, Key> hashMap = new HashMap<>();
        hashMap.put(key1, key1);
        System.out.println(hashMap.get(key1));
        System.out.println(hashMap.get(key2));//不重Key的hashCode和equals方法，这个结果是null，重写之后这个结果是Key{id=1, name='test1'}
    }
}
