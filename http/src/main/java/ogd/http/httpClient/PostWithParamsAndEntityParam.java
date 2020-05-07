package ogd.http.httpClient;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ogd.http.entity.Base;
import ogd.http.entity.Hello;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <p>
 * 功能描述 : 有普通参数和对象参数的POST请求
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 下午5:21
 */
@Slf4j
public class PostWithParamsAndEntityParam extends Base {

    private static void send() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建对象参数
        Hello hello = new Hello(1, "hello http");
        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(hello), "UTF-8");
        // 响应模型
        CloseableHttpResponse response = null;
        // 创建Post请求
        HttpPost httpPost = null;

        try {
            httpPost = new HttpPost(PATH_POST_WITH_PARAMS_AND_ENTITY_PARAM + "?name=" + URLEncoder.encode("Garen Gosling", "utf-8"));
            // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
            httpPost.setEntity(entity);
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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

