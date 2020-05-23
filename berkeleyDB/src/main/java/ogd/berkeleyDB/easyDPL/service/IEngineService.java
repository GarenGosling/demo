package ogd.berkeleyDB.easyDPL.service;

import ogd.berkeleyDB.easyDPL.entity.Engine;

/**
 * <p>
 * 功能描述 : 业务接口 - AI 应用
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午3:05
 */
public interface IEngineService {
    Engine save(Engine engine);
    void delete(String id);
    Engine update(Engine engine);
    Engine get(String id);
}
