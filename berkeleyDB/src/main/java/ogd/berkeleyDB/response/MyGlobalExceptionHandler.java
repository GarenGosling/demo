package ogd.berkeleyDB.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 功能描述：全局异常处理类
 * </p>
 *
 * @author         Garen Gosling    2019/4/8 下午3:53
 * @version        v1.1.0
 */
@Slf4j
@ControllerAdvice
public class MyGlobalExceptionHandler {

	/**
	 * <p>
	 * 功能描述: 捕获业务异常
	 * </p>
	 *
	 * @author  GarenGosling    2019/4/8 下午3:54
	 * @since   v1.1.0
	 */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public DataResult BusinessExceptionHandler(Exception e) {
    	e.printStackTrace();
        return new DataResult<>(false, ResultEnum.RESULT_ERROR, ResultCodeEnum.RESULT_OPERATION_FAIL, e.getMessage());
    }
	
	/**
	 * <p>
	 * 功能描述: 捕获系统报错
	 * </p>
	 *
	 * @author  GarenGosling    2019/4/8 下午3:54
	 * @since   v1.1.0
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public DataResult SystemErrorHandler(Exception e) {
		e.printStackTrace();
		return new DataResult<>(false, ResultEnum.RESULT_ERROR, ResultCodeEnum.RESULT_ERROR, e);
	}

}
