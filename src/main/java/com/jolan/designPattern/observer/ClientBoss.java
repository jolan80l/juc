package com.jolan.designPattern.observer;

/**
 * @author FC Bayern Munich
 * @date 2020-07-2020/7/3 0003 9:19
 */
public class ClientBoss {

    public static void main(String[] args) {
        //老板为通知者
        Boss boss = new Boss();

        StockObserver observer = new StockObserver("adam", boss);
        NBAObserver observer2 = new NBAObserver("tom", boss);

        //老板通知
        boss.attach(observer);
        boss.attach(observer2);

        //tom没被老板通知到，所以不用挨骂
        boss.detach(observer2);

        //老板回来了
        boss.setAction("咳咳，我大Boss回来了！");
        //发通知
        boss.notifyObservers();
    }

}