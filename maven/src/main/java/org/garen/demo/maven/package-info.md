# 什么是maven
maven是一个项目管理工具，它包含一个项目对象模型（POM: Project Object Model），一组标准集合，一个项目生命周期（Project Lifecycle），一个依赖管理系统（Dependency Management System），和用来运行定义在生命周期阶段（phase）中插件（plugin）目标（goal）的逻辑。

# maven能解决什么问题
* 通过pom.xml文件，编写jar包坐标就可以指向jar包，不需要再在项目中引用jar包
* 解决jar包冲突、版本冲突
* 自动化测试
* 打包

# maven的优势
传统项目jar包在项目中，maven项目jar包在jar包仓库，通过坐标指向jar包，节省空间

# 项目的一键构建
* 构建
    项目从编译、测试、运行、打包、安装、部署整个过程都交给maven管理，这个过程称为构建。
* 一键构建
    整个构建过程，使用maven一个命令可以轻松完成整个工作
    
# maven的安装
* 下载压缩包
* 配置环境变量
* 修改配置文件
    修改本地仓库地址（可选）
    
# maven仓库
* 本地仓库
* 远程仓库（私服）
* 中央仓库

# maven项目标准目录结构
* src/main/java，核心代码部分
* src/main/resources，配置文件部分
* src/test/java，测试代码部分
* src/test/resources，测试配置文件
* src/main/webapp，页面资源，js、css、图片等

# maven 命令
* 清空
    * mvn clean
* 编译
    * mvn compile
* 测试
    * mvn test
* 打包
    * mvn package
* 安装
    * mvn install
* 发布
    * mvn deploy
    
# maven 生命周期
* 清理生命周期
    * clean
* 默认生命周期
    * compile test package install deploy
* 站点生命周期    
 
