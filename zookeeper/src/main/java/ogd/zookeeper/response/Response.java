package ogd.zookeeper.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


/**
 * <p>
 * 功能描述：响应对象
 * </p>
 *
 * @author         Garen Gosling    2019/4/8 下午3:47
 * @version        v1.1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response implements Serializable {
    /**
     * <p>
     * 功能描述: 状态码
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:47
     * @since   v1.1.0
     * @see     CodeEnum#code()
     */
    private Integer code;

    /**
     * <p>
     * 功能描述: 状态码对应的信息
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:49
     * @since   v1.1.0
     * @see     CodeEnum#msg()
     */
    private String message;

    /**
     * <p>
     * 功能描述:
     *      1、code==OK, 即：请求成功时，保存业务对象
     *      2、code!=OK, 即：请求异常时，保存报错信息
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:49
     * @since   v1.1.0
     * @see     com.bonade.hatch.base.response.MyGlobalExceptionHandler
     */
    private Object data;
}