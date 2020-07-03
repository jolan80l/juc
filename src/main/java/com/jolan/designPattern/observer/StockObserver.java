package com.jolan.designPattern.observer;

/**
 * @author FC Bayern Munich
 * @date 2020-07-2020/7/3 0003 9:17
 */
public class StockObserver extends Observer {

    public StockObserver(String name, Subject subject) {
        super(name, subject);
    }

    @Override
    public void update() {
        System.out.println(subject.getAction() + "\n" + name + "关闭股票行情，继续工作");
    }

}
