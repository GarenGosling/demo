# 线程池

## new Thread 弊端

* 每次 new Thread 新建对象，性能差

* 线程缺乏统一管理，可能无限制的新建线程，相互竞争，有可能占用过多系统资源导致死机或OOM

* 缺少更多功能，如更多执行、定期执行、线程中断


## 线程池的好处

* 重用存在的线程，减少对象创建、消亡的开销，性能佳

* 可有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多资源竞争，避免阻塞

* 提供定时执行、定期执行、单线程、并发数控制等功能


## 线程池 - ThreadPoolExecutor

### ThreadPoolExecutor 参数

* corePoolSize : 核心线程数量

* maximumPoolSize : 最大线程数数量

* workQueue : 阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生重大影响

* keepAliveTime : 线程没有任务执行时最多保持多久时间终止

* unit : keepAliveTime的时间单位

* threadFactory : 线程工厂，用来创建线程

* rejectHandler : 当拒绝处理任务时的策略

### ThreadPoolExecutor 状态

* RUNNING
    * 可以接受新提交的任务，也能处理阻塞队列中的任务

* SHUTDOWN
    * RUNNING [shutdown()] -> SHUTDOWN
    * 不能接受新提交的任务，但可以处理阻塞队列中的任务

* STOP
    * RUNNING [shutdownNow()] -> STOP
    * 不能接受新提交的任务，也不能处理阻塞队列中的任务

* TIDYING
    * SHUTDOWN [阻塞队列为空，线程池中的工作线程数量为0] -> TIDYING 
    * STOP [线程池中的工作栈数量为0] -> TIDYING 

* TERMINATED
    * TIDYING [terminated()]-> TERMINATED
    
### ThreadPoolExecutor 方法

* execute() : 提交任务，交给线程池执行

* submit() : 提交任务，能够返回执行结果 execute + Future

* shutdown() : 关闭线程池，等待任务都执行完

* shutdownNow() : 关闭线程池，不等待任务执行完

* getTaskCount() : 线程池已执行和未执行的任务总数

* getCompletedTaskCount() : 已完成的任务数量

* getPoolSize() : 线程池当前的线程数量

* getActiveCount() : 当前线程池中正在执行任务的线程数量


## 线程池 - Executor 框架接口

* Executors.newCachedThreadPool

* Executors.newFixedThreadPool

* Executors.newScheduledThreadPool

* Executors.newSingleThreadExecutor


## 线程池 - 合理配置

* CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU + 1

* IO密集型任务，参考值可以设置为 2 * NCPU


