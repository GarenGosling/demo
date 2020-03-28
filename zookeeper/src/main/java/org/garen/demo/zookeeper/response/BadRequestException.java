package org.garen.demo.zookeeper.response;

/**
 * <p>
 * 功能描述：请求参数异常
 * </p>
 *
 * @author         Garen Gosling    2019/4/8 下午3:31
 * @version        v1.1.0
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }
}
