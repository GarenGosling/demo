package ogd.berkeleyDB.DPL.config;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import ogd.berkeleyDB.DPL.entity.User;
import ogd.berkeleyDB.DPL.entity.Role;

/**
 * <p>
 * 功能描述 : 数据存取器
 * </p>
 *
 * @author : Garen Gosling 2020/5/14 下午5:33
 */
public class DataAccessor {
    public PrimaryIndex<Integer, User> userById;                    // 一级索引，用户表
    public SecondaryIndex<String,Integer, User> userByName;         // 二级索引，用户表，字段：姓名
    public SecondaryIndex<Integer, Integer, User> userByRoleId;     // 二级索引，用户表，字段：角色ID
    public PrimaryIndex<Integer, Role> roleById;                    // 一级索引，角色表

    /**
     * <p>
     * 功能描述 : 打开索引
     * </p>
     *
     * @author : Garen Gosling   2020/5/14 下午8:17
     *
     * @param store 仓库对象
     * @Return
     **/
    public DataAccessor(EntityStore store) throws DatabaseException {
        userById = store.getPrimaryIndex(Integer.class, User.class);
        userByName = store.getSecondaryIndex(userById, String.class, "name");
        userByRoleId = store.getSecondaryIndex(userById, Integer.class, "roleId");
        roleById = store.getPrimaryIndex(Integer.class, Role.class);
    }
}
