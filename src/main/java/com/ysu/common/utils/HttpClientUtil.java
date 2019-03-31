package com.ysu.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class HttpClientUtil {

    private static final Logger logger = LogManager.getLogger(HttpClientUtil.class);

    private static final int HTTP_TIMEOUT = 20000;

    public static String postRequestURL(String url, Map<String, Object> params) {
        return postRequestURL(url, params, HTTP_TIMEOUT);
    }

    public static String postRequestURL(String url, Map<String, Object> params, int timeout) {
        String result = null;
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        if (Validator.isNotNull(params)) {
            for (Map.Entry entry : params.entrySet()) {
                postMethod.addParameter(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        client.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        int status = 0;
        try {
            status = client.executeMethod(postMethod);
        } catch (HttpException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        if (status == HttpStatus.SC_OK) {
            try {
                result = postMethod.getResponseBodyAsString();
            } catch (IOException e) {
                logger.error(e.getMessage());
            } finally {
                postMethod.releaseConnection();
            }
        } else {
            logger.error("请求状态:" + status);
        }
        return result;
    }

    public static String getRequestURL(String url, Map<String, Object> params) {
        return getRequestURL(url, params, HTTP_TIMEOUT);
    }

    public static String getRequestURL(String url, Map<String, Object> params, int timeout) {
        String result = null;
        HttpClient client = new HttpClient();
        StringBuffer sb = new StringBuffer();
        sb.append("?");
        if (Validator.isNotNull(params)) {
            for (Map.Entry entry : params.entrySet()) {
                sb.append(entry.getKey().toString()).append("=").append(entry.getValue().toString()).append("&");
            }
        }
        GetMethod getMethod = new GetMethod(url + sb.toString());
        getMethod.addRequestHeader("Content-Type", "text/html; charset=UTF-8");
        int status = 0;
        try {
            client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
            status = client.executeMethod(getMethod);
        } catch (HttpException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        if (status == HttpStatus.SC_OK) {
            try {
                result = getMethod.getResponseBodyAsString();
            } catch (IOException e) {
                logger.error(e.getMessage());
            } finally {
                getMethod.releaseConnection();
            }
        } else {
            logger.error("请求状态:" + status);
        }

        return result;
    }
}
