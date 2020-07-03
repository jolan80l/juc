package com.jolan.designPattern.observer;

/**
 * @author FC Bayern Munich
 * @date 2020-07-2020/7/3 0003 9:17
 */
public class NBAObserver extends Observer {

    public NBAObserver(String name, Subject subject) {
        super(name, subject);
    }

    @Override
    public void update() {
        System.out.println(subject.getAction() + "\n" + name + "关闭NBA，继续工作");
    }

}
