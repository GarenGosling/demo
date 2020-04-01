package org.garen.demo.zookeeper.component.impl;

import org.garen.demo.zookeeper.component.core.BCounter;
import org.garen.demo.zookeeper.component.IDistributedCounterGroup;
import org.garen.demo.zookeeper.component.entity.CounterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * <p>
 * 功能描述 : 分布式计数器组，实现类
 * </p>
 *
 * @author : Garen Gosling 2020/4/1 上午10:12
 */
@Component
public class DistributedCounterGroup implements IDistributedCounterGroup {

    @Autowired
    private BCounter bCounter;

    @Override
    public boolean createCounter(String counterName, String counterDesc, final Long initialize) throws Exception {
        return bCounter.createCounter(counterName, counterDesc, initialize);
    }

    @Override
    public boolean deleteCounter(String counterName) throws Exception {
        return bCounter.deleteCounter(counterName);
    }

    @Override
    public boolean updateCounter(String counterName, String counterDesc) throws Exception {
        return bCounter.updateCounter(counterName, counterDesc);
    }

    @Override
    public List<CounterInfo> getCounterList() throws Exception {
        return bCounter.getCounterList();
    }

    @Override
    public boolean initialize(String counterName, Long initialize) throws Exception {
        return bCounter.initialize(counterName, initialize);
    }
}
