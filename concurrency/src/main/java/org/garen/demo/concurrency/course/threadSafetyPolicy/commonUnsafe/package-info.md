# 线程不安全类与写法

线程不安全的类：如果一个类的对象可以同时可以被多个线程访问，如果不做同步或并发处理，那么它很容易表现出线程不安全的现象，比如：抛出异常、逻辑处理错误等等，这样的类就叫做线程不安全的类。

* StringBuilder -> StringBuffer

* SimpleDateFormat -> JodaTime

* ArrayList, HashSet, HashMap 等 Collections
