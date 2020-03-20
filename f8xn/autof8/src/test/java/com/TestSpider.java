package com;

import com.alibaba.fastjson.JSON;
import com.entity.T_user;
import com.utils.MyStreamUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TestSpider {
    @Test
    //GET无参：无帐户密码
    public void noProxyNoLoginSpiderGet(){
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("https://www.huanqiu.com");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                str = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);
                //保存到本地日记log.log，控制台输出的所有内容，请复制到log.log查看。响应内容在最后。
                //MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log.log");

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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
        //return str;
    }
    @Test
    //使用代理的get
    public void proxyNoLoginSpiderGet(){
        String str = null;
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost("117.88.5.103",3000);
        //设置请求配置的代理地址,中间参数可不要。RequestConfig.custom().build();
        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost)
            // 设置连接超时时间(单位毫秒)
            .setConnectTimeout(5000)
            // 设置请求超时时间(单位毫秒)
            .setConnectionRequestTimeout(5000)
            // socket读写超时时间(单位毫秒)
            .setSocketTimeout(5000)
            // 设置是否允许重定向(默认为true)
            .setRedirectsEnabled(true).build();
        //创建get
        HttpGet httpGet = new HttpGet("https://www.51job.com/");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        httpGet.setHeader("refer", "http://www.baidu.com/s?tn=monline_5_dg&bs=httpclient4+MultiThreadedHttpConnectionManager");
        //执行请求
        CloseableHttpResponse httpResponse = null;
        HttpEntity httpEntity = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            //获取响应的内容(实体)
            httpEntity = httpResponse.getEntity();
            System.out.println("响应状态为:" + httpResponse.getStatusLine());
            //实体内容转字符串
            if (httpEntity != null) {
                System.out.println("响应内容长度为:" + httpEntity.getContentLength());
                str = EntityUtils.toString(httpEntity);
                System.out.println("响应内容为:" + str);
            }
            //保存到本地日记log.log，控制台输出的所有内容，请复制到log.log查看。响应内容在最后。
            //MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log1.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //打印,内容在最后
        System.out.println("-----str:"+str);
        //return str;
    }
    @Test
    //代理失败。不能使用代理
    public void proxyLoginSpiderPost()throws Exception{
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost("117.88.4.127",3000);
        //设置请求配置的代理地址
        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();
        //表单参数
        List<NameValuePair> form = new ArrayList<NameValuePair>();
        form.add(new BasicNameValuePair("email","jiance520@163.com"));
        form.add(new BasicNameValuePair("password","19831107"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(form,"UTF-8");
        //创建post请求
        HttpPost httpPost = new HttpPost("https://i.huanqiu.com/login");
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(formEntity);
        //执行请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //获取响应的内容(实体)
        HttpEntity httpEntity = httpResponse.getEntity();
        //实体内容转字符串
        String str = EntityUtils.toString(httpEntity,"UTF-8");
        //打印,无法查看是乱码
        System.out.println("-----str:"+str);
    }

}
