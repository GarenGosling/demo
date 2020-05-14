package ogd.berkeleyDB.DPL.entity;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import static com.sleepycat.persist.model.Relationship.*;
import com.sleepycat.persist.model.SecondaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 功能描述 : 用户
 * </p>
 *
 * @author : Garen Gosling 2020/5/14 下午7:06
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    /**
     * 主键 ID
     */
    @PrimaryKey
    private Integer id;

    /**
     * 二级索引 用户名
     */
    @SecondaryKey(relate=MANY_TO_ONE)
    private String name;

    /**
     * 二级索引 角色ID
     */
    @SecondaryKey(relate=MANY_TO_ONE)
    private Integer roleId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

}
