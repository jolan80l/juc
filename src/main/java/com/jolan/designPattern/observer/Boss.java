package com.jolan.designPattern.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author FC Bayern Munich
 * @date 2020-07-2020/7/3 0003 9:19
 */
public class Boss implements Subject {

    //同事列表
    private List<Observer> observers = new LinkedList<>();
    private String action;

    //添加
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //删除
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    //通知
    @Override
    public void notifyObservers() {
        for(Observer observer : observers) {
            observer.update();
        }
    }

    //前台状态
    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

}
