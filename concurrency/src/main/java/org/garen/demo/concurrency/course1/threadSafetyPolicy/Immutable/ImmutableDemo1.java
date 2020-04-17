package org.garen.demo.concurrency.course1.threadSafetyPolicy.Immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>
 * 功能描述 : final 修饰变量
 *          1、基本数据类型变量(初始化之后不能修改)
 *          2、引用类型变量(初始化之后不能指向另一个对象)
 * </p>
 *
 * @author : Garen Gosling 2020/4/13 上午11:09
 */
@Slf4j
public class ImmutableDemo1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2;    // 不允许修改
//        b = "3";  // 不允许修改
//        map = Maps.newHashMap();  // 不允许修改
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    private void test(final int a) {
//        a = 1;    // 不允许修改
    }

}
