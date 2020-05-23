package ogd.berkeleyDB.easyDPL.service;

import ogd.berkeleyDB.easyDPL.entity.AiApp;

/**
 * <p>
 * 功能描述 : 业务接口 - AI 应用
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午3:05
 */
public interface IAiAppService {
    AiApp save(AiApp aiApp);
    void delete(String id);
    AiApp update(AiApp aiApp);
    AiApp get(String id);
}
