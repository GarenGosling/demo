package ogd.zookeeper.component.zookeeperCounter.impl;

import ogd.zookeeper.component.zookeeperCounter.core.BCounter;
import ogd.zookeeper.component.zookeeperCounter.IDistributedCounterGroup;
import ogd.zookeeper.component.zookeeperCounter.entity.CounterInfo;
import ogd.zookeeper.response.BusinessException;
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
    public boolean createCounter(String counterName, String counterDesc, final Long initialize) {
        try {
            return bCounter.createCounter(counterName, counterDesc, initialize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public boolean deleteCounter(String counterName) {
        try {
            return bCounter.deleteCounter(counterName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public boolean updateCounter(String counterName, String counterDesc) {
        try {
            return bCounter.updateCounter(counterName, counterDesc);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<CounterInfo> getCounterList() {
        try {
            return bCounter.getCounterList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public boolean initialize(String counterName, Long initialize) {
        try {
            return bCounter.initialize(counterName, initialize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }
}
