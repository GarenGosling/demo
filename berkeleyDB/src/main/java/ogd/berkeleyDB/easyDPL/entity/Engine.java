package ogd.berkeleyDB.easyDPL.entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.SecondaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.sleepycat.persist.model.Relationship.MANY_TO_ONE;


/**
 * <p>
 * 功能描述 : 领域
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 上午11:44
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Engine {
    /**
     * ID
     */
    @PrimaryKey
    private String id;

    /**
     * 应用ID
     */
    @SecondaryKey(relate=MANY_TO_ONE)
    private String aiAppId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 类型 0是通用 1专用
     */
    private int type;

    /**
     * 上一次提供服务的服务器索引（轮询算法用）
     */
    private Integer lastServerIndex;
}
