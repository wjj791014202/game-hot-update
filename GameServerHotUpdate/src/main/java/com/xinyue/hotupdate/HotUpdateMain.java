package com.xinyue.hotupdate;


import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class HotUpdateMain {

    public static void main(String[] args) {
//        //这个pid在实际应用中可以通过args参数传进来
//        String pid = args[0];
//        //VirtualMachine是jdk中tool.jar里面的东西，所以要在pom.xml引用这个jar
//        VirtualMachine vm = VirtualMachine.attach(pid);
//        // 这个路径是相对于被热更的服务的，也就是这个pid的服务，也可以使用绝对路径。
//        vm.loadAgent(args[1],args[2]);
//        System.out.println("加载成功");
//        vm.detach();
//        Thread.sleep(300000);
        //这个pid在实际应用中可以通过args参数传进来
        System.out.println(System.getProperty("java.classpath"));
        System.out.println(args[0]+":"+args[1]+":"+args[2]);
        String pid = args[0];
//        //VirtualMachine是jdk中tool.jar里面的东西，所以要在pom.xml引用这个jar
        VirtualMachine vm = null;
        try {
            vm = VirtualMachine.attach(pid);
            //        // 这个路径是相对于被热更的服务的，也就是这个pid的服务，也可以使用绝对路径。
            vm.loadAgent(args[1],args[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // vm.detach();
        // Thread.sleep(300000);
    }
}
