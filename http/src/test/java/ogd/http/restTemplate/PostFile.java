package ogd.http.restTemplate;

import lombok.extern.slf4j.Slf4j;
import ogd.http.entity.Base;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
public class PostFile extends Base {

    @Autowired
    private RestTemplate restTemplate;

    private static String url;

    static {
        try {
            url = PATH_FILE + "?name=" + URLEncoder.encode("hello http", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private HttpEntity<MultiValueMap<String, Object>> getFiles() {
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource("/Users/liuxueliang/A-Box/demo/http/src/main/resources/1.jpg");
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        form.add("filename", "1.jpg");

        //用HttpEntity封装整个请求报文
        return new HttpEntity<>(form, headers);
    }

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
        HttpEntity<MultiValueMap<String, Object>> files = getFiles();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, files, String.class);
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
        HttpEntity<MultiValueMap<String, Object>> files = getFiles();
        String s = restTemplate.postForObject(url, files, String.class, "1.jpg");
        log.info("s: {}", s);
    }

}
