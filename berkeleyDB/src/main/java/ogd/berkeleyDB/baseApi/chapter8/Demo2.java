package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.je.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : 保存
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 上午11:16
 */
public class Demo2 {

    public static void main(String[] args) {
        // 打开环境，如果不存在则创建
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        Environment myDbEnv = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA4"), envConfig);
        // 打开数据库，如果不存在则创建
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        Database db = myDbEnv.openDatabase(null, "testDB", dbConfig);
        // 保存
        DatabaseEntry theKey = new DatabaseEntry("myFirstKey".getBytes(StandardCharsets.UTF_8));
        DatabaseEntry theData = new DatabaseEntry("myFirstData".getBytes(StandardCharsets.UTF_8));
        db.put(null, theKey, theData);
        // 关闭数据库、环境
        db.close();
        myDbEnv.close();
    }
}
