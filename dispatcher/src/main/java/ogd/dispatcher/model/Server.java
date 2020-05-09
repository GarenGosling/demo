package ogd.dispatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class Server {
    /**
     * ID
     */
    private Integer id;
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
