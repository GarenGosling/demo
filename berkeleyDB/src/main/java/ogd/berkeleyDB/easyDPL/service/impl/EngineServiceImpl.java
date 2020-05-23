package ogd.berkeleyDB.easyDPL.service.impl;

import lombok.extern.slf4j.Slf4j;
import ogd.berkeleyDB.easyDPL.entity.Engine;
import ogd.berkeleyDB.easyDPL.mapper.EngineMapper;
import ogd.berkeleyDB.easyDPL.service.IEngineService;
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
public class EngineServiceImpl implements IEngineService {

    @Resource
    EngineMapper engineMapper;

    @Override
    public Engine save(Engine engine) {
        engine.setId(engineMapper.createPK());    // UUID
        log.info("engine id : {}", engine.getId());
        return engineMapper.save(engine.getId(), engine);
    }

    @Override
    public void delete(String id) {
        engineMapper.delete(id);
    }

    @Override
    public Engine update(Engine engine) {
        return engineMapper.update(engine.getId(), engine);
    }

    @Override
    public Engine get(String id) {
        return engineMapper.get(id);
    }
}
