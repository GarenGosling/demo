package ogd.berkeleyDB.easyDPL.service;

import ogd.berkeleyDB.easyDPL.entity.Engine;
import org.garen.plus.dplPlus.IBaseDao;

import java.util.List;

/**
 * <p>
 * 功能描述 : 业务接口 - 领域
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午3:05
 */
public interface IEngineDao extends IBaseDao<String, Engine> {
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

    /**
     * <p>
     * 功能描述 : 用 dplPlus 的 execute ，使用原生方法
     * </p>
     *
     * @author : Garen Gosling   2020/5/26 下午5:22
     *
     * @param
     * @Return void
     **/
    String useExecute();
}
