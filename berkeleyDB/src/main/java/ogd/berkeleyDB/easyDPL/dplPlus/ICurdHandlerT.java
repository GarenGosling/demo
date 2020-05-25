package ogd.berkeleyDB.easyDPL.dplPlus;

import com.sleepycat.je.Transaction;
import com.sleepycat.persist.EntityStore;

/**
 * <p>
 * 功能描述 : 数据库操作（事务中）
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 上午11:20
 */
public interface ICurdHandlerT<T> {
    T curdT(EntityStore store, Transaction txn);
}
