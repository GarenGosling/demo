package ogdd.sessionDistributed.noRepeatSubmit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 功能描述 : 禁止重复提交 AOP
 * </p>
 *
 * @author : Garen Gosling 2020/4/27 下午6:02
 */
@Aspect
@Configuration
public class NoRepeatSubmitAOP {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 拼接符号
     */
    private static String JOINT_SYMBOL = "#";

    /**
     * <p>
     * 功能描述 : 定义切入点，切入点为：有 @NoRepeatSubmitAnnotation 注解标注的方法
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:23
     *
     * @param
     * @Return void
     **/
    @Pointcut("execution(public * *(..)) && @annotation(ogdd.sessionDistributed.noRepeatSubmit.NoRepeatSubmitAnnotation)")
    public void myPointcut(){}

    /**
     * <p>
     * 功能描述 : 环绕通知
     *
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:23
     *
     * @param proceedingJoinPoint aop对象
     * @Return java.lang.Object
     **/
    @Around(value = "myPointcut()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String cacheKey = getCacheKey(proceedingJoinPoint); // 获取缓存key
        noRepeatSubmit(cacheKey);    // 禁止重复提交
//        Thread.sleep(2000);     // 测试用，真正使用的时候要删除 TODO
        Object o = proceedingJoinPoint.proceed();   // 执行方法
        stringRedisTemplate.delete(cacheKey);  // 删除缓存
        return o;
    }

    /**
     * <p>
     * 功能描述 : 禁止重复提交
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:39
     *
     * @param cacheKey 缓存key
     * @Return void
     **/
    private void noRepeatSubmit(String cacheKey) {
        synchronized (this) {   // 从redis中取值、判断、存值的过程，有并发安全问题，因此加锁
            String value = stringRedisTemplate.opsForValue().get(cacheKey); // redis中取值
            if(!StringUtils.isEmpty(value)) throw new RuntimeException("请勿重复提交");
            stringRedisTemplate.opsForValue().set(cacheKey, "0", 10L, TimeUnit.MINUTES);    // 过期时间：10分钟
        }
    }

    /**
     * <p>
     * 功能描述 : 获取缓存key 【模块名#全类名#方法名#sessionId】
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:56
     *
     * @param proceedingJoinPoint aop对象
     * @Return java.lang.String
     **/
    private String getCacheKey(ProceedingJoinPoint proceedingJoinPoint) {
        StringBuilder sb = new StringBuilder(); // 拼串对象
        jointModuleName(sb);    // 拼接【模块名】
        jointFullClassName(sb, proceedingJoinPoint);    // 拼接【全类名（包名.类名）】
        jointMethodName(sb, proceedingJoinPoint);   // 拼接【方法名】
        jointSessionId(sb); // 拼接【sessionId】
        String key = sb.toString();
        if(StringUtils.isEmpty(key)){
            throw new RuntimeException("cache key is empty!");
        }
        return key;
    }

    /**
     * <p>
     * 功能描述 : sb 拼接上模块名（或项目名，父子工程格式：parent.child）
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:46
     *
     * @param sb StringBuilder对象
     * @Return void
     **/
    private void jointModuleName(StringBuilder sb) {
        sb.append("demo.distributed.sessionDistributed");
    }

    /**
     * <p>
     * 功能描述 : sb 拼接上全类名（包名.类名）
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:53
     *
     * @param proceedingJoinPoint aop对象
     * @param sb StringBuilder对象
     * @Return void
     **/
    private void jointFullClassName(StringBuilder sb, ProceedingJoinPoint proceedingJoinPoint) {
        sb.append(JOINT_SYMBOL);
        sb.append(proceedingJoinPoint.getSignature().getDeclaringTypeName());
    }

    /**
     * <p>
     * 功能描述 : sb 拼接上方法名
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:53
     *
     * @param proceedingJoinPoint aop对象
     * @param sb StringBuilder对象
     * @Return void
     **/
    private void jointMethodName(StringBuilder sb, ProceedingJoinPoint proceedingJoinPoint) {
        sb.append(JOINT_SYMBOL);
        sb.append(proceedingJoinPoint.getSignature().getName());
    }

    /**
     * <p>
     * 功能描述 : sb 拼接上 sessionId
     * </p>
     *
     * @author : Garen Gosling   2020/4/29 下午4:21
     *
     * @param sb StringBuilder对象
     * @Return void
     **/
    private void jointSessionId(StringBuilder sb) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String sessionId = request.getRequestedSessionId();
        sb.append(JOINT_SYMBOL);
        sb.append(sessionId);
    }

}
