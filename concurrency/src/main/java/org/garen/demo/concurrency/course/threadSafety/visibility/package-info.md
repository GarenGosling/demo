# 可见性


## 导致共享变量在线程间不可见的原因

* 线程交叉执行

* 重排序结合线程交叉执行

* 共享变量更新后的值没有在工作内存与主存间及时更新


## 可见性 - synchronized

JMM关于synchronized的两条规定

* 线程解锁前，必须把共享变量的最新值刷新到主内存

* 线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值（注意：加锁与解锁需要是同一把锁） 


## 可见性 - volatile

* 对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地内存中的共享变量值刷新到主内存

* 对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量 


## volatile适用场景

要在多线程中安全的使用volatile变量，必须同时满足： 

* 对变量的写入操作不依赖其当前值 

* 该变量没有包含在具有其他变量的不变式中 


## synchronized和volatile的比较 

* volatile不需要加锁，比synchronized更轻量级，不会阻塞线程 

* 从内存可见性角度讲，volatile读操作=进入synchronized代码块（加锁），volatile写操作=退出synchronized代码块（解锁） 

* synchronized既能保证可见性，又能保证原子性，而volatile只能保证可见性，不能保证原子性