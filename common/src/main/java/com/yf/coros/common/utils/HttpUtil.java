package com.yf.coros.common.utils;

import com.alibaba.fastjson.JSON;
import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.common.exception.YfException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author yangyueming
 */
public class HttpUtil {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String UTF_8 = "utf-8";
    private static final int BODY_STEP = 2;
    private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    public static final int CONNECT_TIMEOUT = 5000;
    public static final int CONNECTION_REQUEST_TIMEOUT = 5000;
    public static final int SOCKET_TIMEOUT = 5000;
    private static HttpClientBuilder fastHttpClientBuilder =
            HttpClientBuilder.create().setDefaultRequestConfig(
                    RequestConfig.custom()
                            .setConnectTimeout(CONNECT_TIMEOUT)
                            .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                            .setSocketTimeout(SOCKET_TIMEOUT).build());

    /**
     * http connection with method of get
     *
     * @param url example: http://localhost:8084/web1/app1.jsp
     * @param fast 是否使用超时时间较短的http客户端
     * @return string with utf-8
     */
    public static String get(String url, boolean...fast) {
        return get(null, url, fast);
    }

    /**
     * http connection with method of get
     *
     * @param header http request header
     * @param url    example: http://localhost:8084/web1/app1.jsp
     * @param fast 是否使用超时时间较短的http客户端
     * @return result
     */
    public static String get(Header[] header, String url, boolean...fast) {
        HttpClient client = getHttpClient(fast);
        HttpGet g = new HttpGet(url);
        HttpResponse rep;
        HttpEntity entity;
        String text = null;
        try {
            if (null != header) {
                g.setHeaders(header);
            }
            rep = client.execute(g);
            entity = rep.getEntity();
            System.out.println("entity.getContentType(): " + entity.getContentType());
            text = EntityUtils.toString(entity, UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return text;
    }

    /**
     * http connection with method of post
     *
     * @param url   example: http://localhost:8084/web1/app1.jsp
     * @param param string array, key, value, key, value, ..., key value, example: {"name", "lizw", "company", "hyt"}
     * @param fast 是否使用超时时间较短的http客户端
     * @return string with utf-8
     */
    public static String post(String url, String[] param, boolean...fast) {
        return post(url, null, param, fast);
    }

    /**
     * http connection with method of post
     *
     * @param url   example: http://localhost:8084/web1/app1.jsp
     * @param paramMap  map
     * @param fast 是否使用超时时间较短的http客户端
     * @return string with utf-8
     */
    public static String post(String url, Map<String, String> paramMap, boolean...fast) throws IOException {
        return post(url, null, paramMap, fast);
    }

    /**
     * 发送json请求
     *
     * @param url     url
     * @param headers 头域集合
     * @param entity  body
     * @param fast 是否使用超时时间较短的http客户端
     * @return response
     * @throws IOException 失败时抛异常
     */
    public static String postJson(String url, Collection<Header> headers, Object entity, boolean...fast) throws IOException {
        HttpClient httpClient = getHttpClient(fast);
        HttpPost httpPost = new HttpPost(url);

        headers.forEach(httpPost::addHeader);

        StringEntity requestEntity = new StringEntity(JSON.toJSONString(entity), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        assertHttpStatusCode(response);
        return EntityUtils.toString(response.getEntity(), UTF_8);
    }

    /**
     * 使用Multipart/form-data模式发送http请求
     * @param url url
     * @param fileMap 文件map,key为文件名，value为File对象或文件内容字符串
     * @param paramMap post参数map
     * @return 响应body字符串
     * @throws Exception 执行失败时抛出异常
     */
    public static String postWithFile(String url, Map<String, Object> fileMap, Map<String, String> paramMap) throws Exception {
        return postWithFile(url, null, fileMap, paramMap);
    }

    /**
     * 使用Multipart/form-data模式发送http请求
     * @param url url
     * @param headerMap 头域map
     * @param fileMap 文件map,key为文件名，value为File对象或文件内容字符串
     * @param paramMap post参数map
     * @return 响应body字符串
     * @throws Exception 执行失败时抛出异常
     */
    public static String postWithFile(String url, Map<String, String> headerMap, Map<String, Object> fileMap, Map<String, String> paramMap) throws Exception {
        // 要使用commons.httpclient
        org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();

        PostMethod postMethod = new PostMethod(url);

        if (null != headerMap) {
            headerMap.forEach(postMethod::addRequestHeader);
        }

        List<Part> partList = new ArrayList<>();
        if (null != paramMap) {
            paramMap.forEach((k, v) -> partList.add(new StringPart(k, v)));
        }
        if (null != fileMap) {
            fileMap.forEach((k, v) -> {
                if (v instanceof File) {
                    try {
                        partList.add(new FilePart(k, (File) v));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                // 如果是自构建的字符串形式的文件（字符串内容为文件内容）
                else if (v instanceof String) {
                    partList.add(new FilePart(k, new ByteArrayPartSource(k, ((String) v).getBytes())));
                }
                else if (v instanceof byte[]) {
                    partList.add(new FilePart(k, new ByteArrayPartSource(k, (byte[]) v)));
                }
            });
        }

        Part[] parts = new Part[partList.size()];
        partList.toArray(parts);
        MultipartRequestEntity entity = new MultipartRequestEntity(parts, postMethod.getParams());
        postMethod.setRequestEntity(entity);
        httpClient.executeMethod(postMethod);
        if (postMethod.getStatusCode() >= org.apache.commons.httpclient.HttpStatus.SC_BAD_REQUEST) {
            throw new Exception("status code: " + postMethod.getStatusCode() + ", failed to send message to server.");
        }

        return postMethod.getResponseBodyAsString();
    }

    /**
     * 发送get请求，url传参
     *
     * @param url       url
     * @param headers   头域集合
     * @param paramList url 参数
     * @param fast 是否使用超时时间较短的http客户端
     * @return response
     * @throws IOException 失败时抛异常
     */
    public static String getUrlParam(String url, Collection<Header> headers,
                                     List<NameValuePair> paramList, boolean...fast) throws IOException {
        HttpClient httpClient = getHttpClient(fast);
        String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
        HttpGet httpGet = new HttpGet(url + "?" + paramStr);
        headers.forEach(httpGet::addHeader);
        HttpResponse response = httpClient.execute(httpGet);
        assertHttpStatusCode(response);
        return EntityUtils.toString(response.getEntity(), UTF_8);
    }

    /**
     * http connection with method of post
     *
     * @param url    example: http://localhost:8084/web1/app1.jsp
     * @param header http request header
     * @param paramMap  map
     * @param fast 是否使用超时时间较短的http客户端
     * @return string with utf-8
     */
    public static String post(String url, Header[] header, Map<String, String> paramMap, boolean...fast) throws IOException {
        HttpClient client = getHttpClient(fast);
        HttpPost post = new HttpPost(url);
        HttpResponse rep;
        HttpEntity entity;
        List<NameValuePair> list = new ArrayList<>();
        String text = null;
        paramMap.forEach((k, v) -> list.add(new BasicNameValuePair(k, v)));
        post.setEntity(new UrlEncodedFormEntity(list, UTF_8));
        if (null != header) {
            post.setHeaders(header);
        }
        rep = client.execute(post);
        assertHttpStatusCode(rep);
        entity = rep.getEntity();
        text = EntityUtils.toString(entity, UTF_8);
        return text;
    }

    private static void assertHttpStatusCode(HttpResponse rep) throws IOException {
        if (null == rep || null == rep.getStatusLine()) {
            throw new IOException("Request failed: null response.");
        }
        int statusCode = rep.getStatusLine().getStatusCode();
        if (statusCode >= org.apache.commons.httpclient.HttpStatus.SC_BAD_REQUEST) {
            throw new IOException(
                    "status code: " + statusCode + ", failed to send message to server.");
        }
    }

    /**
     * http connection with method of post
     *
     * @param url    example: http://localhost:8084/web1/app1.jsp
     * @param header http request header
     * @param param  string array, key, value, key, value, ..., key value, example: {"name", "lizw", "company", "hyt"}
     * @param fast 是否使用超时时间较短的http客户端
     * @return string with utf-8
     */
    public static String post(String url, Header[] header, String[] param, boolean...fast) {
        HttpClient client = getHttpClient(fast);
        HttpPost post = new HttpPost(url);
        HttpResponse rep;
        HttpEntity entity;
        List<NameValuePair> list = new ArrayList<>();
        String text = null;
        try {
            int len = param.length;
            for (int i = 0; i < len; i += BODY_STEP) {
                String key = param[i];
                String value = param[1 + i];
                list.add(new BasicNameValuePair(key, value));
            }
            post.setEntity(new UrlEncodedFormEntity(list, UTF_8));
            if (null != header) {
                post.setHeaders(header);
            }
            rep = client.execute(post);
            entity = rep.getEntity();
            text = EntityUtils.toString(entity, UTF_8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return text;
    }

    /**
     * http connection, recommend to use to get binary, such as image, file
     *
     * @param url    example: http://localhost:8084/web1/app1.jsp
     * @param output the outputstream to write the result from url
     * @param fast 是否使用超时时间较短的http客户端
     */
    public static void getStream(String url, OutputStream output, boolean...fast) {
        HttpClient client = getHttpClient(fast);
        HttpGet g = new HttpGet(url);
        HttpResponse rep;
        HttpEntity entity;
        try {
            rep = client.execute(g);
            entity = rep.getEntity();
            entity.writeTo(output);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * http connection, recommend to use to get binary, such as image, file
     *
     * @param url    example: http://localhost:8084/web1/app1.jsp
     * @param param  string array, key, value, key, value, ..., key value, example: {"name", "lizw", "company", "hyt"}
     * @param output the outputstream to write the result from url
     * @param fast 是否使用超时时间较短的http客户端
     */
    public static void postStream(String url, String[] param, OutputStream output, boolean...fast) {
        HttpClient client = getHttpClient(fast);
        HttpPost post = new HttpPost(url);
        HttpResponse rep;
        HttpEntity entity;
        List<NameValuePair> list = new ArrayList<>();
        try {
            int len = param.length;
            for (int i = 0; i < len; i += BODY_STEP) {
                String key = param[i];
                String value = param[1 + i];
                list.add(new BasicNameValuePair(key, value));
            }
            post.setEntity(new UrlEncodedFormEntity(list, UTF_8));
            rep = client.execute(post);
            entity = rep.getEntity();
            entity.writeTo(output);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * post请求 带parameter和header
     *
     * @param url        请求地址
     * @param headerList 头域
     * @param paramList  参数
     * @param fast 是否使用超时时间较短的http客户端
     * @return 请求字符串
     * @throws IOException io异常
     */
    public static String postParameterAndHeader(
            String url, List<Header> headerList, List<NameValuePair> paramList, boolean...fast) throws IOException {
        HttpClient httpClient = getHttpClient(fast);
        HttpPost httpPost = new HttpPost(url);
        if (CollectionUtils.isNotEmpty(headerList)) {
            headerList.forEach(httpPost::addHeader);
        }
        if (CollectionUtils.isNotEmpty(paramList)) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
            httpPost.setEntity(entity);
        }
        HttpResponse httpResponse = httpClient.execute(httpPost);
        return EntityUtils.toString(httpResponse.getEntity(), UTF_8);
    }

    private static HttpClient getHttpClient(boolean...fast) {
        boolean useFast = null != fast && fast.length > 0 && fast[0];
        return useFast ? fastHttpClientBuilder.build() : httpClientBuilder.build();
    }
}
