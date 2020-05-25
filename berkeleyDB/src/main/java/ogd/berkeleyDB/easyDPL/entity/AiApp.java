package ogd.berkeleyDB.easyDPL.entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.SecondaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import static com.sleepycat.persist.model.Relationship.ONE_TO_ONE;

/**
 * <p>
 * 功能描述 : AI 应用
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 上午11:43
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AiApp {
    /**
     * ID
     */
    @PrimaryKey
    private String id;

    /**
     * 名称
     */
    @SecondaryKey(relate=ONE_TO_ONE)
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 算法
     */
    private String arithmetic;
}
