# AbstractQueuedSynchronizer - AQS (JUC的核心)

* 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架

* 利用了一个int类型表示状态

* 使用方法是继承

* 子类通过继承并通过实现它的方法管理其状态{ acquire 和 release }的方法操纵状态
 
* 可以同时实现排它锁和同享锁模式（独占、共享）

## AQS同步组件

* CountDownLatch

* Semaphore

* CyclicBarrier

* ReentrantLock

* Condition

* FutureTask


## FutureTask

* Callable 与 Runnable 接口对比

* Future 接口

* FutureTask 类


## Fork/Join框架

大任务分隔成若干小任务，最后汇总


## BlockingQueue 阻塞队列

* 插入一个满了的队列，会阻塞，直到有对象从对象取出

* 从一个空队列取值，会阻塞，直到有对象进入队列

### BlockingQueue 的4套方法

* Throws Exception 
    不能马上执行时，抛出异常
    * Insert 
        add(o)
    * Remove
         remove(o)
    * Examine
        element()
    
* Special Value
    不能马上执行时，返回特殊值（true/false）
    * Insert 
        offer(o)
    * Remove
         poll()
    * Examine
        peek()

* Blocks
    不能马上执行时，阻塞
    * Insert 
        put(o)
    * Remove
         take()
        
* Times Out
    不能马上执行时，阻塞指定时间，还没有执行则返回特殊值（true/false）
    * Insert 
        offer(o, timeout, timeunit)
    * Remove
         poll(timeout, timeunit)
         
### BlockingQueue 实现类

* ArrayBlockingQueue
    有界的阻塞队列，先进先出

* DelayQueue
    阻塞内部元素，元素需要排序，过期优先级排序
    
* LinkedBlockingQueue
    指定就有边界，不指定就无边界，先进先出
    
* PriorityBlockingQueue
    无边界
    
* SynchronousQueue
    同步队列，无界非缓存的队列