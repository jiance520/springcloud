package com.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySpiderUtil {
    private String uri = "http://localhost:8081/autof8/testActionConnParams";
    private String proxyIp = "117.88.177.244";
    private int proxyPort = 3000;
    private String usernameKey = "all";
    private String passwordKey = "pwd";
    private String username = "jiance520";
    private String password = "1983love";
    private String uriScheme = "http";
    private String uriHost = "localhost";
    private String uriPort = "8081";
    private String uriPath = "/autof8/testActionConnParams";
    private String chartset = "UTF-8";
    //GET无参：无帐户密码,不用登陆
    public String getNoProxyNoLogin(String uri){
        if(uri!=null&&!"".equals(uri)){
            this.uri=uri;
        }
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(this.uri);
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
        return str;
    }
    //GET有参：帐户密码登陆，方式一：直接拼接URL
    public String getNoProxyLogin(){
        if(uri!=null&&!"".equals(uri)){
            this.uri=uri;
        }
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuffer params = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("all=" + URLEncoder.encode("jiance520", "utf-8"));
            params.append("&");
            params.append("pwd=Ol1983love");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 创建Get请求
        //HttpGet httpGet = new HttpGet("https://passport.csdn.net/v1/api/riskControl/checkNVC" + "?" + params);
        HttpGet httpGet = new HttpGet("https://passport.csdn.net/login?code=public" + "?" + params);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

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
                //MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log1.log");
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
        return str;
    }
    //GET有参：帐户密码登陆，方式二：使用URI获得HttpGet
    public String getNoProxyLoginUri(){
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("all", "jiance520"));
            params.add(new BasicNameValuePair("pwd", "Ol1983love"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            //https://passport.csdn.net/login?code=public
            uri = new URIBuilder().setScheme("http").setHost("passport.csdn.net")
                    .setPort(80).setPath("/login")
                    .setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

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
                //MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log1.log");
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
        return str;
    }
    //GET 有参，使用代理,无帐户密码,不用登陆
    public String getProxyNoLogin(String proxyIp,int proxyPort,String uri){
        if(uri!=null&&!"".equals(uri)){
            this.uri=uri;
        }
        if(proxyIp!=null&&!"".equals(proxyIp)){
            this.proxyIp=proxyIp;
        }
        this.proxyPort=proxyPort;
        String str = null;
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost(proxyIp,proxyPort);
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
        HttpGet httpGet = new HttpGet(uri);
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
        return str;
    }
    //GET 有参，使用代理,帐户密码,登陆
    public String getProxyLoginUri(String proxyIp,int proxyPort){
        if(proxyIp!=null&&!"".equals(proxyIp)){
            this.proxyIp=proxyIp;
        }
        this.proxyPort=proxyPort;
        String str = null;
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost(proxyIp,proxyPort);
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

        // 参数
        URI uriObj = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("all", "jiance520"));
            params.add(new BasicNameValuePair("pwd", "Ol1983love"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            //https://passport.csdn.net/login?code=public
            uriObj = new URIBuilder().setScheme("http").setHost("passport.csdn.net")
                    .setPort(80).setPath("/login")
                    .setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uriObj);

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
        return str;
    }
    //POST传递普通(登陆)，方式与GET有参一样即可
    public String postNoProxyLogin(String uri){
        if(uri!=null&&!"".equals(uri)){
            this.uri=uri;
        }
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuffer params = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("all=" + URLEncoder.encode("jiance520", "utf-8"));
            params.append("&");
            params.append("pwd=Ol1983love");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 创建Post请求
        //HttpGet httpGet = new HttpGet("https://passport.csdn.net/v1/api/riskControl/checkNVC" + "?" + params);
        HttpPost httpPost = new HttpPost(uri + "?" + params);
        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Post请求里
            httpPost.setConfig(requestConfig);

            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                str = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);
                //保存到本地日记log.log，控制台输出的所有内容，请复制到log.log查看。响应内容在最后。
                //MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log1.log");
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
        return str;
    }
    //POST 代理+登陆 表单提交
    public String postProxyLoginUrl(String proxyIp,int proxyPort,String uri){
        if(uri!=null&&!"".equals(uri)){
            this.uri=uri;
        }
        if(proxyIp!=null&&!"".equals(proxyIp)){
            this.proxyIp=proxyIp;
        }
        this.proxyPort=proxyPort;
        String str = null;
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost(proxyIp,proxyPort);
        //设置请求配置的代理地址
        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();
        //表单参数
        List<NameValuePair> form = new ArrayList<NameValuePair>();
        form.add(new BasicNameValuePair("all","jiance520"));
        form.add(new BasicNameValuePair("pwd","Ol***e"));
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(form,"UTF-8");
            //创建post请求
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(formEntity);
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            //获取响应的内容(实体)
            HttpEntity httpEntity = httpResponse.getEntity();
            //实体内容转字符串
            str = EntityUtils.toString(httpEntity,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----str:"+str);
        return  str;
    }
    //POST 代理+不登陆
    public String postProxyNoLogin(String proxyIp,int proxyPort,String uri){
        if(uri!=null&&!"".equals(uri)){
            this.uri=uri;
        }
        if(proxyIp!=null&&!"".equals(proxyIp)){
            this.proxyIp=proxyIp;
        }
        this.proxyPort=proxyPort;
        String str = null;
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost(proxyIp,proxyPort);
        //设置请求配置的代理地址
        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();
        try {
            //创建post请求
            HttpPost httpPost = new HttpPost(uri);
            httpPost.setConfig(requestConfig);
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            //获取响应的内容(实体)
            HttpEntity httpEntity = httpResponse.getEntity();
            //实体内容转字符串
            str = EntityUtils.toString(httpEntity,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----str:"+str);
        return  str;
    }
    //POST有参(对象参数)
    public String postObjectNoProxyNoLogin(String uri,T objectBean){
        if(uri!=null&&!"".equals(uri)){
            this.uri=uri;
        }
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(uri);
        // 我这里利用阿里的fastjson，将Object转换为json字符串;
        // (需要导入com.alibaba.fastjson.JSON包)
        String jsonString = JSON.toJSONString(objectBean);

        StringEntity entity = new StringEntity(jsonString, "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                str = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);
                //保存到本地日记log.log，控制台输出的所有内容，请复制到log.log查看。响应内容在最后。
                //MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log1.log");

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
        return str;
    }
    //POST有参(普通参数(登陆) + 对象参数)
    public String postObjectNoProxyLoginUri(T objectBean){
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("all", "jiance520"));
            params.add(new BasicNameValuePair("pwd", "O***e？"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8081)
                    .setPath("/autof8/testActionConnParams").setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        HttpPost httpPost = new HttpPost(uri);
        // HttpPost httpPost = new
        // HttpPost("http://localhost:8081/autof8/testActionConnParams");

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(objectBean), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                str = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);
                //保存到本地日记log.log，控制台输出的所有内容，请复制到log.log查看。响应内容在最后。
                MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log1.log");
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
        return str;
    }
    //POST有参(普通参数(登陆) + 对象参数 + 代理)
    public String postObjectProxyLoginUri(String proxyIp,int proxyPort,T objectBean){
        if(proxyIp!=null&&!"".equals(proxyIp)){
            this.proxyIp=proxyIp;
        }
        this.proxyPort=proxyPort;
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost(proxyIp,proxyPort);
        //设置请求配置的代理地址
        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();

        // 创建Post请求
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("all", "jiance520"));
            params.add(new BasicNameValuePair("pwd", "O***e？"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost").setPort(8081)
                    .setPath("/autof8/testActionConnParams").setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }

        HttpPost httpPost = new HttpPost(uri);
        // HttpPost httpPost = new
        // HttpPost("http://localhost:8081/autof8/testActionConnParams");

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(objectBean), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                str = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + str);
                //保存到本地日记log.log，控制台输出的所有内容，请复制到log.log查看。响应内容在最后。
                //MyStreamUtil.strWriteIntoFile(str,"D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\main\\resources\\log1.log");
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
        return str;
    }
    //登陆需要form表单提交，只能用Post
}
