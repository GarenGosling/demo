# 原子性


## 原子性 - Atomic包

* AtomicXXX: CAS、Unsafe.compareAndSwapInt

* AtomicLong、LongAdder

* AtomicReference、AtomicReferenceFieldUpdater

* AtomicStampReference: CAS 的 ABA 问题


## 原子性 - 锁

* synchronized : 依赖JVM

* Lock : 依赖特殊的CPU指令，代码实现，ReentrantLock

 
## 原子性 - synchronized

* 修饰代码块 : 大括号括起来的代码，作用于【调用的对象】

* 修饰方法 : 整个方法，作用于【调用的对象】

* 修饰静态方法 : 整个静态方法，作用于【所有对象】

* 修饰类 : 括号括起来的部分，作用于【所有对象】


## 原子性 - 对比

* synchronized : 不可中断锁，适合竞争不激烈，可读性好；竞争激烈时，性能迅速下降。

* Lock : 可中断锁，多样化同步，竞争激烈时能维持常态

* Atomic : 竞争激烈时能维持常态，比Lock性能好；只能同步一个值