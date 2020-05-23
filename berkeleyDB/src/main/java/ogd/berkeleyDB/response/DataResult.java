package ogd.berkeleyDB.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinpin
 * @date 2020-04-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResult<T> {
    private static final long serialVersionUID = 5063776667672736098L;

    private boolean success = true;
    private String message = "成功";
    private Integer code = ResultCodeEnum.RESULT_SUCCESS;
    private T data;

}