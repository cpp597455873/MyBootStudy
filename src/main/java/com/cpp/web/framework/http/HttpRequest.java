package com.cpp.web.framework.http;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpRequest {

    private Method method;
    private long timeout;
    private String url;
    private Map getMap;
    private MediaType mediaType;
    private String content;
    private byte[] byteContent;
    private File fileContent;
    private OkHttpClient okHttpClient;
    private Map<String, String> header;

    public enum Method {
        POST, GET, PUT, DELETE, PATCH
    }

    /**
     * 创建方法
     *
     * @param url
     * @param method
     * @return
     */
    public static HttpRequest newInstance(String url, Method method) {
        return new HttpRequest().url(url).method(method);
    }

    /**
     * 设置请求地址
     *
     * @param url
     * @return
     */
    public HttpRequest url(String url) {
        this.url = url;
        return this;
    }

    /**
     * 设置请求方法
     *
     * @param method
     * @return
     */
    public HttpRequest method(Method method) {
        this.method = method;
        return this;
    }

    /**
     * 设置时间，单位毫秒
     *
     * @param timeout
     * @return
     */
    public HttpRequest timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 设置mediaType
     *
     * @param mediaType
     * @return
     */
    public HttpRequest mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * 设置json请求
     *
     * @return
     */
    public HttpRequest jsonMediaType() {
        this.mediaType = MediaType.parse("application/json");
        return this;
    }

    /**
     * 设置文件请求
     *
     * @return
     */
    public HttpRequest fileMediaType() {
        this.mediaType = MediaType.parse("multipart/form-data");
        return this;
    }

    /**
     * get请求的参数
     *
     * @param getMap
     * @return
     */
    public HttpRequest getParams(Map getMap) {
        this.getMap = getMap;
        return this;
    }

    /**
     * 发送字符串
     *
     * @param content
     * @return
     */
    public HttpRequest postStr(String content) {
        this.content = content;
        return this;
    }

    /**
     * 发送字节数组
     *
     * @param byteContent
     * @return
     */
    public HttpRequest postByte(byte[] byteContent) {
        this.byteContent = byteContent;
        return this;
    }

    /**
     * 请求头
     *
     * @param header
     * @return
     */
    public HttpRequest addHeaders(Map<String, String> header) {
        this.header = header;
        return this;
    }

    /**
     * 设置发送内容为文件
     *
     * @param fileContent
     * @return
     */
    public HttpRequest postFile(File fileContent) {
        this.fileContent = fileContent;
        return this;
    }

    public String send() throws Exception {
        if (method == null) {
            throw new Exception("http方法没有指定");
        }

        okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(timeout, TimeUnit.MILLISECONDS);

        if (method == Method.GET) {
            return get();
        } else {
            return post();
        }
    }

    /**
     * 发送get请求
     *
     * @return
     * @throws Exception
     */
    private String get() throws Exception {
        StringBuilder buffer = new StringBuilder();
        if (getMap != null && getMap.size() > 0) {
            Set<Map.Entry<String, Object>> entries = getMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                if (buffer.length() == 0) {
                    buffer.append("?");
                } else {
                    buffer.append("&");
                }
                buffer.append(entry.getKey()).append("=").append(entry.getValue() == null ? "" : entry.getValue().toString());
            }
        }
        Request.Builder builder = new Request.Builder().get().url(this.url + buffer.toString());
        addHeader(builder);
        return okHttpClient.newCall(builder.build()).execute().body().string();
    }

    /**
     * 添加请求头
     *
     * @param builder
     */
    private void addHeader(Request.Builder builder) {
        if (header != null && header.size() > 0) {
            Set<Map.Entry<String, String>> entries = header.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 发送post请求
     *
     * @return
     * @throws Exception
     */
    private String post() throws Exception {
        RequestBody body = null;
        if (content != null) {
            body = RequestBody.create(mediaType, content);
        } else if (byteContent != null) {
            body = RequestBody.create(mediaType, byteContent);
        } else if (fileContent != null) {
            body = RequestBody.create(mediaType, fileContent);
        }


        Request.Builder builder = new Request.Builder().url(this.url);
        if (method == Method.POST) {
            builder.post(body);
        } else if (method == Method.PUT) {
            builder.put(body);
        } else if (method == Method.DELETE) {
            builder.delete(body);
        } else if (method == Method.PATCH) {
            builder.patch(body);
        }
        addHeader(builder);
        return okHttpClient.newCall(builder.build()).execute().body().string();
    }

}