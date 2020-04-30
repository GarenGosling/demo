package ogd.concurrency.course1.threadSafety.atomicity.atomic;

import lombok.extern.slf4j.Slf4j;
import ogd.concurrency.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * 功能描述 : AtomicReference
 * </p>
 *
 * @author : Garen Gosling 2020/4/8 下午4:24
 */
@Slf4j
@ThreadSafe
public class AtomicDemo4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);  // 2
        count.compareAndSet(0, 1);  // no
        count.compareAndSet(1, 3);  // no
        count.compareAndSet(2, 4);  // 4
        count.compareAndSet(3, 5);  // no
        log.info("count: {}", count.get());
    }
}
