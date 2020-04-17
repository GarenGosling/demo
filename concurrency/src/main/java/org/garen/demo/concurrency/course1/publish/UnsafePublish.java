package org.garen.demo.concurrency.course1.publish;

import lombok.extern.slf4j.Slf4j;
import org.garen.demo.concurrency.annotation.NotThreadSafe;

import java.util.Arrays;

/**
 * <p>
 * 功能描述 : 不安全的对象发布
 *
 * 通过public方法得到私有域states，如果其他线程真正使用这个对象的时候，
 * states中的变量是不能被确定了，因此这样发布对象的方式是不安全的发布对象
 * 的方式，因为我们无法假设其他线程是否对这个域修改，从而造成状态的错误
 *
 * </p>
 *
 * @author : Garen Gosling 2020/4/10 下午5:05
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }

}
