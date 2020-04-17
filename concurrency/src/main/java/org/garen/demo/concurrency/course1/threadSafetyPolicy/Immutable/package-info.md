# 不可变对象

* 对象创建以后其状态不能修改

* 对象所有域都是final类型

* 对象是正确创建（在对象创建期间，this引用没有溢出）


## final 关键字 : 类、方法、变量

* 修饰类 : 不能被继承 (所有成员方法隐式指定成final方法)

* 修饰方法 : 1、锁定方法不被继承类修改
            2、效率 (早起，final方法转成内嵌调用，但是如果方法过于庞大，将看不到内嵌调用的任何性能提升，在最近的版本中不需要用final来进行优化了)
            3、private方法会被隐式被指定为final方法         

* 修饰变量 : 1、基本数据类型变量(初始化之后不能修改)
            2、引用类型变量(初始化之后不能指向另一个对象)
            
            
## java中其他的不可变对象

* Collections.unmodifiableXX : Collection、List、set、Map ...

* Guava : ImmutableXXX : Collection、List、Set、Map ...
            
