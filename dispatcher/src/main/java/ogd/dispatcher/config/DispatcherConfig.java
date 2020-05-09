package ogd.dispatcher.config;

import ogd.dispatcher.model.AiApp;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 功能描述 : 调度配置
 *          采用"单例模式"之"枚举模式"，最安全
 *          采用读写锁来处理get/set配置列表的并发问题
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 上午11:06
 */
public class DispatcherConfig {

    // 私有构造函数
    private DispatcherConfig(){}

    // 静态工厂方法
    public static DispatcherConfig getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    // 枚举模式，实现单例
    private enum Singleton {
        INSTANCE;

        private DispatcherConfig singleton;

        // JVM 保证这个方法绝对只调用一次，在类初始化前调用的
        Singleton() {
            singleton = new DispatcherConfig();
        }

        public DispatcherConfig getInstance() { return singleton; }
    }

    // 配置列表（应用-引擎-服务器）
    private List<AiApp> aiAppList = null;

    // 是否允许使用默认配置，如果允许，则配置列表为null时，使用默认模板，否则抛出异常
    // TODO 同步配置实现以后，这里改成false
    private boolean allowDefaultConfig = true;

    // 读写锁
    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // 读锁
    private final Lock readLock = lock.readLock();

    // 写锁
    private final Lock writeLock = lock.writeLock();

    /**
     * <p>
     * 功能描述 : 获取配置列表
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 下午2:56
     *
     * @Return java.util.List<ogd.dispatcher.model.AiApp>
     **/
    public List<AiApp> getAiAppList() {
        readLock.lock();
        try {
            if(aiAppList == null) {
                if(allowDefaultConfig) {
                    aiAppList = new DefaultConfig().initAiAppList();
                }else {
                    throw new RuntimeException("配置列表不能为空");
                }
            }
            return aiAppList;
        }finally {
            readLock.unlock();
        }
    }

    /**
     * <p>
     * 功能描述 : 配置列表（应用-引擎-服务器）赋值
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 下午3:33
     *
     * @param aiAppList 配置列表（应用-引擎-服务器）
     * @Return void
     **/
    public void setAiAppList(List<AiApp> aiAppList) {
        writeLock.lock();
        try {
            this.aiAppList = aiAppList;
        } finally {
            writeLock.unlock();
        }
    }

}
