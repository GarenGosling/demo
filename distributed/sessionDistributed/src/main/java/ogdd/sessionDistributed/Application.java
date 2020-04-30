package ogdd.sessionDistributed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * <p>
 * 功能描述 : 启动类
 * </p>
 *
 * @author : Garen Gosling 2020/4/30 上午9:49
 */
@SpringBootApplication
@EnableRedisHttpSession
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
