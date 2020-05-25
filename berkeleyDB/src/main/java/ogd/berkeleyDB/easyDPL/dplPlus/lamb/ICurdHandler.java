package ogd.berkeleyDB.easyDPL.dplPlus.lamb;

import com.sleepycat.persist.EntityStore;

/**
 * <p>
 * 功能描述 : 数据库操作（无事务）
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 上午11:20
 */
public interface ICurdHandler<T> {
    T curd(EntityStore store);
}
