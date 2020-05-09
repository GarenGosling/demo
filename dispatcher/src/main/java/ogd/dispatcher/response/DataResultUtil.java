package ogd.dispatcher.response;


/**
 * @author jinpin
 * @date 2019-12-02 12:11
 */
public class DataResultUtil {

    /**
     * @return 操作成功响应对象
     */
    public static DataResult ofSuccess() {
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, "操作成功");
    }

    /**
     * @return 操作成功响应对象(带返回体)
     */
    public static DataResult ofSuccess(Object o) {
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, o);
    }

}
