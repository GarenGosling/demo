package ogd.berkeleyDB.easyDPL.dplPlus;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
public class DplServiceImpl<PK, E> implements IDplService<PK, E> {

    @Resource
    DplPlus dplPlus;

    @SuppressWarnings("unchecked")
    public E save(PK pk, E e) {
        return (E) dplPlus.save(getPKClass(), getEClass(), pk, e);
    }

    @SuppressWarnings("unchecked")
    public E update(PK pk, E e) {
        return (E) dplPlus.update(getPKClass(), getEClass(), pk, e);
    }

    @SuppressWarnings("unchecked")
    public E get(PK pk) {
        return (E) dplPlus.getByPk(getPKClass(), getEClass(), pk);
    }

    @SuppressWarnings("unchecked")
    public void delete(PK pk) {
        dplPlus.deleteByPk(getPKClass(), getEClass(), pk);
    }

    @SuppressWarnings("unchecked")
    public List<E> listAll() {
        return dplPlus.list(getPKClass(), getEClass());
    }

    @SuppressWarnings("unchecked")
    public <SK> List<E> listBySk(String keyName, Class<SK> keyClass, SK sk) {
        return dplPlus.listBySk(getPKClass(), getEClass(), keyName, keyClass, sk);
    }

    public <T> Page<T> pageAll(Integer current, Integer size, List<T> list) { return dplPlus.page(current, size, list); }

    public <T> T execute(ICurdHandler<T> iCurdHandler) { return dplPlus.execute(iCurdHandler); }


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
