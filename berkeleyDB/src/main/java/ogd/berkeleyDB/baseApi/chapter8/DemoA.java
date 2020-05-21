package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.je.*;

import java.io.File;

/**
 * <p>
 * 功能描述 : 使用比较器
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:56
 */
public class DemoA {
    public static void main(String[] args) {
        try {
            // 打开环境，如果不存在则创建
            EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            Environment myDbEnv = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA5"), envConfig);
            // Get the database configuration object
            DatabaseConfig myDbConfig = new DatabaseConfig();
            myDbConfig.setAllowCreate(true);

            // Set the duplicate comparator class
            myDbConfig.setDuplicateComparator(MyDataComparator.class);

            // Open the database that you will use to store your data
            myDbConfig.setSortedDuplicates(true);
            Database myDatabase = myDbEnv.openDatabase(null, "myDb", myDbConfig);
        } catch (DatabaseException dbe) {
            // Exception handling goes here
        }
    }
}
