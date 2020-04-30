package ogd.concurrency.course1.threadSafetyPolicy.threadLocal;

import ogd.concurrency.annotation.ThreadSafe;

/**
 * <p>
 * 功能描述 : threadLocal 线程封闭
 * </p>
 *
 * @author : Garen Gosling 2020/4/13 下午2:55
 */
@ThreadSafe
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    /**
     * <p>
     * 功能描述 : 在filter里存值
     * </p>
     *
     * @author : Garen Gosling   2020/4/13 下午3:41
     *
     * @param id 线程id
     * @Return void
     **/
    public static void add(Long id) {
        requestHolder.set(id);
    }

    /**
     * <p>
     * 功能描述 : 接口中随时取用
     * </p>
     *
     * @author : Garen Gosling   2020/4/13 下午3:41
     *
     * @param
     * @Return java.lang.Long
     **/
    public static Long getId() {
        return requestHolder.get();
    }

    /**
     * <p>
     * 功能描述 : 拦截器，最后要关闭
     * </p>
     *
     * @author : Garen Gosling   2020/4/13 下午3:42
     *
     * @param
     * @Return void
     **/
    public static void remove() {
        requestHolder.remove();
    }
 }
