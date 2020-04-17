package org.garen.demo.concurrency.course1.threadSafetyPolicy.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * <p>
 * 功能描述 : Vector 单线程用foreach、迭代器循环过程中删除元素，会报错 java.util.ConcurrentModificationException
 * </p>
 *
 * @author : Garen Gosling 2020/4/14 上午10:30
 */
public class VectorExamples {

    // java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1) {
        for(Integer i : v1){
            if(i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1) {
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    // success
    private static void test3(Vector<Integer> v1) {
        for (int i = 0; i < v1.size(); i++) {
            if(v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test1(vector);
    }

}
