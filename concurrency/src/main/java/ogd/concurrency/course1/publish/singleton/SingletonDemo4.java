package ogd.concurrency.course1.publish.singleton;

import ogd.concurrency.annotation.NotThreadSafe;

/**
 * <p>
 * 功能描述 : 懒汉模式 - 双重同步锁单例模式
 *          单例的实例在第一次使用时创建
 *          线程不安全，原因：指令重排
 *
 * instance = new SingletonDemo4() 的执行过程：
 *
 * 1、memory = allocate() 分配对象的内存空间
 * 2、ctorInstance() 初始化对象
 * 3、instance = memory 设置instance指向刚分配的内存
 *
 * 多线程环境下，JVM 和 CPU 优化，发生了指令重排
 *
 * 1、memory = allocate() 分配对象的内存空间
 * 3、instance = memory 设置instance指向刚分配的内存
 * 2、ctorInstance() 初始化对象
 *
 * 线程 A 在执行到第3部，线程 B 执行第一重检查，发现已经有值了，于是return instance;
 * 但是线程 A 还没有执行第2步，在线程 B 返回的对象是空，一旦调用，可能会有问题
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午5:29
 */
@NotThreadSafe
public class SingletonDemo4 {

    // 私有构造函数
    private SingletonDemo4() {}

    // 单例对象
    private static SingletonDemo4 instance = null;

    // 静态工厂方法
    public static SingletonDemo4 getInstance() {
        if(instance == null) {  // 双重检测机制   // B
            synchronized (SingletonDemo4.class) {   // 同步锁
                if(instance == null){
                    instance = new SingletonDemo4();    // A - 3
                }
            }
        }
        return instance;
    }


}
