package ogd.controllers.response;

/**
 * 状态码枚举
 *
 * @author xiejinei on 2018年12月12日
 */
public class ResultCodeEnum {
    /**
     * 成功状态码
     */
    public static final Integer RESULT_SUCCESS = 10200;
    /**
     * 无权状态码
     */
    public static final Integer RESULT_UNAUTHORIZED = 10001;

    /**
     * 没登录态码
     */
    public static final Integer RESULT_NO_LOGIN = 10002;

    /**
     * 业务操作失败状态码
     */
    public static final Integer RESULT_OPERATION_FAIL = 10003;


    /**
     * 不支持方法请求状态码
     */
    public static final Integer RESULT_METHOD_NOT_SUPPORT = 10004;


    /**
     * 不支持方法请求状态码
     */
    public static final Integer RESULT_PARAMETER_ERROR = 10005;


    /**
     * 服务器异常状态码
     */
    public static final Integer RESULT_ERROR = 10006;

    /**
     * 校验异常状态码
     */
    public static final Integer VALID_ERROR = 10007;

    /**
     * 重复登录状态码
     */
    public static final Integer REPEAT_LOGIN = 10008;

}
