package ogd.berkeleyDB.baseApi.chapter7;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Database;

import java.io.File;

/**
 * <p>
 * 功能描述 : 使用MyDBEnv
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 上午9:40
 */
public class Demo5 {

    public static void main(String[] args) {
        MyDbEnv exampleDbEnv = new MyDbEnv();
        try {
            exampleDbEnv.setup(new File("/Users/liuxueliang/Desktop/BDB/BA"), true);
            Database vendorDb = exampleDbEnv.getVendorDB();
            Database inventoryDB = exampleDbEnv.getInventoryDB();
        } catch(DatabaseException dbe) {
            // Error code goes here
        } finally {
            exampleDbEnv.close();
        }
    }
}
