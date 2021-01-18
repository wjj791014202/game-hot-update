package com.xinyue.hot.agent;

/**
 * @author wangjijiang
 * @date 2021/1/18 11:14
 */
public class MyClassLoader extends ClassLoader {
    public MyClassLoader() {
        super(Thread.currentThread().getContextClassLoader());
    }

    public Class<?> findClass(byte[] b) throws ClassNotFoundException {
        return this.defineClass((String) null, b, 0, b.length);//利用父类ClassLoader中defineClass方法找到（重定义）此类
    }
}
