package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.serial.SerialBinding;

import com.sleepycat.je.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : 序列化对象，使用MyData
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:20
 */
public class Demo7 {

    public static void main(String[] args) {
        // The key data.
        String aKey = "myData";

        // The data data
        MyData data2Store = new MyData();
        data2Store.setLongData(123456789L);
        data2Store.setDoubleData(1234.9876543);
        data2Store.setDescription("A test instance of this class");

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

            // 创建key
            DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes(StandardCharsets.UTF_8));

            // 为数据创建DatabaseEntry。使用刚刚创建的EntryBinding对象填充DatabaseEntry
            DatabaseEntry theData = new DatabaseEntry();
            dataBinding.objectToEntry(data2Store, theData);

            // 保存
            db.put(null, theKey, theData);

            // 关闭数据库、环境
            db.close();
            classDb.close();
            myDbEnv.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
