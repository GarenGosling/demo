package org.garen.demo.concurrency.course.publish;

import lombok.extern.slf4j.Slf4j;
import org.garen.demo.concurrency.annotation.NotThreadSafe;

/**
 * <p>
 * 功能描述 : 对象逸出
 *
 * 内部类中有对封装实例隐含的引用，这样在对象没有被正确构造之前就被发布，
 * 有可能会有不安全因素在里面。一个导致this引用在构造期间逸出的错误
 *
 * 在对象在构造完成之前不应该发布对象
 *
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午5:12
 */
@Slf4j
@NotThreadSafe
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
