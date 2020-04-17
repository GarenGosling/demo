# ReentrantLock 与 锁

## ReentrantLock(可重入锁)和synchronized区别
    
* 可重入性

* 锁的实现

* 性能的区别

* 功能区别
    
    
## ReentrantLock独有的功能

* 可指定是公平锁还是非公平锁

* 提供一个Condition类，可以分组唤醒需要唤醒的线程

* 提供能够中断等待锁的线程的机制，lock.lockInterruptibly()


## ReentrantReadWriteLock

* 没有任何读锁的时候才能够获取写入锁

* 当读很多的情况下，写入很少的时候，使用这个类，可能会遭遇线程饥饿


## StampedLock

* 乐观锁

* 加锁返回版本号，解锁传入版本号

* 不可重入锁


## 锁的选择

* JVM层面的锁 (不需要主动释放锁，使用简单，内部实现不可见)
    * synchronized 只有少量竞争者

* 对象层面的锁 (需要主动释放锁，内部实现可见)
    * ReentrantLock 
        竞争者不少，线程增长趋势是可以预知的
    * ReentrantReadWriteLock
    
    * StampedLock