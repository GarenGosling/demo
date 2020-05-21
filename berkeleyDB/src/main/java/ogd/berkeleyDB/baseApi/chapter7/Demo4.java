package ogd.berkeleyDB.baseApi.chapter7;

import com.sleepycat.je.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 功能描述 : 管理方法
 * </p>
 *
 * @author : Garen Gosling 2020/5/20 下午3:53
 */
public class Demo4 {

    public static void main(String[] args) {
        Demo4 demo4 = new Demo4();

        // 打开环境，如果不存在则创建
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        Environment myDbEnv = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA3"), envConfig);

        // 打开数据库，如果不存在则创建
        List<Database> dbList = demo4.openDB(myDbEnv, "db1", "db2", "db3", "db4", "db5");

        // 展示所有数据库
        demo4.showDB(myDbEnv);

        // 删除数据库
        demo4.deleteDB(myDbEnv, dbList.get(3), dbList.get(4));

        // 展示所有数据库
        demo4.showDB(myDbEnv);

        // 重命名
        demo4.renameDB(myDbEnv, dbList.get(1), "db2_new");
        demo4.renameDB(myDbEnv, dbList.get(2), "db3_new");

        // 展示所有数据库
        demo4.showDB(myDbEnv);

        // 截断删除
//        demo4.truncate(myDbEnv, dbList.get(0), true);

        // 关闭数据库
        demo4.closeDB(dbList.get(0), dbList.get(1), dbList.get(2), dbList.get(3), dbList.get(4));
    }

    /**
     * <p>
     * 功能描述 : 打开数据库，不存在则创建
     * </p>
     *
     * @author : Garen Gosling   2020/5/20 下午4:30
     *
     * @param myDbEnv 环境
     * @param dbNames 数据库名称
     * @Return com.sleepycat.je.Database
     **/
    private List<Database> openDB(Environment myDbEnv, String... dbNames) {
        List<Database> dbList = new ArrayList<>();
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        if(dbNames != null && dbNames.length > 0){
            for(String dbName : dbNames){
                Database db = myDbEnv.openDatabase(null, dbName, dbConfig);
                dbList.add(db);
            }
        }
        return dbList;
    }

    /**
     * <p>
     * 功能描述 : 关闭数据库
     * </p>
     *
     * @author : Garen Gosling   2020/5/20 下午4:33
     *
     * @param dbs 数据库
     * @Return void
     **/
    private void closeDB(Database... dbs) {
        if(dbs != null && dbs.length > 0 ){
            for(Database db : dbs){
                if (db != null) db.close();
            }
        }
    }

    /**
     * <p>
     * 功能描述 : 展示所有数据库
     * </p>
     *
     * @author : Garen Gosling   2020/5/20 下午4:23
     *
     * @param myDbEnv 环境
     * @Return void
     **/
    private void showDB(Environment myDbEnv) {
        List myDbNames = myDbEnv.getDatabaseNames();
        for(int i=0; i < myDbNames.size(); i++) {
            System.out.println("DB Name: " + myDbNames.get(i));
        }
    }

    /**
     * <p>
     * 功能描述 : 删除数据库
     * </p>
     *
     * @author : Garen Gosling   2020/5/20 下午4:26
     *
     * @param myDbEnv 环境
     * @param dbs 数据库
     * @Return void
     **/
    private void deleteDB(Environment myDbEnv, Database... dbs) {
        if(dbs != null && dbs.length > 0){
            for(Database db : dbs){
                String dbName = db.getDatabaseName();
                db.close();
                myDbEnv.removeDatabase(null, dbName);
            }
        }

    }

    /**
     * <p>
     * 功能描述 : 重命名
     * </p>
     *
     * @author : Garen Gosling   2020/5/20 下午4:47
     *
     * @param myDbEnv 环境
     * @param db 数据库
     * @param newName 新名字
     * @Return void
     **/
    private void renameDB(Environment myDbEnv, Database db, String newName) {
        String oldName = db.getDatabaseName();
        db.close();
        myDbEnv.renameDatabase(null, oldName, newName);
    }

    /**
     * <p>
     * 功能描述 : 截断删除
     * </p>
     *
     * @author : Garen Gosling   2020/5/20 下午4:53
     *
     * @param myDbEnv 环境
     * @param db 数据库
     * @param returnCount 是否需要返回数量
     * @Return void
     **/
    private void truncate(Environment myDbEnv, Database db, boolean returnCount) {
        long numDiscarded = myDbEnv.truncateDatabase(null, db.getDatabaseName(), returnCount);
        System.out.println("Discarded " + numDiscarded + " records from database " + db.getDatabaseName());
    }
}
