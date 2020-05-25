package ogd.berkeleyDB.easyDPL.dplPlus.bs;

import ogd.berkeleyDB.easyDPL.dplPlus.core.DplPlus;
import ogd.berkeleyDB.easyDPL.dplPlus.lamb.ICurdHandler;
import ogd.berkeleyDB.easyDPL.dplPlus.lamb.ICurdHandlerT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * <p>
 * 功能描述 : 数据库映射基类
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午12:13
 */
@Component
public class BaseServiceImpl<PK, E> implements IBaseService<PK, E> {

    @Value("${BerkeleyDB.envPath}")
    private String BDB_ENV_PATH;

    @SuppressWarnings("unchecked")
    public E save(PK pk, E e) {
        return (E) DplPlus.getInstance(BDB_ENV_PATH).save(getPKClass(), getEClass(), pk, e);
    }

    @SuppressWarnings("unchecked")
    public E update(PK pk, E e) {
        return (E) DplPlus.getInstance(BDB_ENV_PATH).update(getPKClass(), getEClass(), pk, e);
    }

    @SuppressWarnings("unchecked")
    public E get(PK pk) {
        return (E) DplPlus.getInstance(BDB_ENV_PATH).getByPk(getPKClass(), getEClass(), pk);
    }

    @SuppressWarnings("unchecked")
    public void delete(PK pk) {
        DplPlus.getInstance(BDB_ENV_PATH).deleteByPk(getPKClass(), getEClass(), pk);
    }

    @SuppressWarnings("unchecked")
    public List<E> list() {
        return DplPlus.getInstance(BDB_ENV_PATH).list(getPKClass(), getEClass());
    }

    /**
     * <p>
     * 功能描述 : 通用方法，有事务
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午3:02
     *
     * @param iCurdHandlerT 自定义数据库操作接口（lambda表达式）
     * @Return T 返回类型
     **/
    public <T> T executeT(ICurdHandlerT<T> iCurdHandlerT) {
        return DplPlus.getInstance(BDB_ENV_PATH).executeT(iCurdHandlerT);
    }

    /**
     * <p>
     * 功能描述 : 通用方法，无事务
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 上午9:34
     *
     * @param iCurdHandler 自定义数据库操作接口（lambda表达式）
     * @Return T 返回类型
     **/
    public <T> T execute(ICurdHandler<T> iCurdHandler) {
        return DplPlus.getInstance(BDB_ENV_PATH).execute(iCurdHandler);
    }

    /**
     * <p>
     * 功能描述 : 获取主键类型
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午2:40
     *
     * @param
     * @Return java.lang.Class
     **/
    private Class getPKClass() {
        return (Class<PK>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * <p>
     * 功能描述 : 获取实体类型
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午2:40
     *
     * @param
     * @Return java.lang.Class
     **/
    private Class getEClass() {
        return (Class<E>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

}
