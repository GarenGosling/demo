package ogd.berkeleyDB.easyDPL.service;

import ogd.berkeleyDB.easyDPL.entity.Server;

/**
 * <p>
 * 功能描述 : 业务接口 - 服务器
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午3:24
 */
public interface IServerService {
    Server save(Server server);
    void delete(String id);
    Server update(Server server);
    Server get(String id);
}