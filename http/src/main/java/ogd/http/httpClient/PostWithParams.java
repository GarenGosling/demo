package ogd.http.httpClient;

import lombok.extern.slf4j.Slf4j;
import ogd.http.entity.Base;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * <p>
 * 功能描述 : 有参POST请求
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 下午3:29
 */
@Slf4j
public class PostWithParams extends Base {

    private static void send() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 创建Post请求
            HttpPost httpPost = new HttpPost(PATH_POST_WITH_PARAMS + "?id=200&message=" + URLEncoder.encode("hello http", "utf-8"));
            // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            log.info("响应状态为: {}", response.getStatusLine());
            if (responseEntity != null) {
                log.info("响应内容长度为: {}", responseEntity.getContentLength());
                log.info("响应内容为: {}", EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
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
