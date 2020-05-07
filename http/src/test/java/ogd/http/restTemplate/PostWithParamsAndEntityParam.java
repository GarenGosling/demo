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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

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
public class PostWithParamsAndEntityParam extends Base {

    @Autowired
    private RestTemplate restTemplate;

    private static String url;

    static {
        try {
            url = PATH_POST_WITH_PARAMS_AND_ENTITY_PARAM + "?name=" + URLEncoder.encode("Garen Gosling", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static Hello h = new Hello(1, "hello http");

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
        ResponseEntity<Map> mapResponseEntity = restTemplate.postForEntity(url, h, Map.class);
        log.info("status code: {}", mapResponseEntity.getStatusCode());
        log.info("status code value: {}", mapResponseEntity.getStatusCodeValue());
        log.info("body: {}", mapResponseEntity.getBody());
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
        Map map = restTemplate.postForObject(url, h, Map.class);
        log.info("hello: {}", map);
    }

}
