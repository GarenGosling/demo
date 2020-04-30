package ogd.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 功能描述 : 课程里用来标记【推荐】的类或写法
 * </p>
 *
 * @author : Garen Gosling 2020/4/8 下午2:37
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {
    String value() default "";
}
