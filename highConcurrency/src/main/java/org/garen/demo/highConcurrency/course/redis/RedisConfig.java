package org.garen.demo.highConcurrency.course.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 * 功能描述 : redis配置
 * </p>
 *
 * @author : Garen Gosling 2020/4/16 下午3:10
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("${redis.host}") String host,
                               @Value("${redis.port}") int port,
                               @Value("${redis.password}") String password,
                               @Value("${redis.database}") int database) {
        return new JedisPool(new GenericObjectPoolConfig(), host, port, 10000, password, database);
    }

}
