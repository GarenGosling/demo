package org.garen.demo.zookeeper.component.zookeeperCounter;

import org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterResult;

/**
 * <p>
 * 功能描述 : 分布式计数器，接口
 * </p>
 *
 * @author : Garen Gosling 2020/4/1 上午10:08
 */
public interface IDistributedCounter {

    /**
     * <p>
     * 功能描述 : 计数值 + 1
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:29
     *
     * @param counterName 计数器名称
     * @Return org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterResult
     **/
    CounterResult increment(String counterName);

    /**
     * <p>
     * 功能描述 : 计数值 - 1
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:38
     *
     * @param counterName 计数器名称
     * @Return org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterResult
     **/
    CounterResult decrement(String counterName);

    /**
     * <p>
     * 功能描述 : 计数值 + delta
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:40
     *
     * @param counterName 计数器名称
     * @param delta 加上的值
     * @Return org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterResult
     **/
    CounterResult add(String counterName, final Long delta);

    /**
     * <p>
     * 功能描述 : 计数值 - delta
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:35
     *
     * @param counterName 计数器名称
     * @param delta 减去的值
     * @Return org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterResult
     **/
    CounterResult subtract(String counterName, final Long delta);

    /**
     * <p>
     * 功能描述 : 查询当前计数值
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:24
     *
     * @param counterName 计数器名称
     * @Return java.lang.Long
     **/
    CounterResult get(String counterName);

    /**
     * <p>
     * 功能描述 : 查询当前计数值
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:43
     *
     * @param counterName 计数器名称
     * @Return java.lang.Long
     **/
    Long getValue(String counterName);

    /**
     * <p>
     * 功能描述 : 尝试设置计数值
     * </p>
     *
     * @author : Garen Gosling   2020/4/1 上午10:30
     *
     * @param counterName 计数器名称
     * @param newValue 新值
     * @Return org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterResult
     **/
    CounterResult trySet(String counterName, final Long newValue);

    /**
     * <p>
     * 功能描述 : 强迫设置计数值
     * </p>
     *
     * @author : Garen Gosling   2020/3/31 下午5:26
     *
     * @param counterName 计数器名称
     * @param newValue 新值
     * @Return void
     **/
    void forceSet(String counterName, Long newValue);
}
