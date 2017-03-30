package com.wish.pay.common.utils.http;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基于 httpclient 4.x版本的 httpClient工具类
 * Created by fqh on 2015/12/14.
 */
public class HttpClientUtils {

    private static PoolingHttpClientConnectionManager connManager = null;
    private static final CloseableHttpClient httpClient;
    //请求器的配置
    private static RequestConfig requestConfig;
    public static final String CHARSET = "UTF-8";
    public static final Integer CONNECTTIMEOUT = 6000;
    public static final Integer SOCKETTIMEOUT = 3000;

    static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    static {
        //超时时间设置为6秒，socket链接超时时间设置为3秒
        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(SOCKETTIMEOUT).setConnectTimeout(CONNECTTIMEOUT).build();
        //httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        httpClient = HttpConnectionManager.getHttpClient();
    }

    public static String doGet(String url, Map<String, String> params) throws Exception {
        return doGet(url, params, CHARSET);
    }


    public static String doPost(String url, Map<String, String> params) throws Exception {
        return doPost(url, params, CHARSET);
    }

    public static CloseableHttpClient getHttpClient() {
        //httpClient.getHttpConnectionManager().getParams().setTcpNoDelay(true);
        return httpClient;
    }

    /**
     * HTTP Get 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public static String doGet(String url, Map<String, String> params, String charset) throws Exception {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * HTTP Post 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public static String doPost(String url, Map<String, String> params, String charset) throws Exception {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<>(params.size());
                //去掉NameValuePair转换，这样就可以传递Map<String,Object>
                /*pairs = new ArrayList<NameValuePair>(params.size());*/
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 直接将json串post方式进行发送
     *
     * @param url
     * @param jsonStr
     * @return
     */
    public static String doPost(String url, String jsonStr) throws Exception {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new StringEntity(jsonStr, CHARSET));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param url
     * @param xmlObj
     * @return
     * @throws IOException
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static String doPost(String url, Object xmlObj) throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        //解决XStream对出现双下划线的bug
        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
        //XStream xstream = new XStream(new StaxDriver());
        xstream.autodetectAnnotations(true);
        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xstream.toXML(xmlObj);
        log.info("API，POST过去的数据是：");
        log.info(postDataXML);
        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        log.info("executing request" + httpPost.getRequestLine());
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);
        } catch (ConnectionPoolTimeoutException e) {
            log.error("http get throw ConnectionPoolTimeoutException(wait time out)");
        } catch (ConnectTimeoutException e) {
            log.error("http get throw ConnectTimeoutException");
        } catch (SocketTimeoutException e) {
            log.error("http get throw SocketTimeoutException");
        } catch (Exception e) {
            log.error("http get throw Exception");
        } finally {
            httpPost.abort();
        }
        /*result = "<xml>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[TeqClE3i0mvn3DrK]]></nonce_str>\n" +
                "   <out_refund_no_0><![CDATA[1415701182]]></out_refund_no_0>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <refund_count>1</refund_count>\n" +
                "   <refund_fee_0>1</refund_fee_0>\n" +
                "   <refund_id_0><![CDATA[2008450740201411110000174436]]></refund_id_0>\n" +
                "   <refund_status_0><![CDATA[PROCESSING]]></refund_status_0>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <sign><![CDATA[1F2841558E233C33ABA71A961D27561C]]></sign>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "</xml>";*/
        return result;
    }

    /**
     * @param url
     * @param headers
     * @param paramsMap
     * @param encoding
     * @return
     * @throws Exception
     * @Description post获取内容，带header
     */
    //TODO 有时间进行进一步通用抽象，比如将传递参数、流、文件统一抽象封装；请求类型生成统一抽象封装
    public static String doPost(String url, Header[] headers, Map<String, Object> paramsMap, String encoding) throws Exception {
        try {
            //创建请求对象
            HttpPost httpPost = new HttpPost(checkHashRestfulPlaceholder(url, paramsMap));

            //设置header信息
            httpPost.setHeaders(headers);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            //装填参数
            HttpEntity entity = null;
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            if (nvps.size() > 0) {
                entity = new UrlEncodedFormEntity(nvps, encoding);
            }

            //设置参数到请求对象中
            httpPost.setEntity(entity);

            //执行请求操作，并拿到结果（同步阻塞）
            HttpResponse resp = httpClient.execute(httpPost);

            String result = fmt2String(resp, encoding);

            //获取结果实体
            return result;

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @param url
     * @param paramsMap
     * @return
     * @Description 检测url中是否有restful风格的占位符，比如/{sessionId}/token中的sessionId，从paramsMap中替换
     */
    private static String checkHashRestfulPlaceholder(String url, Map<String, Object> paramsMap) {
        Pattern pattern = Pattern.compile("(\\{\\w+\\})");
        Matcher matcher = pattern.matcher(url);

        String resultUrl = url;

        String plaseholder = "";
        String mapKey = "";
        while (matcher.find()) {
            plaseholder = matcher.group(1);
            mapKey = plaseholder.substring(1, plaseholder.length() - 1);
            //如果有占位符Map中未get到，直接跳出这组指标规则循环继续下一组
            resultUrl = url.replace(plaseholder, String.valueOf(paramsMap.get(mapKey)));
        }
        return resultUrl;
    }

    /**
     * 转化为字符串
     *
     * @param resp     响应对象
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    private static String fmt2String(HttpResponse resp, String encoding) throws Exception {
        String body = "";
        try {
            if (resp.getEntity() != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(resp.getEntity(), encoding);
            } else {//有可能是head请求
                body = resp.getStatusLine().toString();
            }
            EntityUtils.consume(resp.getEntity());
        } catch (Exception e) {
            throw e;
        } finally {
            close(resp);
        }
        return body;
    }

    /**
     * 尝试关闭response
     *
     * @param resp HttpResponse对象
     */

    private static void close(HttpResponse resp) throws Exception {
        try {
            if (resp == null) return;
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        // String getData = doGet("http://localhost:8888/xxx/v1/init", null);
        // System.out.println("----------------------分割线1-----------------------");
        // System.out.println(getData);
       /* System.out.println("----------------------分割线2-----------------------");
        Map<String,String> paramsMaps= Maps.newHashMap();
        paramsMaps.put("tenderId","1019");
        paramsMaps.put("key", Security.md5("11"+"13"));
        paramsMaps.put("salt1","11");
        paramsMaps.put("salt2","12");
        paramsMaps.put("salt3","13");
        paramsMaps.put("salt4","14");
        String postData = doPost("http://10.0.9.253:8080/appservice/queryTenderDetail.action", paramsMaps);
        System.out.println("----------------------分割线2-----------------------");
        System.out.println(postData);
        System.out.println("----------------------分割线2-----------------------");*/
    }

}
