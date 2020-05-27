package ogd.berkeleyDB.easyDPL.service.impl;

import ogd.berkeleyDB.easyDPL.entity.Server;
import ogd.berkeleyDB.easyDPL.service.IServerDao;
import org.garen.plus.dplPlus.BaseDaoImpl;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能描述 : 映射类 - 服务器
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午12:05
 */
@Component
public class ServerDaoImpl extends BaseDaoImpl<String, Server> implements IServerDao {

}
