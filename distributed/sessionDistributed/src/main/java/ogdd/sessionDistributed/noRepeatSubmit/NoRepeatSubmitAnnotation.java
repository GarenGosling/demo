package ogdd.sessionDistributed.noRepeatSubmit;

/**
 * <p>
 * 功能描述 : 禁止重复提交注解
 *          使用方法：在需要禁止重复提交的接口，贴上@NoRepeatSubmitAnnotation即可
 * </p>
 *
 * @author : Garen Gosling 2020/4/28 下午5:24
 */
public @interface NoRepeatSubmitAnnotation {
    String name() default "name:";
}
