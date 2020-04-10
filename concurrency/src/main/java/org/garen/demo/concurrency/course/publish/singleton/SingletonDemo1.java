package org.garen.demo.concurrency.course.publish.singleton;

import org.garen.demo.concurrency.annotation.NotThreadSafe;

/**
 * <p>
 * 功能描述 : 懒汉模式
 *          单例的实例在第一次使用时创建
 *
 * 单线程模式下使用没问题，但是多线程环境下可能会出现问题，如果两个线程同时，会被实例化2次，线程不安全
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午5:29
 */
@NotThreadSafe
public class SingletonDemo1 {

    // 私有构造函数
    private SingletonDemo1() {}

    // 单例对象
    private static SingletonDemo1 instance = null;

    // 静态工厂方法
    public static SingletonDemo1 getInstance() {
        if(instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }


}
