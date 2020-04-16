# 线程安全 - 并发容器J.U.C

* Array -> CopyOnWriteArrayList

* HashSet -> CopyOnWriteArraySet
* TreeSet -> ConcurrentSkipListSet

* HashMap -> ConcurrentHashMap
    * 不允许空值
    * 度操作做了优化，并发性特别好 
* TreeMap -> ConcurrentSkipListMap
    * key有序
    * 存取时间是和线程数没关系的，并发数越高越能提现出优势来

## J.U.C

* tools

* locks

* atomic

* collections

* executor


## 安全共享对象策略 - 总结

* 线程安全对象：一个线程安全的对象或者容器，在内部通过同步机制来保证线程安全，所以其他线程无需额外的同步就可以通过公共接口随意访问它

* 被守护对象：被守护对象只能通过获取特定的锁来访问
