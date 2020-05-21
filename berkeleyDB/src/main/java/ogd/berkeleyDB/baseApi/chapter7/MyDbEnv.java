package ogd.berkeleyDB.baseApi.chapter7;

/**
 * <p>
 * 功能描述 : 数据库实例
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 上午9:38
 */

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Database;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.Environment;

import java.io.File;

public class MyDbEnv {

    private Environment myEnv;
    private Database vendorDb;
    private Database inventoryDb;

    public MyDbEnv() {}


    public void setup(File envHome, boolean readOnly)
            throws DatabaseException {

        // 实例化环境和数据库配置对象
        EnvironmentConfig myEnvConfig = new EnvironmentConfig();
        DatabaseConfig myDbConfig = new DatabaseConfig();
        // 将环境和数据库配置为只读状态，该状态由此方法调用上的readOnly参数标识
        myEnvConfig.setReadOnly(readOnly);
        myDbConfig.setReadOnly(readOnly);
        // 如果环境为写打开，那么我们希望能够创建环境和数据库(如果它们不存在)。
        myEnvConfig.setAllowCreate(!readOnly);
        myDbConfig.setAllowCreate(!readOnly);

        // 实例化环境。这将打开它，也可能创建它。
        myEnv = new Environment(envHome, myEnvConfig);

        // 现在创建并打开我们的数据库
        vendorDb = myEnv.openDatabase(null, "VendorDB", myDbConfig);
        inventoryDb = myEnv.openDatabase(null,"InventoryDB", myDbConfig);
    }

    // Getter方法
    public Environment getEnvironment() {
        return myEnv;
    }

    public Database getVendorDB() {
        return vendorDb;
    }

    public Database getInventoryDB() {
        return inventoryDb;
    }

    // 关闭环境
    public void close() {
        if (myEnv != null) {
            try {
                vendorDb.close();
                inventoryDb.close();
                myEnv.close();
            } catch(DatabaseException dbe) {
                System.err.println("Error closing MyDbEnv: " +
                        dbe.toString());
                System.exit(-1);
            }
        }
    }

}
