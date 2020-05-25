package ogd.berkeleyDB.easyDPL.dplPlus.bs;

import ogd.berkeleyDB.easyDPL.dplPlus.core.Page;

import java.util.List;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/25 上午9:55
 */
public interface IDplService<PK, E> {
    E save(PK pk, E e);
    E update(PK pk, E e);
    E get(PK pk);
    void delete(PK pk);
    List<E> listAll();
    <T> Page<T> pageAll(Integer current, Integer size, List<T> list);
}
