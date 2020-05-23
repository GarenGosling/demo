package ogd.berkeleyDB.easyDPL.entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
