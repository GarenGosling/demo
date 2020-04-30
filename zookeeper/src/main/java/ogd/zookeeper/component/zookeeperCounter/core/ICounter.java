package ogd.zookeeper.component.zookeeperCounter.core;

import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;

/**
 * <p>
 * 功能描述 : 分布式计数器函数式接口
 * </p>
 *
 * @author : Garen Gosling 2020/3/31 上午14:47
 */
public interface ICounter {

    /**
     * <p>
     * 功能描述 : 此计数器有一系列的操作：
     *
     * get(): 获取当前值
     * increment()： 加一
     * decrement(): 减一
     * add()： 增加特定的值
     * subtract(): 减去特定的值
     * trySet(): 尝试设置计数值
     *
     * forceSet(): 强制设置计数值
     * 你必须检查返回结果的succeeded()， 它代表此操作是否成功。 如果操作成功， preValue()代表操作前的值， postValue()代表操作后的值。
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午2:49
     *
     * @param count 函数式
     * @Return org.apache.curator.framework.recipes.atomic.AtomicValue<java.lang.Long>
     **/
    AtomicValue<Long> count(DistributedAtomicLong count) throws Exception;
}
