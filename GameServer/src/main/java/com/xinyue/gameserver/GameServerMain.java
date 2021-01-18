package com.xinyue.gameserver;

import java.lang.management.ManagementFactory;

public class GameServerMain {

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        //这里为了方便测试，打印出来进程id
        String pid = name.split("@")[0];
        System.out.println("进程Id：" + pid);
        while (true) {
            test();
            try {
                //这里等待15s，给启动热更新一点时间
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void test() {
        TestHot testHot = new TestHot();
        testHot.print();
    }
}
