package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.je.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : 查询
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 上午11:16
 */
public class Demo3 {

    public static void main(String[] args) {
        // 打开环境，如果不存在则创建
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        Environment myDbEnv = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA4"), envConfig);
        // 打开数据库，如果不存在则创建
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        Database db = myDbEnv.openDatabase(null, "testDB", dbConfig);
        // 查询
        String aKey = "myFirstKey";
        DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes(StandardCharsets.UTF_8));
        DatabaseEntry theData = new DatabaseEntry();
        OperationStatus operationStatus = db.get(null, theKey, theData, LockMode.DEFAULT);
        if(operationStatus.equals(OperationStatus.SUCCESS)){
            byte[] retData = theData.getData();
            String foundData = new String(retData, StandardCharsets.UTF_8);
            System.out.println("For key: '" + aKey + "' found data: '" + foundData + "'.");
        } else {
            System.out.println("No record found for key '" + aKey + "'.");
        }
        // 关闭数据库、环境
        db.close();
        myDbEnv.close();
    }
}
