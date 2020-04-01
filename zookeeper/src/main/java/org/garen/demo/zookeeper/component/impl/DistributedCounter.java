package org.garen.demo.zookeeper.component.impl;

import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.garen.demo.zookeeper.component.core.BCounter;
import org.garen.demo.zookeeper.component.IDistributedCounter;
import org.garen.demo.zookeeper.component.entity.CounterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能描述 : 分布式计数器，实现类
 * </p>
 *
 * @author : Garen Gosling 2020/4/1 上午10:17
 */
@Component
public class DistributedCounter implements IDistributedCounter {

    @Autowired
    private BCounter bCounter;

    @Override
    public CounterResult increment(String counterName) throws Exception {
        return bCounter.execute(counterName, DistributedAtomicLong::increment);
    }

    @Override
    public CounterResult decrement(String counterName) throws Exception {
        return bCounter.execute(counterName, DistributedAtomicLong::decrement);
    }

    @Override
    public CounterResult add(String counterName, final Long delta) throws Exception {
        return bCounter.execute(counterName, count -> count.add(delta));
    }

    @Override
    public CounterResult subtract(String counterName, final Long delta) throws Exception {
        return bCounter.execute(counterName, count -> count.subtract(delta));
    }

    @Override
    public CounterResult get(String counterName) throws Exception {
        return bCounter.execute(counterName, DistributedAtomicLong::get);
    }

    @Override
    public Long getValue(String counterName) throws Exception {
        return bCounter.get(counterName);
    }

    @Override
    public CounterResult trySet(String counterName, final Long newValue) throws Exception {
        return bCounter.execute(counterName, count -> count.trySet(newValue));
    }

    @Override
    public void forceSet(String counterName, Long newValue) throws Exception {
        bCounter.forceSet(counterName, newValue);
    }


}
