# 线程安全 - 同步容器

* ArrayList -> Vector, Stack

* HashMap -> HashTable(key、value不能为null)

* Collections.synchronizedXXX(List、Set、Map)

## 同步容器的缺点

* 同步容器用synchronized关键字，影响性能

* 不能完全做到线程安全
