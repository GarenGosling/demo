package ogd.concurrency.course1.threadSafetyPolicy.syncContainer;

import ogd.concurrency.annotation.NotThreadSafe;

import java.util.Vector;

/**
 * <p>
 * 功能描述 : vector的线程不安全的写法
 * </p>
 *
 * @author : Garen Gosling 2020/4/14 上午10:10
 */
@NotThreadSafe
public class VectorDemo2 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            });

            thread1.start();
            thread2.start();
        }
    }
}
