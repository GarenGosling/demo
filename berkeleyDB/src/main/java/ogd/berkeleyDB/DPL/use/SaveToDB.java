package ogd.berkeleyDB.DPL.use;
import java.io.File;

import com.sleepycat.je.DatabaseException;
import ogd.berkeleyDB.DPL.config.DataAccessor;
import ogd.berkeleyDB.DPL.config.MyDbEnv;
import ogd.berkeleyDB.DPL.entity.User;
import ogd.berkeleyDB.DPL.entity.Role;

/**
 * <p>
 * 功能描述 : 保存到数据库
 * </p>
 *
 * @author : Garen Gosling 2020/5/14 下午5:42
 */
public class SaveToDB {

    private static File myDbEnvPath = new File("/Users/liuxueliang/Desktop/BDB/JEDB");
    private DataAccessor da;
    private static MyDbEnv myDbEnv = new MyDbEnv();


    public static void main(String args[]) {
        SaveToDB edp = new SaveToDB();
        try {
            edp.run();
        } catch (DatabaseException dbe) {
            System.err.println("SaveToDB: " + dbe.toString());
            dbe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
            e.printStackTrace();
        } finally {
            myDbEnv.close();
        }
        System.out.println("All done.");
    }

    /**
     * <p>
     * 功能描述 : run方法会做四件事
     *
     * 1、打开环境和仓库
     * 2、实例化一个数据存取器对象，我们将使用该对象将数据写入仓库
     * 3、加载所有供应商信息
     * 4、加载所有库存信息
     *
     * 注意，myDbEnv 对象被设置为可读写。这样 EntityStore就可以支持事务
     * </p>
     *
     * @author : Garen Gosling   2020/5/14 下午5:55
     *
     * @param args
     * @Return void
     **/
    private void run() {
        myDbEnv.setup(myDbEnvPath, false);
        da = new DataAccessor(myDbEnv.getEntityStore());    // 打开数据访问器，用于存储持久对象
        // 持久化用户数据
        da.userById.put(new User(1, "Garen", 1, "18816894292", "garen@163.com"));
        da.userById.put(new User(2, "Garen", 2, "18816894292", "garen@163.com"));
        da.userById.put(new User(3, "Garen", 3, "18816894292", "garen@163.com"));
        da.userById.put(new User(4, "Gosling", 2, "13552979387", "gosling@163.com"));
        da.userById.put(new User(5, "Gosling", 3, "13552979387", "gosling@163.com"));
        da.userById.put(new User(6, "kitty", 3, "13846915110", "kitty@163.com"));
        da.userById.put(new User(7, "Tom", 4, "13245637895", "tom@163.com"));
        da.userById.put(new User(8, "amy", 4, "15933338888", "amy@163.com"));
        da.userById.put(new User(9, "grace", 4, "13288889999", "grace@163.com"));
        da.userById.put(new User(10, "timi", 4, "13699990000", "timi@163.com"));
        // 持久化角色数据
        da.roleById.put(new Role(1, "root", "超级管理员"));
        da.roleById.put(new Role(2, "admin", "管理员"));
        da.roleById.put( new Role(3, "manager", "经理"));
        da.roleById.put(new Role(4, "employee", "员工"));
    }

}
