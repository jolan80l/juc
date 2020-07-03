package com.jolan.designPattern.observer;

/**
 * @author FC Bayern Munich
 * @date 2020-07-2020/7/3 0003 9:14
 */
public abstract class Observer {

    protected String name;
    protected Subject subject;

    public Observer(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public abstract void update();

}
