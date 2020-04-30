package ogd.zookeeper.response;

/**
 * <p>
 * 功能描述：响应状态码
 * </p>
 *
 * @author         Garen Gosling    2019/4/8 下午3:24
 * @version        v1.1.0
 */
public enum CodeEnum {
    /**
     * <p>
     * 功能描述: 请求成功
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:25
     * @since   v1.1.0
     */
    OK(1, "OK"),
    /**
     * <p>
     * 功能描述: 请求参数异常，通常可以直接在前端显示异常信息，已提示用户操作。如：手机号码不能为空
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:25
     * @since   v1.1.0
     */
    BAD_REQUEST_EXCEPTION(2, "请求参数异常"),
    /**
     * <p>
     * 功能描述: 业务异常，通常可以直接在前端显示异常信息，已提示用户操作。如：原密码错误
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:26
     * @since   v1.1.0
     */
    BUSINESS_EXCEPTION(3, "业务异常"),
    /**
     * <p>
     * 功能描述: 系统异常，建议不要直接在前端显示异常信息，这个信息返回给前端，是为了在开发者模式下，
     *          开发人员，方便的查看报错信息，以分析错误原因用的。如：抢单模式的任务没有设置最大可抢单数量
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:28
     * @since   v1.1.0
     */
    SYSTEM_EXCEPTION(4, "系统异常"),
    /**
     * <p>
     * 功能描述: 程序报错，建议不要直接在前端显示异常信息，这个信息返回给前端，是为了在开发者模式下，
     *          开发人员，方便的查看报错信息，以分析错误原因用的。如：java.lang.NullPointerException
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:34
     * @since   v1.1.0
     */
    SYSTEM_ERROR(5, "程序报错");



    private final int code;
    private final String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.message;
    }
}
