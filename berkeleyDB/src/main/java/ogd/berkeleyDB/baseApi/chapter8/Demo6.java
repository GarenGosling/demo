package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.je.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : BIND API - 数字对象 - 查询
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 上午11:39
 */
public class Demo6 {

    public static void main(String[] args) {
        // 打开环境，如果不存在则创建
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        Environment myDbEnv = new Environment(new File("/Users/liuxueliang/Desktop/BDB/BA4"), envConfig);
        // 打开数据库，如果不存在则创建
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        Database db = myDbEnv.openDatabase(null, "testDB", dbConfig);


        // Need a key for the get
        String aKey = "myLong";
        DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes(StandardCharsets.UTF_8));

        // Need a DatabaseEntry to hold the associated data.
        DatabaseEntry theData = new DatabaseEntry();

        // Bindings need only be created once for a given scope
        EntryBinding myBinding = TupleBinding.getPrimitiveBinding(Long.class);

        // Get it
        OperationStatus retVal = db.get(null, theKey, theData, LockMode.DEFAULT);
        String retKey = null;
        if (retVal == OperationStatus.SUCCESS) {
            // Recreate the data.
            // Use the binding to convert the byte array contained in theData
            // to a Long type.
            Long theLong = (Long) myBinding.entryToObject(theData);
            retKey = new String(theKey.getData(), StandardCharsets.UTF_8);
            System.out.println("For key: '" + retKey + "' found Long: '" + theLong + "'.");
        } else {
            System.out.println("No record found for key '" + null + "'.");
        }


        // 关闭数据库、环境
        db.close();
        myDbEnv.close();
    }
}
