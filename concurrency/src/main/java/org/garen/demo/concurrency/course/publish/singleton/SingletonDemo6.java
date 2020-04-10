package org.garen.demo.concurrency.course.publish.singleton;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.garen.demo.concurrency.annotation.ThreadSafe;

/**
 * <p>
 * 功能描述 : 饿汉模式 - 静态块
 *          单例的实例在类装载时创建
 *          注意顺序，静态代码块在声明之后
 *
 * 如果单例类的构造方法中没有包含过多的操作处理，饿汉模式是可以接收的
 *
 * 不足：
 * 1、如果构造方法中存在过多的处理，会导致这个类加载的特别慢，可能会引起性能问题。
 * 2、如果用饿汉模式只进行类的加载而没有实际的调用的话，会造成资源的浪费
 *
 * 使用：
 * 1、私有构造函数没有过多的操作处理
 * 2、类肯定会被调用
 *
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午5:29
 */
@Slf4j
@ThreadSafe
public class SingletonDemo6 {

    // 私有构造函数
    private SingletonDemo6() {}

    // 单例对象
    private static SingletonDemo6 instance = null;

    static {
        instance = new SingletonDemo6();
    }

    // 静态工厂方法
    public static SingletonDemo6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        log.info("{}", getInstance().hashCode());
        log.info("{}", getInstance().hashCode());
    }

}
