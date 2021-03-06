package ogd.zookeeper.response;

/**
 * <p>
 * 功能描述：系统异常
 * </p>
 *
 * @author         Garen Gosling    2019/4/8 下午3:32
 * @version        v1.1.0
 */
public class SystemException extends RuntimeException {
    public SystemException(String msg) {
        super(msg);
    }
}
