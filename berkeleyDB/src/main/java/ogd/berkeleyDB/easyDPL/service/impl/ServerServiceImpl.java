package ogd.berkeleyDB.easyDPL.service.impl;

import lombok.extern.slf4j.Slf4j;
import ogd.berkeleyDB.easyDPL.entity.Server;
import ogd.berkeleyDB.easyDPL.mapper.ServerMapper;
import ogd.berkeleyDB.easyDPL.service.IServerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 功能描述 : 业务实现类 - 领域
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午3:19
 */
@Slf4j
@Service
public class ServerServiceImpl implements IServerService {

    @Resource
    ServerMapper serverMapper;

    @Override
    public Server save(Server server) {
        server.setId(serverMapper.createPK());  // UUID
        log.info("server id : {}", server.getId());
        return serverMapper.save(server.getId(), server);
    }

    @Override
    public void delete(String id) {
        serverMapper.delete(id);
    }

    @Override
    public Server update(Server server) {
        return serverMapper.update(server.getId(), server);
    }

    @Override
    public Server get(String id) {
        return serverMapper.get(id);
    }


}
