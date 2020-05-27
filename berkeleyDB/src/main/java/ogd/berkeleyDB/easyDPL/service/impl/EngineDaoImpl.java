package ogd.berkeleyDB.easyDPL.service.impl;

import com.sleepycat.je.LockMode;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import ogd.berkeleyDB.easyDPL.entity.AiApp;
import ogd.berkeleyDB.easyDPL.entity.Engine;
import ogd.berkeleyDB.easyDPL.service.IEngineDao;
import org.garen.plus.dplPlus.BaseDaoImpl;
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
public class EngineDaoImpl extends BaseDaoImpl<String, Engine> implements IEngineDao {

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

    @Override
    public String useExecute() {
        return execute(((store, txn) -> {
            // aiApp 对象主键、二级索引
            PrimaryIndex<String, AiApp> aiAppPI = store.getPrimaryIndex(String.class, AiApp.class);
            SecondaryIndex<String, String, AiApp> aiAppSiName = store.getSecondaryIndex(aiAppPI, String.class, "name");

            // engine 对象主键、二级索引
            PrimaryIndex<String, Engine> enginePI = store.getPrimaryIndex(String.class, Engine.class);
            SecondaryIndex<String, String, Engine> engineSiName = store.getSecondaryIndex(enginePI, String.class, "name");
            SecondaryIndex<Integer, String, Engine> engineSiType = store.getSecondaryIndex(enginePI, Integer.class, "type");

            // 复杂操作，随便写几个意思一下
            AiApp aiApp = aiAppPI.get(txn, "82752fad926a44a1af652245dc83a625", LockMode.DEFAULT);
            aiApp.setDescription("hello world");
            aiAppPI.put(txn, aiApp);
            aiAppSiName.get("hello");
            enginePI.delete(txn, "99992fad926a44a1af652245dc839999");
            engineSiName.get(txn, "engine-1", LockMode.DEFAULT);
            engineSiType.get(txn, 0, LockMode.DEFAULT);
            return aiApp.getName();
        }));
    }
}
