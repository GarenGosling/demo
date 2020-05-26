package ogd.berkeleyDB.easyDPL.dplPlus;

import com.sleepycat.persist.SecondaryIndex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 功能描述 : 参数
 * </p>
 *
 * @author : Garen Gosling 2020/5/26 上午9:21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Param<SK, PK, E> {
    /**
     * 二级索引
     */
    private SecondaryIndex<SK, PK, E> secondaryIndex;
    /**
     * 参数值
     */
    private SK key;
}
