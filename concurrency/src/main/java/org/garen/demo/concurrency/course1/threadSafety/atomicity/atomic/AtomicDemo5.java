package org.garen.demo.concurrency.course1.threadSafety.atomicity.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.garen.demo.concurrency.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * <p>
 * 功能描述 : AtomicIntegerFieldUpdater
 *
 * 在AtomicInteger成员变量只有一个int value，似乎好像并没有多出内存，但是我们的AtomicInteger是一个对象，
 * 一个对象的正确计算应该是 对象头 + 数据大小，在64位机器上AtomicInteger对象占用内存如下：
 *
 * 关闭指针压缩： 16(对象头)+4(实例数据)=20不是8的倍数，因此需要对齐填充 16+4+4(padding)=24
 *
 * 开启指针压缩（-XX:+UseCompressedOop): 12+4=16已经是8的倍数了，不需要再padding。
 *
 * 由于我们的AtomicInteger是一个对象，还需要被引用，那么真实的占用为：
 *
 * 关闭指针压缩：24 + 8 = 32
 * 开启指针压缩: 16 + 4 = 20
 * 而fieldUpdater是staic final类型并不会占用我们对象的内存，所以使用fieldUpdater的话可以近似认为只用了4字节，
 * 这个再未关闭指针压缩的情况下节约了7倍，关闭的情况下节约了4倍，这个在少量对象的情况下可能不明显，当我们对象有几十万，
 * 几百万，或者几千万的时候，节约的可能就是几十M,几百M,甚至几个G。
 * </p>
 *
 * @author : Garen Gosling 2020/4/8 下午4:24
 */
@Slf4j
@ThreadSafe
public class AtomicDemo5 {

    @Getter
    public volatile int count = 100;

    private static final AtomicIntegerFieldUpdater<AtomicDemo5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicDemo5.class, "count");

    public static void main(String[] args) {
        AtomicDemo5 example5 = new AtomicDemo5();
        if(updater.compareAndSet(example5, 100, 120)) {
            log.info("update success 1, {}", example5.count);
        }
        if(updater.compareAndSet(example5, 100, 120)){
            log.info("update success 2, {}", example5.count);
        }else {
            log.info("update failed, {}", example5.count);
        }
    }
}
