package ogd.dispatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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
public class AiApp {
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
     * 引擎列表
     */
    private List<Engine> engineList;
}
