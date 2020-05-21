package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : 反序列化对象
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:20
 */
public class Demo8 {

    public static void main(String[] args) {
        // The key data.
        String aKey = "myData";

        try {
            // 打开环境，如果不存在则创建
            EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            Environment myDbEnv = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA5"), envConfig);
            // 打开数据库，如果不存在则创建
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setAllowCreate(true);
            dbConfig.setSortedDuplicates(true);
            Database db = myDbEnv.openDatabase(null, "testDB", dbConfig);

            // 打开用于存储类信息的数据库。 用于存储类信息的db不需要重复支持。
            dbConfig.setSortedDuplicates(false);
            Database classDb = myDbEnv.openDatabase(null, "classDb", dbConfig);

            // 实例化类目录
            StoredClassCatalog classCatalog = new StoredClassCatalog(classDb);

            // 创建绑定
            EntryBinding dataBinding = new SerialBinding(classCatalog, MyData.class);

            // 为密钥和数据创建DatabaseEntry对象
            DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes(StandardCharsets.UTF_8));
            DatabaseEntry theData = new DatabaseEntry();

            // 查询
            db.get(null, theKey, theData, LockMode.DEFAULT);

            // 使用上面创建的EntryBinding从检索到的DatabaseEntry重新创建MyData对象
            MyData retrievedData = (MyData) dataBinding.entryToObject(theData);
            System.out.println(retrievedData.toString());

            // 关闭数据库、环境
            db.close();
            classDb.close();
            myDbEnv.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
