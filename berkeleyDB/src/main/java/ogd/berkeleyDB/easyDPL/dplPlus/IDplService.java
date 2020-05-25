package ogd.berkeleyDB.easyDPL.dplPlus;

import java.util.List;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/25 上午9:55
 */
public interface IDplService<PK, E> {
    /**
     * <p>
     * 功能描述 : 新增
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 下午4:55
     *
     * @param pk 主键
     * @param e 实体
     * @Return E 返回实体对象
     **/
    E save(PK pk, E e);

    /**
     * <p>
     * 功能描述 : 修改
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 下午4:55
     *
     * @param pk 主键
     * @param e 实体
     * @Return E 返回实体对象
     **/
    E update(PK pk, E e);

    /**
     * <p>
     * 功能描述 : 主键查询
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 下午4:56
     *
     * @param pk 主键
     * @Return E 返回实体对象
     **/
    E get(PK pk);

    /**
     * <p>
     * 功能描述 : 删除
     * </p>
     *
     * @author : Garen Gosling   2020/5/25 下午4:56
     *
     * @param pk 主键
     * @Return void
     **/
    void delete(PK pk);
    List<E> listAll();
    <T> Page<T> pageAll(Integer current, Integer size, List<T> list);

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
    <T> T execute(ICurdHandler<T> iCurdHandler);
}
