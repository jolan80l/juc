package com.jolan.jvm.jun07;

/**
 * @author jolan80
 * @date 2020-06-07 9:26
 */
public class MyObject {
    public static void main(String[] args) {
        /*java核心的类在jre/lib的根目录下，jar包名称为rt.jar，意思是runtime evenvirment*/
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());//jdk自带的类是使用Bootstrap根 加载器加载，输出为null。

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());

        /*java扩展包是java后面增加的类，放在jre安装目录的lib/ext下，凡是这个类下的jar包，类的包路径都是javax开头的，表示java Extension的意思*/

    }
}
