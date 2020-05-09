package ogd.dispatcher.arithmetic;

import ogd.dispatcher.model.Engine;
import ogd.dispatcher.model.Server;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

/**
 * <p>
 * 功能描述 : 算法实现类 - 随机算法
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 上午10:35
 */
public class RandomImpl implements IArithmetic{

    /**
     * <p>
     * 功能描述 : 随机算法
     * </p>
     *
     * @author : Garen Gosling   2020/5/9 上午9:59
     *
     * @param engine 引擎
     * @Return ogd.dispatcher.model.Server
     **/
    @Override
    public Server arithmetic(Engine engine) {
        if(engine == null || CollectionUtils.isEmpty(engine.getServerList())) return null;
        List<Server> serverList = engine.getServerList();
        int i = new Random().nextInt(serverList.size());
        return serverList.get(i);
    }

}
