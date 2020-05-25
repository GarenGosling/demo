package ogd.berkeleyDB.easyDPL.dplPlus;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.Transaction;
import com.sleepycat.persist.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 功能描述 : BDB 的 DPL 方式，扩展类
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 上午11:12
 */
@Slf4j
public class DplPlus {

    private String evnPath; // 环境地址
    private static final String STORE_NAME = "myStore";

    // 私有构造
    private DplPlus(String evnPath) {
        this.evnPath = evnPath;
    }

    // 单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private volatile static DplPlus instance = null;

    // 静态工厂方法
    static DplPlus getInstance(String envPath) {
        if(instance == null) {  // 双重检测机制   // B
            synchronized (DplPlus.class) {   // 同步锁
                if(instance == null){
                    instance = new DplPlus(envPath);    // A - 3
                }
            }
        }
        return instance;
    }

    /**
     * <p>
     * 功能描述 : 通用方法，事务
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午3:02
     *
     * @param iCurdHandlerT 自定义数据库操作接口（lambda表达式）
     * @Return T 返回类型
     **/
    <T> T executeT(ICurdHandlerT<T> iCurdHandlerT) {
        Environment env;
        EntityStore store = null;
        Transaction txn = null;
        try {
            // 环境
            env = EnvSingleton.getInstance(evnPath).getEnv();
            // 仓库
            store = getStore();
            // 事务
            txn = env.beginTransaction(null, null);
            T t = iCurdHandlerT.curdT(store, txn);
            txn.commit();
            return t;
        }catch (Exception e) {
            e.printStackTrace();
            if(txn != null) txn.abort();
            throw e;
        }finally {
            closeStore(store);
        }
    }

    /**
     * <p>
     * 功能描述 : 通用方法，无事务
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 上午9:32
     *
     * @param iCurdHandler 自定义数据库操作接口（lambda表达式）
     * @Return T 返回类型
     **/
    <T> T execute(ICurdHandler<T> iCurdHandler) {
        EntityStore store = null;
        try {
            store = getStore();
            return iCurdHandler.curd(store);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            closeStore(store);
        }
    }


    /**
     * <p>
     * 功能描述 : 主键查询
     * </p>
     *
     * @author : Garen Gosling   2020/5/22 下午4:42
     *
     * @param primaryKeyClass 主键类型
     * @param entityClass 实体类型
     * @param pk 主键
     * @Return E 实体
     **/
    <PK, E> E getByPk(Class<PK> primaryKeyClass, Class<E> entityClass, PK pk) {
        EntityStore store = getStore();
        PrimaryIndex<PK, E> pi = store.getPrimaryIndex(primaryKeyClass, entityClass);
        E e = pi.get(pk);
        closeStore(store);
        return e;
    }

    /**
     * <p>
     * 功能描述 : 增
     * </p>
     *
     * @author : Garen Gosling   2020/5/22 下午4:49
     *
     * @param primaryKeyClass 主键类型
     * @param entityClass 实体类型
     * @param pk 主键
     * @param entity 实体
     * @Return void
     **/
    <PK, E> E save(Class<PK> primaryKeyClass, Class<E> entityClass, PK pk, E entity) {
        EntityStore store = getStore();
        PrimaryIndex<PK, E> pi = store.getPrimaryIndex(primaryKeyClass, entityClass);
        E e = pi.get(pk);
        if(e != null) {
            closeStore(store);
            throw new RuntimeException("保存失败：对象已存在");
        }
        pi.put(entity);
        closeStore(store);
        return entity;
    }

    /**
     * <p>
     * 功能描述 : 删
     * </p>
     *
     * @author : Garen Gosling   2020/5/22 下午4:55
     *
     * @param primaryKeyClass 主键类型
     * @param entityClass 实体类型
     * @param pk 主键
     * @Return void
     **/
    <PK, E> void deleteByPk(Class<PK> primaryKeyClass, Class<E> entityClass, PK pk) {
        EntityStore store = getStore();
        PrimaryIndex<PK, E> pi = store.getPrimaryIndex(primaryKeyClass, entityClass);
        pi.delete(pk);
        closeStore(store);
    }

    /**
     * <p>
     * 功能描述 : 改
     * </p>
     *
     * @author : Garen Gosling   2020/5/22 下午4:59
     *
     * @param primaryKeyClass 主键类型
     * @param entityClass 实体类型
     * @param pk 主键
     * @param entity 实体
     * @Return void
     **/
    <PK, E> E update(Class<PK> primaryKeyClass, Class<E> entityClass, PK pk, E entity) {
        return executeT(((store, txn) -> {
            PrimaryIndex<PK, E> pi = store.getPrimaryIndex(primaryKeyClass, entityClass);
            // 查
            E e = pi.get(txn, pk, LockMode.DEFAULT);
            if(e == null) {
                throw new RuntimeException("修改失败：对象不存在");
            }
            MyBeanUtils.copyProperties(entity, e);
            // 删
            pi.delete(txn, pk);
            // 增
            pi.put(txn, e);
            return e;
        }));
    }

    /**
     * <p>
     * 功能描述 : 查询全部
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午6:33
     *
     * @param primaryKeyClass 主键
     * @param entityClass 实体类
     * @Return java.util.List<E>
     **/
    <PK, E> List<E> list(Class<PK> primaryKeyClass, Class<E> entityClass) {
        EntityStore store = getStore();
        PrimaryIndex<PK, E> pi = store.getPrimaryIndex(primaryKeyClass, entityClass);
        EntityCursor<E> entities = pi.entities();
        List<E> list = new ArrayList<>();
        try {
            for (E e : entities) {
                list.add(e);
            }
        } finally {
            entities.close();
        }
        return list;
    }

    /**
     * <p>
     * 功能描述 : 获取仓库
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午5:17
     *
     * @param
     * @Return com.sleepycat.persist.EntityStore
     **/
    private EntityStore getStore() {
        // 创建配置对象
        StoreConfig storeConfig = new StoreConfig();
        // 配置参数
        storeConfig.setAllowCreate(true);  // 仓库文件不存在是否要创建，true创建、false抛异常
        storeConfig.setTransactional(true); // 开启事务
        // 仓库对象
        return new EntityStore(EnvSingleton.getInstance(evnPath).getEnv(), STORE_NAME, storeConfig);
    }

    /**
     * <p>
     * 功能描述 : 关闭仓库
     * </p>
     *
     * @author : Garen Gosling   2020/5/22 下午4:13
     *
     * @param store 仓库对象
     * @Return void
     **/
    private void closeStore(EntityStore store) {
        if (store != null) {
            try {
                store.close();
                EnvSingleton.getInstance(evnPath).getEnv().sync();
            } catch(DatabaseException dbe) {
                dbe.printStackTrace();
                throw new RuntimeException("store close fail");
            }
        }
    }

}
