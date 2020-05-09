package ogd.dispatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * 功能描述 : 引擎
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 上午11:44
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Engine {
    /**
     * ID
     */
    private Integer id;
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
     * 服务器列表
     */
    private List<Server> serverList;
    /**
     * 上一次提供服务的服务器索引（轮询算法用）
     */
    private Integer lastServerIndex;
}
