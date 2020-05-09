package ogd.dispatcher.arithmetic;

import lombok.extern.slf4j.Slf4j;
import ogd.dispatcher.model.Engine;
import ogd.dispatcher.model.Server;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 功能描述 : 轮询算法
 * </p>
 *
 * @author : Garen Gosling 2020/5/9 下午2:17
 */
@Slf4j
public class PollingImpl implements IArithmetic{

    @Override
    public Server arithmetic(Engine engine) {
        return syncArithmetic(engine);
    }

    private synchronized Server syncArithmetic(Engine engine) {
        if(engine == null || CollectionUtils.isEmpty(engine.getServerList())) return null;
        int serverIndex; // 选出的服务器索引
        if(engine.getLastServerIndex() == null || engine.getLastServerIndex() == engine.getServerList().size() - 1) {
            serverIndex = 0;
        }else{
            serverIndex = engine.getLastServerIndex() + 1;
        }
        engine.setLastServerIndex(serverIndex);
        return engine.getServerList().get(serverIndex);
    }
}
