package ogd.berkeleyDB.DPL.entity;

/**
 * <p>
 * 功能描述 : 角色
 * </p>
 *
 * @author : Garen Gosling 2020/5/14 下午7:06
 */
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role {

    /**
     * 主键
     */
    @PrimaryKey
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description; // 描述

}