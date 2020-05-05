# HttpClient

HttpClient相比传统JDK自带的URLConnection，增加了易用性和灵活性，它不仅使客户端发送Http请求变得容易，而且也方便开发人员测试接口（基于Http协议的），提高了开发的效率，也方便提高代码的健壮性。因此熟练掌握HttpClient是很重要的必修内容，掌握HttpClient后，相信对于Http协议的了解会更加深入。

## org.apache.commons.httpclient.HttpClient与org.apache.http.client.HttpClient的区别

Commons的HttpClient项目现在是生命的尽头，不再被开发,  已被Apache HttpComponents项目HttpClient和HttpCore  模组取代，提供更好的性能和更大的灵活性。  

## 一、简介

HttpClient是Apache Jakarta Common下的子项目，用来提供高效的、最新的、功能丰富的支持HTTP协议的客户端编程工具包，并且它支持HTTP协议最新的版本和建议。HttpClient已经应用在很多的项目中，比如Apache Jakarta上很著名的另外两个开源项目Cactus和HTMLUnit都使用了HttpClient。

下载地址: http://hc.apache.org/downloads.cgi


## 二、特性

1. 基于标准、纯净的java语言。实现了Http1.0和Http1.1

2. 以可扩展的面向对象的结构实现了Http全部的方法（GET, POST, PUT, DELETE, HEAD, OPTIONS, and TRACE）。

3. 支持HTTPS协议。

4. 通过Http代理建立透明的连接。

5. 利用CONNECT方法通过Http代理建立隧道的https连接。

6. Basic, Digest, NTLMv1, NTLMv2, NTLM2 Session, SNPNEGO/Kerberos认证方案。

7. 插件式的自定义认证方案。

8. 便携可靠的套接字工厂使它更容易的使用第三方解决方案。

9. 连接管理器支持多线程应用。支持设置最大连接数，同时支持设置每个主机的最大连接数，发现并关闭过期的连接。

10. 自动处理Set-Cookie中的Cookie。

11. 插件式的自定义Cookie策略。

12. Request的输出流可以避免流中内容直接缓冲到socket服务器。

13. Response的输入流可以有效的从socket服务器直接读取相应内容。

14. 在http1.0和http1.1中利用KeepAlive保持持久连接。

15. 直接获取服务器发送的response code和 headers。

16. 设置连接超时的能力。

17. 实验性的支持http1.1 response caching。

18. 源代码基于Apache License 可免费获取。


## 三、使用方法

使用HttpClient发送请求、接收响应很简单，一般需要如下几步即可。

1. 创建HttpClient对象。

2. 创建请求方法的实例，并指定请求URL。如果需要发送GET请求，创建HttpGet对象；如果需要发送POST请求，创建HttpPost对象。

3. 如果需要发送请求参数，可调用HttpGet、HttpPost共同的setParams(HttpParams params)方法来添加请求参数；对于HttpPost对象而言，也可调用setEntity(HttpEntity entity)方法来设置请求参数。

4. 调用HttpClient对象的execute(HttpUriRequest request)发送请求，该方法返回一个HttpResponse。

5. 调用HttpResponse的getAllHeaders()、getHeaders(String name)等方法可获取服务器的响应头；调用HttpResponse的getEntity()方法可获取HttpEntity对象，该对象包装了服务器的响应内容。程序可通过该对象获取服务器的响应内容。

6. 释放连接。无论执行方法是否成功，都必须释放连接

