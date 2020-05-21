package ogd.berkeleyDB.baseApi.chapter7;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import java.io.File;

/**
 * <p>
 * 功能描述 : 打开数据库
 * </p>
 *
 * @author : Garen Gosling 2020/5/20 下午3:48
 */
public class Demo1 {

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
            myDatabase = myDbEnvironment.openDatabase(null, "sampleDatabase", dbConfig);
        } catch (DatabaseException dbe) {
            // 异常处理转到这里
        }
    }

}
