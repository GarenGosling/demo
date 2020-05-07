package ogd.http.restTemplate;

import lombok.extern.slf4j.Slf4j;
import ogd.http.entity.Base;
import ogd.http.entity.Hello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * 功能描述 : 无参GET请求
 * </p>
 *
 * @author : Garen Gosling 2020/5/7 上午9:57
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetNoParam extends Base {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * <p>
     * 功能描述 : getForEntity
     * </p>
     *
     * @author : Garen Gosling   2020/5/7 上午10:04
     *
     * @param
     * @Return void
     **/
    @Test
    public void send() {
        ResponseEntity<Hello> responseEntity = restTemplate.getForEntity(PATH_GET, Hello.class);
        log.info("status code: {}", responseEntity.getStatusCode());
        log.info("status code value: {}", responseEntity.getStatusCodeValue());
        log.info("body: {}", responseEntity.getBody());
    }

    /**
     * <p>
     * 功能描述 : getForObject
     * </p>
     *
     * @author : Garen Gosling   2020/5/7 上午10:05
     *
     * @param
     * @Return void
     **/
    @Test
    public void send2() {
        Hello hello = restTemplate.getForObject(PATH_GET, Hello.class);
        log.info("hello: {}", hello);
    }

}