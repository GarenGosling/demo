package org.garen.demo.highConcurrency.course.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * <p>
 * 功能描述 : redis客户端
 *          http://redis.cn/
 * </p>
 *
 * @author : Garen Gosling 2020/4/16 下午3:16
 */
@Component
public class RedisClient {

    @Resource(name = "redisPool")
    private JedisPool jedisPool;

    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

}
