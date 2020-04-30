package ogd.concurrency.course1.threadSafetyPolicy.Immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import ogd.concurrency.annotation.ThreadSafe;

import java.util.List;

/**
 * <p>
 * 功能描述 : java中的不可变对象
 *           Guava : ImmutableXXX : Collection、List、Set、Map ...
 * </p>
 *
 * @author : Garen Gosling 2020/4/13 上午11:32
 */
@ThreadSafe
public class ImmutableDemo3 {
    private final static ImmutableList list = ImmutableList.of(1, 2, 3);
    private final static List<Integer> list2 = ImmutableList.of(1, 2, 3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();
    public static void main(String[] args) {
//        list.add(4);
//        list2.add(4);
//        set.add(4);
//        map.put(1, 4);
//        map2.put(1, 4);
        System.out.println(map2.get(3));
    }
}