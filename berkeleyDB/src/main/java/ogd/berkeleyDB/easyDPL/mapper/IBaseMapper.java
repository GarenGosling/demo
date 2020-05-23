package ogd.berkeleyDB.easyDPL.mapper;

import ogd.berkeleyDB.easyDPL.core.DplTemplate;
import ogd.berkeleyDB.easyDPL.core.ICurdHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.UUID;

/**
 * <p>
 * 功能描述 : 数据库映射基类
 * </p>
 *
 * @author : Garen Gosling 2020/5/23 下午12:13
 */
@Component
public class IBaseMapper<PK, E> {

    @Value("${BerkeleyDB.entityPackage}")
    private String BDB_ENTITY_PACKAGE;

    @Resource
    DplTemplate dplTemplate;

    @SuppressWarnings("unchecked")
    public E save(PK pk, E e) {
        return (E) dplTemplate.save(getPKClass(), getEClass(), pk, e);
    }

    @SuppressWarnings("unchecked")
    public E update(PK pk, E e) {
        return (E) dplTemplate.update(getPKClass(), getEClass(), pk, e);
    }

    @SuppressWarnings("unchecked")
    public E get(PK pk) {
        return (E) dplTemplate.getByPk(getPKClass(), getEClass(), pk);
    }

    @SuppressWarnings("unchecked")
    public void delete(PK pk) {
        dplTemplate.deleteByPk(getPKClass(), getEClass(), pk);
    }

    /**
     * <p>
     * 功能描述 : 通用方法
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午3:02
     *
     * @param iCurdHandler 自定义数据库操作接口（lambda表达式）
     * @Return T 返回类型
     **/
    public <T> T execute(ICurdHandler<T> iCurdHandler) {
        return dplTemplate.execute(iCurdHandler);
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

    /**
     * <p>
     * 功能描述 : 创建一个主键 （新增用）
     * </p>
     *
     * @author : Garen Gosling   2020/5/23 下午3:14
     *
     * @param
     * @Return java.lang.String
     **/
    public String createPK() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
