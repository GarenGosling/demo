package ogd.berkeleyDB.DPL.use;

import java.io.File;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityCursor;
import ogd.berkeleyDB.DPL.config.DataAccessor;
import ogd.berkeleyDB.DPL.config.MyDbEnv;
import ogd.berkeleyDB.DPL.entity.Role;
import ogd.berkeleyDB.DPL.entity.User;

/**
 * <p>
 * 功能描述 : 从数据库查询
 * </p>
 *
 * @author : Garen Gosling 2020/5/14 下午6:08
 */
public class GetFromDB {

    private static File myDbEnvPath = new File("/Users/liuxueliang/Desktop/BDB/JEDB");
    private DataAccessor da;
    private static MyDbEnv myDbEnv = new MyDbEnv();

    public static void main(String args[]) {
        GetFromDB eir = new GetFromDB();
        try {
            eir.run();
        } catch (DatabaseException dbe) {
            System.err.println("GetFromDB: " + dbe.toString());
            dbe.printStackTrace();
        } finally {
            myDbEnv.close();
        }
        System.out.println("All done.");
    }

    private void run() throws DatabaseException {
        myDbEnv.setup(myDbEnvPath, true);
        da = new DataAccessor(myDbEnv.getEntityStore());
        // 查询全部角色
        showRoleAll();
        // 查询全部用户
        showUserAll();
        // 通过姓名查用户
        showUserByName();
        // 通过角色ID查用户
        showUserByRoleId();

    }

    private void showRoleAll() {
        EntityCursor<Role> entities = da.roleById.entities();
        printList(entities);
    }

    private void showUserAll() {
        EntityCursor<User> items = da.userById.entities();
        printList(items);
    }

    private void showUserByName() {
        String name = "Gosling";
        EntityCursor<User> items = da.userByName.subIndex(name).entities();
        printList(items);
    }

    private void showUserByRoleId() {
        Integer roleId = 3;
        EntityCursor<User> items = da.userByRoleId.subIndex(roleId).entities();
        printList(items);
    }

    private <T> void printList(EntityCursor<T> items) {
        try {
            for (T item : items) {
                System.out.println(item.toString());
            }
            System.out.println();
        } finally {
            items.close();
        }
    }

}