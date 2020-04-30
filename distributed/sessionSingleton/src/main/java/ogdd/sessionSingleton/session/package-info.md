# 单独的session

   用来测试分布式session，作为对比用。要证明的是：这个项目部署2个server，负载均衡2个点，每次拿到的sessionId是不一样的；而分布式session也部署两个点，负载均衡，每次拿到的sessionId是一样的。