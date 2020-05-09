package ogd.dispatcher.arithmetic;

import lombok.extern.slf4j.Slf4j;
import ogd.dispatcher.model.Engine;
import ogd.dispatcher.model.Server;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 * 功能描述 : 加权随机算法
 *
 * map: {0=1, 1=1, 2=2, 3=2, 4=2, 5=2, 6=2, 7=3, 8=3, 9=3, 10=3, 11=3, 12=3, 13=3, 14=3}
 * key: 14
 * server: Server(id=3, name=默认服务器3, ip=192.168.24.93, port=8093, weight=8)
 *
 * </p>
 *
 * @author : Garen Gosling 2020/5/9 上午11:32
 */
@Slf4j
public class WeightRandomImpl implements IArithmetic{

    @Override
    public Server arithmetic(Engine engine) {
        if(engine == null || CollectionUtils.isEmpty(engine.getServerList())) return null;
        List<Server> serverList = engine.getServerList();
        Map<Integer, Integer> map = new HashMap<>();
        int weightAll = 0;
        Server server = null;

        for(Server s : serverList){
            for(int i=weightAll;i<weightAll + s.getWeight();i++){
                map.put(i, s.getId());
            }
            weightAll += s.getWeight();
        }
        log.info("map: {}", map);
        int key = new Random().nextInt(weightAll);
        log.info("key: {}", key);
        Integer value = map.get(key);
        for(Server s : serverList){
            if(s.getId().equals(value)) {
                server = s;
            }
        }
        log.info("server: {}", server);
        return server;
    }

}
