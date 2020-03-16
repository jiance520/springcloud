package com;

import com.utils.MyStreamUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestSpider {
    @Test
    public void test()throws Exception{
        //创建get
        HttpGet httpGet = new HttpGet("https://www.jd.com");
        //获取响应
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //执行请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //获取响应的内容(实体)
        HttpEntity httpEntity = httpResponse.getEntity();
        //实体内容转字符串
        String str = EntityUtils.toString(httpEntity,"UTF-8");
        //打印
        System.out.println("-----test:"+str);

//        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
//        //创建代理主机，ip,port,protocal
//        HttpHost httpHost = new HttpHost("175.11.192.28",8118,"https");
//        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();
//        //表单参数
//        List<NameValuePair> form = new ArrayList<NameValuePair>();
//        form.add(new BasicNameValuePair("username","18626675332"));
//        form.add(new BasicNameValuePair("password","xupcc_5332"));
//        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(form,"UTF-8");
//        //创建post请求
//        HttpPost httpPost = new HttpPost("https://passport.csdn.net/account/verify");
//        httpPost.setConfig(requestConfig);
//        httpPost.setEntity(formEntity);
//        EntityUtils.toString(formEntity,"UTF-8");
    }
}
