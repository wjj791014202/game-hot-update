package com.xinyue.hot.agent;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

public class GameServerAgent {

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        String path = args;
        if (path == null) {
            path = "E:\\workspace\\game-hot-update\\GameServer\\config";
        }

        System.out.println("agent 启动成功,path=" + path);
        while (true) {
            try {
                File dir = new File(path);
                File[] files = dir.listFiles();
                for (File file : files) {
                    if (isClassFile(file)) {
                        String name = file.getName();
                        System.out.println("开始热更新" + name);
                        reloadClass(file.getAbsolutePath(), inst);
                        System.out.println("热更新成功" + name);
                        file.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(10000);
        }

    }

    private static void reloadClass(String classFilePath, Instrumentation inst) throws Exception {
        File file = new File(classFilePath);
        byte[] buff = new byte[(int) file.length()];//将class文件的二进制码读入
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        in.readFully(buff);
        in.close();
        MyClassLoader loader = new MyClassLoader();//定义一个类加载器
        Class<?> updateCalss = loader.findClass(buff);//找到该类
        ClassDefinition definition = new ClassDefinition(Class.forName(updateCalss.getName()), buff);
        inst.redefineClasses(new ClassDefinition[]{definition});
    }

    private static boolean isClassFile(File file) {
        return file.getName().contains(".class");
    }

    public static byte[] fileToBytes(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        return bytes;
    }
}
