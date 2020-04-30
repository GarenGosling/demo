package ogd.concurrency.course1.publish.singleton;

import ogd.concurrency.annotation.NotRecommend;
import ogd.concurrency.annotation.ThreadSafe;

/**
 * <p>
 * 功能描述 : 懒汉模式
 *          单例的实例在第一次使用时创建
 *          线程安全：synchronized，保证不会被同时访问getInstance()
 *
 * 单线程模式下使用没问题，但是多线程环境下可能会出现问题，如果两个线程同时，会被实例化2次，线程不安全
 * 保证线程安全：静态工厂方法加上synchronized
 * 不推荐，有性能问题
 *
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午5:29
 */
@ThreadSafe
@NotRecommend
public class SingletonDemo3 {

    // 私有构造函数
    private SingletonDemo3() {}

    // 单例对象
    private static SingletonDemo3 instance = null;

    // 静态工厂方法
    public static synchronized SingletonDemo3 getInstance() {
        if(instance == null) {
            instance = new SingletonDemo3();
        }
        return instance;
    }


}
