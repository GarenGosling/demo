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
 * 功能描述 : 服务器
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 上午11:46
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Server {
    /**
     * ID
     */
    @PrimaryKey
    private String id;

    /**
     * 领域ID
     */
    @SecondaryKey(relate=MANY_TO_ONE)
    private String engineId;

    /**
     * 名称
     */
    private String name;

    /**
     * IP
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 权重
     */
    private Integer weight = 1;
}
