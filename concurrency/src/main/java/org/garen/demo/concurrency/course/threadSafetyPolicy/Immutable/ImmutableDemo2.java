package org.garen.demo.concurrency.course.threadSafetyPolicy.Immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * <p>
 * 功能描述 : java中的不可变对象
 *          Collections.unmodifiableXX : Collection、List、set、Map ...
 * </p>
 *
 * @author : Garen Gosling 2020/4/13 上午11:09
 */
@Slf4j
public class ImmutableDemo2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

}
