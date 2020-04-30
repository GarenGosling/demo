package ogd.zookeeper.response;

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
     * 功能描述: 捕获请求参数异常
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:53
     * @since   v1.1.0
     */
	@ExceptionHandler(value = BadRequestException.class)
	@ResponseBody
	public Response BadRequestExceptionHandler(Exception e) {
		e.printStackTrace();
		return new Response(CodeEnum.BAD_REQUEST_EXCEPTION.code(), CodeEnum.BAD_REQUEST_EXCEPTION.msg(), e.getMessage());
	}

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
    public Response BusinessExceptionHandler(Exception e) {
    	e.printStackTrace();
        return new Response(CodeEnum.BUSINESS_EXCEPTION.code(), CodeEnum.BUSINESS_EXCEPTION.msg(), e.getMessage());
    }

    /**
     * <p>
     * 功能描述: 捕获系统异常
     * </p>
     *
     * @author  GarenGosling    2019/4/8 下午3:54
     * @since   v1.1.0
     */
	@ExceptionHandler(value = SystemException.class)
	@ResponseBody
	public Response SystemExceptionHandler(Exception e) {
		e.printStackTrace();
		return new Response(CodeEnum.SYSTEM_EXCEPTION.code(), CodeEnum.SYSTEM_EXCEPTION.msg(), e.getMessage());
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
	public Response SystemErrorHandler(Exception e) {
		e.printStackTrace();
		return new Response(CodeEnum.SYSTEM_ERROR.code(), CodeEnum.SYSTEM_ERROR.msg(), e.getMessage());
	}

}
