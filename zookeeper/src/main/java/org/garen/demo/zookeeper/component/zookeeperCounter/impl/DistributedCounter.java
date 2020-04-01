package org.garen.demo.zookeeper.component.zookeeperCounter.impl;

import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.garen.demo.zookeeper.component.zookeeperCounter.core.BCounter;
import org.garen.demo.zookeeper.component.zookeeperCounter.IDistributedCounter;
import org.garen.demo.zookeeper.component.zookeeperCounter.entity.CounterResult;
import org.garen.demo.zookeeper.response.BusinessException;
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
    public CounterResult increment(String counterName) {
        try {
            return bCounter.execute(counterName, DistributedAtomicLong::increment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public CounterResult decrement(String counterName) {
        try {
            return bCounter.execute(counterName, DistributedAtomicLong::decrement);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public CounterResult add(String counterName, final Long delta) {
        try {
            return bCounter.execute(counterName, count -> count.add(delta));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public CounterResult subtract(String counterName, final Long delta) {
        try {
            return bCounter.execute(counterName, count -> count.subtract(delta));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public CounterResult get(String counterName) {
        try {
            return bCounter.execute(counterName, DistributedAtomicLong::get);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Long getValue(String counterName) {
        try {
            return bCounter.get(counterName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public CounterResult trySet(String counterName, final Long newValue) {
        try {
            return bCounter.execute(counterName, count -> count.trySet(newValue));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public void forceSet(String counterName, Long newValue) {
        try {
            bCounter.forceSet(counterName, newValue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }


}
