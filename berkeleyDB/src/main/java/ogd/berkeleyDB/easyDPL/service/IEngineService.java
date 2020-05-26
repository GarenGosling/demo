package ogd.berkeleyDB.easyDPL.service;

import ogd.berkeleyDB.easyDPL.entity.Engine;
import org.garen.plus.dplPlus.IDplService;

import java.util.List;

/**
 * <p>
 * 功能描述 : 业务接口 - 领域
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午3:05
 */
public interface IEngineService extends IDplService<String, Engine> {
    /**
     * <p>
     * 功能描述 : 条件查询
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 下午6:36
     *
     * @param name 应用名称
     * @param type 类型
     * @Return java.util.List<ogd.berkeleyDB.easyDPL.entity.Engine>
     **/
    List<Engine> getByParams(String name, Integer type);
}
