package com.jolan.dynamicProxy;

/**
 * @author jolan80
 * @date 2020-07-02 22:35
 */
/**
 * 测试类
 */
public class ProxyTest {

    public static void main(String[] args) {
        UserManager userManager = (UserManager)new CGLibProxy().createProxyObject(new UserManagerImpl());
        System.out.println("CGLibProxy：");
        userManager.addUser("tom", "root");

        System.out.println("JDKProxy：");
        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager)jdkProxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");
    }
}

