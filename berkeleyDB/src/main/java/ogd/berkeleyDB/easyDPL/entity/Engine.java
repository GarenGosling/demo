package ogd.berkeleyDB.easyDPL.entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
