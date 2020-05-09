package ogd.dispatcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/9 上午9:54
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Answer {
    private Integer aiAppId;
    private String aiAppName;
    private String aiAppArithmetic;
    private Integer engineId;
    private String engineName;
    private Integer engineType;
    private Integer serverId;
    private String serverName;
    private String ip;
    private Integer port;
    private String answer;
}
