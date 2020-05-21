package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.je.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : BIND API - 数字对象 - 保存
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 上午11:39
 */
public class Demo5 {

    public static void main(String[] args) {
        // 打开环境，如果不存在则创建
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        Environment myDbEnv = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA4"), envConfig);
        // 打开数据库，如果不存在则创建
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        Database db = myDbEnv.openDatabase(null, "testDB", dbConfig);

        String aKey = "myLong";
        DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes(StandardCharsets.UTF_8));

        // Now build the DatabaseEntry using a TupleBinding
        Long myLong = 123456789L;
        DatabaseEntry theData = new DatabaseEntry();
        EntryBinding myBinding = TupleBinding.getPrimitiveBinding(Long.class);
        myBinding.objectToEntry(myLong, theData);

        // Now store it
        db.put(null, theKey, theData);

        // 关闭数据库、环境
        db.close();
        myDbEnv.close();
    }
}
