package ogd.berkeleyDB.easyDPL.dplPlus;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.Transaction;
import com.sleepycat.persist.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
@Component
public class DplPlus {

    @Value("${BerkeleyDB.envPath}")
    private String ENV_PATH;

    @Value("${BerkeleyDB.storeName}")
    private String STORE_NAME;

    /**
     * <p>
     * 功能描述 : 通用方法，有事务
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午3:02
     *
     * @param iCurdHandler 自定义数据库操作接口（lambda表达式）
     * @Return T 返回类型
     **/
    <T> T execute(ICurdHandler<T> iCurdHandler) {
        Environment env;
        EntityStore store = null;
        Transaction txn = null;
        try {
            // 环境
            env = EnvSingleton.getInstance(ENV_PATH).getEnv();
            // 仓库
            store = getStore();
            // 事务
            txn = env.beginTransaction(null, null);
            T t = iCurdHandler.curdT(store, txn);
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
        return execute(((store, txn) -> {
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
     * 功能描述 : 分页
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 下午4:43
     *
     * @param current 当前索引
     * @param size 每页数量
     * @param list 源数据集合
     * @Return ogd.berkeleyDB.easyDPL.dplPlus.Page<T>
     **/
    <T> Page<T> page(Integer current, Integer size, List<T> list) {
        if(current == null) current = 0;
        if(size == null) size = 10;
        return new Page<>(current, size, list);
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
        return new EntityStore(EnvSingleton.getInstance(ENV_PATH).getEnv(), STORE_NAME, storeConfig);
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
                EnvSingleton.getInstance(ENV_PATH).getEnv().sync();
            } catch(DatabaseException dbe) {
                dbe.printStackTrace();
                throw new RuntimeException("store close fail");
            }
        }
    }

}
