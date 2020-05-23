package ogd.berkeleyDB.easyDPL.service.impl;

import lombok.extern.slf4j.Slf4j;
import ogd.berkeleyDB.easyDPL.entity.AiApp;
import ogd.berkeleyDB.easyDPL.mapper.AiAppMapper;
import ogd.berkeleyDB.easyDPL.service.IAiAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 功能描述 : 业务实现类 - AI 应用
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午3:09
 */
@Slf4j
@Service
public class AiAppServiceImpl implements IAiAppService {

    @Resource
    AiAppMapper aiAppMapper;

    @Override
    public AiApp save(AiApp aiApp) {
        aiApp.setId(aiAppMapper.createPK());    // UUID
        log.info("aiApp id : {}", aiApp.getId());
        return aiAppMapper.save(aiApp.getId(), aiApp);
    }

    @Override
    public void delete(String id) {
        aiAppMapper.delete(id);
    }

    @Override
    public AiApp update(AiApp aiApp) {
        return aiAppMapper.update(aiApp.getId(), aiApp);
    }

    @Override
    public AiApp get(String id) {
        return aiAppMapper.get(id);
    }
}
