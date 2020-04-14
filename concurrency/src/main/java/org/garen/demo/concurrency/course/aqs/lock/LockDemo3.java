package org.garen.demo.concurrency.course.aqs.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 功能描述 : 锁 - ReentrantReadWriteLock
 *          没有任何读锁的时候才能够获取写入锁
 *          当读很多的情况下，写入很少的时候，使用这个类，可能会遭遇线程饥饿
 * </p>
 *
 * @author : Garen Gosling 2020/4/9 上午10:14
 */
@Slf4j
public class LockDemo3 {

    private final Map<String, Data> map = new TreeMap<>();

    private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            readLock.unlock();
        }
    }

    class Data {

    }
}
