package ogd.berkeleyDB.easyDPL.service.impl;

import com.sleepycat.persist.SecondaryIndex;
import ogd.berkeleyDB.easyDPL.entity.Engine;
import ogd.berkeleyDB.easyDPL.service.IEngineService;
import org.garen.plus.dplPlus.DplServiceImpl;
import org.garen.plus.dplPlus.Param;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 功能描述 : 映射类 - 领域
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午12:05
 */
@Component
public class EngineServiceImpl extends DplServiceImpl<String, Engine> implements IEngineService {

    @Override
    public List<Engine> getByParams(String name, Integer type) {
        return listByParams(((store, pi) -> {
            // 声明参数
            Param<String, String, Engine> param1 = null;
            Param<Integer, String, Engine> param2 = null;
            // 参数 1
            if(!StringUtils.isEmpty(name)){
                SecondaryIndex<String, String, Engine> si_name = store.getSecondaryIndex(pi, String.class, "name");
                param1 = new Param<>(si_name, name);
            }
            // 参数 2
            if(!StringUtils.isEmpty(type)){
                SecondaryIndex<Integer, String, Engine> si_type = store.getSecondaryIndex(pi, Integer.class, "type");
                param2 = new Param<>(si_type, type);
            }
            // 返回参数集合
            return toParamList(param1, param2);
        }));
    }
}
