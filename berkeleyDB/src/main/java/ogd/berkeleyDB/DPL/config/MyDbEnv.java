package ogd.berkeleyDB.DPL.config;

import java.io.File;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

/**
 * <p>
 * 功能描述 : 封装环境、仓库、关闭等功能
 * </p>
 *
 * @author : Garen Gosling 2020/5/14 下午5:30
 */
public class MyDbEnv {

    private Environment myEnv;  // 环境
    private EntityStore store;  // 仓库

    public MyDbEnv() {}

    /**
     * <p>
     * 功能描述 : 设置
     * </p>
     *
     * @author : Garen Gosling   2020/5/14 下午8:11
     *
     * @param envHome 环境文件
     * @param readOnly 是否只读
     * @Return void
     **/
    public void setup(File envHome, boolean readOnly)
            throws DatabaseException {
        // 创建配置对象
        EnvironmentConfig myEnvConfig = new EnvironmentConfig();
        StoreConfig storeConfig = new StoreConfig();

        // 配置参数
        myEnvConfig.setReadOnly(readOnly);
        storeConfig.setReadOnly(readOnly);
        myEnvConfig.setAllowCreate(!readOnly);  // 环境文件不存在是否要创建，true创建、false抛异常
        storeConfig.setAllowCreate(!readOnly);  // 仓库文件不存在是否要创建，true创建、false抛异常

        // 创建环境、仓库对象
        myEnv = new Environment(envHome, myEnvConfig);
        store = new EntityStore(myEnv, "EntityStore", storeConfig);
    }

    /**
     * <p>
     * 功能描述 : 获取仓库对象
     * </p>
     *
     * @author : Garen Gosling   2020/5/14 下午8:16
     *
     * @param
     * @Return com.sleepycat.persist.EntityStore
     **/
    public EntityStore getEntityStore() {
        return store;
    }

    /**
     * <p>
     * 功能描述 : 获取环境对象
     * </p>
     *
     * @author : Garen Gosling   2020/5/14 下午8:16
     *
     * @param
     * @Return com.sleepycat.je.Environment
     **/
    public Environment getEnv() {
        return myEnv;
    }

    /**
     * <p>
     * 功能描述 : 关闭仓库对象、环境对象
     * </p>
     *
     * @author : Garen Gosling   2020/5/14 下午8:16
     *
     * @param
     * @Return void
     **/
    public void close() {
        if (store != null) {
            try {
                store.close();
            } catch(DatabaseException dbe) {
                System.err.println("Error closing store: " +
                        dbe.toString());
                System.exit(-1);
            }
        }

        if (myEnv != null) {
            try {
                // Finally, close the environment.
                myEnv.close();
            } catch(DatabaseException dbe) {
                System.err.println("Error closing MyDbEnv: " +
                        dbe.toString());
                System.exit(-1);
            }
        }
    }
}
