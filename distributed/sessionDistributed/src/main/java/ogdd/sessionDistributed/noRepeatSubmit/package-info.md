# 禁止重复提交解决方案

依赖于分布式session实现

## 引入 AOP 的 jar 包

    <!-- aop -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>
    
    
## 自定义注解

    @NoRepeatSubmitAnnotation
    
    
## 禁止重复提交 AOP

* 定义切入点
    
    切入点为：有 @NoRepeatSubmitAnnotation 注解标注的方法

* 获取缓存key 

    模块名#全类名#方法名#sessionId

* 禁止重复提交

    * 从redis中取值、判断、存值的过程，有并发安全问题，因此加锁
    
    * redis中取值
        
    * 如果取到值，抛出异常：请勿重复提交
    
    * 否则，redis中存值
    
* 执行方法

* 删除缓存


## 测试接口

    * 禁止重复提交
        
        controller 方法贴上 @NoRepeatSubmitAnnotation 注解
        
    * 允许重复提交

        controller 方法不贴上 @NoRepeatSubmitAnnotation 注解