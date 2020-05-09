package ogd.dispatcher.response;

/**
 * <p>
 * 功能描述：业务异常
 * </p>
 *
 * @author         Garen Gosling    2019/4/8 下午3:32
 * @version        v1.1.0
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }

}
