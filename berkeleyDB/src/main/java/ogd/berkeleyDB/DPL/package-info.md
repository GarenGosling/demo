# DPL示例

为了说明DPL的用法，我们在本章中提供一个完整的工作示例。

## config 配置包

* DataAccessor 数据存取器
    * 一级索引，角色表
    * 一级索引，用户表
    * 二级索引，用户表，字段：姓名
    * 二级索引，用户表，字段：角色ID
    
* MyDbEnv 
    * 环境对象初始化
    * 仓库对象初始化
    * 关闭（仓库、环境）方法
   
## entity 实体包

* Role 角色
    * @PrimaryKey id
* User 用户
    * @PrimaryKey id
    * @SecondaryKey(relate=MANY_TO_ONE) name
    * @SecondaryKey(relate=MANY_TO_ONE) roleId
    
## use 使用包

* SaveToDB 保存到数据库
    * 持久化角色数据
    * 持久化用户数据

* GetFromDB 从数据库查询    
    * 查询全部角色
    * 查询全部用户
    * 通过姓名查用户
    * 通过角色ID查用户