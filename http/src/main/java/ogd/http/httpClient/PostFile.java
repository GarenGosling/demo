package ogd.http.httpClient;

import lombok.extern.slf4j.Slf4j;
import ogd.http.entity.Base;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 下午5:48
 */
@Slf4j
public class PostFile extends Base {

    private static void send() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(PATH_FILE);
        CloseableHttpResponse response = null;
        try {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            // 第一个文件
            File file = new File("/Users/liuxueliang/A-Box/demo/http/src/main/resources/1.jpg");
            // 防止服务端收到的文件名乱码。 我们这里可以先将文件名URLEncode，然后服务端拿到文件名时在URLDecode。就能避免乱码问题。
            // 文件名其实是放在请求头的Content-Disposition里面进行传输的，如其值为form-data; name="files"; filename="头像.jpg"
            multipartEntityBuilder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, URLEncoder.encode(file.getName(), "utf-8"));
            // 其它参数(注:自定义contentType，设置UTF-8是为了防止服务端拿到的参数出现乱码)
            ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
            multipartEntityBuilder.addTextBody("name", "Garen Gosling", contentType);
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            log.info("HTTPS响应状态为: {}", response.getStatusLine());
            if (responseEntity != null) {
                log.info("HTTPS响应内容长度为: {}", responseEntity.getContentLength());
                // 主动设置编码，来防止响应乱码
                String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                log.info("HTTPS响应内容为: {}", responseStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        send();
    }

}


