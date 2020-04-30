package ogd.concurrency.course1.publish.singleton;

import lombok.extern.slf4j.Slf4j;
import ogd.concurrency.annotation.Recommend;
import ogd.concurrency.annotation.ThreadSafe;

/**
 * <p>
 * 功能描述 : 枚举模式 - 最安全
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午5:29
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonDemo7 {

    // 私有构造函数
    private SingletonDemo7() {}

    // 静态工厂方法
    public static SingletonDemo7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonDemo7 singleton;

        // JVM 保证这个方法绝对只调用一次，在类初始化前调用的
        Singleton() {
            singleton = new SingletonDemo7();
        }

        public SingletonDemo7 getInstance() {
            return singleton;
        }
    }

}
