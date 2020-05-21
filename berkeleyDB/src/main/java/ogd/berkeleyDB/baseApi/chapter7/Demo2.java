package ogd.berkeleyDB.baseApi.chapter7;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import java.io.File;

/**
 * <p>
 * 功能描述 : 延迟写入数据库
 * </p>
 *
 * @author : Garen Gosling 2020/5/20 下午3:53
 */
public class Demo2 {

    public static void main(String[] args) {
        Environment myDbEnvironment = null;
        Database myDatabase = null;

        try {
            // 打开环境，如果不存在则创建
            EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            myDbEnvironment = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA"), envConfig);

            // 打开数据库，如果不存在则创建
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setAllowCreate(true);
            // 延迟写入
            dbConfig.setDeferredWrite(true);
            myDatabase = myDbEnvironment.openDatabase(null, "sampleDatabase", dbConfig);

            // 做一些操作，此处省略。。。

            // 当您希望在关闭数据库之前的某个特定点上保持工作状态时，请执行此操作。
            myDatabase.sync();

            // 然后关闭这里的数据库和环境(本章稍后将进行描述)。
            if (myDatabase != null) {
                myDatabase.close();
            }

            if (myDbEnvironment != null) {
                myDbEnvironment.close();
            }
        } catch (DatabaseException dbe) {
            // 异常处理转到这里
        }

    }

}
