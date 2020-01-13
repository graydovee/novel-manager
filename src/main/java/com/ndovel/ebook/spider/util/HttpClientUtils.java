package com.ndovel.ebook.spider.util;

import com.ndovel.ebook.exception.RequestException;
import com.ndovel.ebook.utils.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class HttpClientUtils {

    private HttpClientUtils() {

    }


    // 连接管理器
    private static PoolingHttpClientConnectionManager pool;

    // 请求配置
    private static RequestConfig requestConfig;

    static {

        try {
            // 初始化HttpClientTest开始
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register(
                    "http", PlainConnectionSocketFactory.getSocketFactory()).register(
                    "https", sslsf).build();
            // 初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(
                    socketFactoryRegistry);
            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
            pool.setMaxTotal(200);
            // 设置最大路由
            pool.setDefaultMaxPerRoute(2);
            // 根据默认超时限制初始化requestConfig
            int socketTimeout = 10000;
            int connectTimeout = 10000;
            int connectionRequestTimeout = 10000;
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
                    connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(
                    connectTimeout).build();

            // "初始化HttpClientTest结束"
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }


        // 设置请求超时时间
        requestConfig = RequestConfig
                .custom()
                .setSocketTimeout(50000)
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(50000)
                .build();
    }

    private static CloseableHttpClient getHttpClient() {

        CloseableHttpClient httpClient;
        httpClient = HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(pool)
                // 设置请求配置
                .setDefaultRequestConfig(requestConfig)
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();

        return httpClient;
    }


    public static String get(String url) throws RequestException {
        return sendHttp(new HttpGet(url));
    }

    public static byte[] getByteArray(String url) throws RequestException {
        return requestByteArray(new HttpGet(url));
    }

    public static String post(String url) throws RequestException {
        return sendHttp(new HttpPost(url));
    }


    /**
     * 进行请求
     * @param consumer 对请求到的数据进行操作
     * @throws RequestException 请求异常
     */
    private static void request(HttpRequestBase http, Consumer<HttpEntity> consumer) throws RequestException {
        CloseableHttpClient httpClient = getHttpClient();

        // 配置请求信息
        http.setConfig(requestConfig);

        //设置请求头
        http.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36");

        // 执行请求
        try (CloseableHttpResponse response = httpClient.execute(http)) {
            // 得到响应实例
            HttpEntity entity = response.getEntity();

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                consumer.accept(entity);
            } else {
                throw new RequestException(
                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
            }
            EntityUtils.consume(entity);

        } catch (Exception e) {
            throw new RequestException(e);
        }
    }

    /**
     * 请求二进制流
     */
    private static byte[] requestByteArray(HttpRequestBase http) throws RequestException {
        Solution solution = new Solution();
        request(http, (httpEntity -> {
            try {
                solution.setByteArray(EntityUtils.toByteArray(httpEntity));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }));
        return solution.getByteArray();
    }

    public static String getRedirectUrl(String url) throws RequestException {
        HttpGet http = new HttpGet(url);
        CloseableHttpClient httpClient = getHttpClient();

        // 配置请求信息
        http.setConfig(requestConfig);

        //设置请求头
        http.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36");

        // 执行请求
        try (CloseableHttpResponse response = httpClient.execute(http)) {
            Header[] allHeaders = response.getAllHeaders();
            for (Header allHeader : allHeaders) {
                if (allHeader.getName().equals("Location")) {
                    return allHeader.getValue();
                }
            }
        } catch (IOException e) {
            throw new RequestException(e);
        }
        return null;
    }

    /**
     * 请求html字符串
     */
    private static String sendHttp(HttpRequestBase http) throws RequestException {
        Solution solution = new Solution();
        request(http, httpEntity -> {
            ContentType type = ContentType.get(httpEntity);
            Charset charset = null;
            if (type != null) {
                charset = type.getCharset();
            }
            try {
                solution.setByteArray(EntityUtils.toByteArray(httpEntity));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return;
            }
            if (charset == null){
                charset = StandardCharsets.UTF_8;
                solution.setString(new String(solution.getByteArray(), charset));

                Charset charSetByBody = getCharSetByBody(solution.getString());
                if (charSetByBody != null && !charSetByBody.equals(charset)){
                    solution.setString(new String(solution.getByteArray(), charSetByBody));
                }
            } else {
                solution.setString(new String(solution.getByteArray(), charset));
            }
        });
        return solution.getString();
    }

    /**
     * 获取在html头部<meta/>标签内的编码信息
     * @param html html字符串
     * @return 编码信息
     */
    private static Charset getCharSetByBody(String html) {
        String charset = null;
        Document document = Jsoup.parse(html);
        Elements elements = document.select("meta");
        for (Element metaElement : elements) {
            if (metaElement != null && !StringUtils.isEmpty(metaElement.attr("http-equiv")) && metaElement.attr("http-equiv").toLowerCase().equals("content-type")) {
                String content = metaElement.attr("content");
                charset = null;

                String regex = ".*charset=([^;]*).*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(content);
                if (matcher.find())
                    charset = matcher.group(1);
                break;
            }
        }
        return charset == null ? null : Charset.forName(charset);
    }



    @Data
    private static class Solution {
        private byte[] byteArray;
        private String string;
    }

}