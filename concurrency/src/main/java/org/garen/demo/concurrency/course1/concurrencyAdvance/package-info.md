# 多线程并发最佳实践

* 使用本地变量

* 使用不可变类

* 最小化锁的作用域范围：S = 1/(1-a+a/n)

* 使用线程池的Executor，而不是直接new Thread执行

* 宁可使用同步也不要使用线程的wait和notify

* 使用BlockingQueue实现生产-消费模式

* 使用并发集合而不是加了锁的同步集合

* 使用Semaphore创建有界的访问

* 宁可使用同步代码块，也不使用同步的方法

* 避免使用静态变量


# Spring与线程安全

* Spring bean : singleton、 prototype

* 无状态对象


# HashMap 与 ConcurrentHashMap

* HashMap 在多线程环境下扩容，容易产生死循环

* concurrentHashMap在1.7前为了增加并发度，引入了Segment，segment里是链表数组，继承ReentrantLockLock使用分段锁，最大并发数==segment数

* concurrentHashMap在1.8废弃了分段锁，直接使用大数组，超过8，链表换成红黑树，提高并发数

# 多线程并发与线程安全总结



