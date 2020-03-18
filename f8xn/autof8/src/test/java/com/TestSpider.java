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
    //GET有参：有帐户密码，方式一：直接拼接URL
    @Test
    public void noProxyLoginSpiderGet(){
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
        //return str;
    }
    //GET有参：有帐户密码，方式二：使用URI获得HttpGet
    @Test
    public void noProxyLoginSpiderGet2(){
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
    //POST传递普通参数时，方式与GET一样即可
    public void noProxyLoginJsonPost(){
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
        HttpPost httpPost = new HttpPost("https://passport.csdn.net/login" + "?" + params);
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
        //return str;
    }
    @Test
    //POST有参(对象参数)
    public void noProxyNoLoginObjectJsonPost(){
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://localhost:8081/autof8/testActionConnParams");
        T_user t_user = new T_user();
        t_user.setId(2);
        t_user.setName("123");
        t_user.setPassword("123");
        // 我这里利用阿里的fastjson，将Object转换为json字符串;
        // (需要导入com.alibaba.fastjson.JSON包)
        String jsonString = JSON.toJSONString(t_user);

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
        //retrun str;
    }
    @Test
    //POST有参(普通参数 + 对象参数)
    public void noProxyLoginObjectJsonPost(){
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

        // 创建t_user参数
        T_user t_user = new T_user();
        t_user.setId(2);
        t_user.setName("123");
        t_user.setPassword("123");

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(t_user), "UTF-8");

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
        //return str;
    }
    @Test
    //POST有参(普通参数(登陆) + 对象参数+代理)
    public void proxyLoginObjectJsonPost(){
        String str = null;
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        HttpHost httpHost = new HttpHost("117.88.177.244",3000);
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

        // 创建t_user参数
        T_user t_user = new T_user();
        t_user.setId(2);
        t_user.setName("123");
        t_user.setPassword("123");

        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(t_user), "UTF-8");

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
        //return str;
    }
    @Test
    //登陆需要form表单提交，只能用Post。
    public void noProxyLoginSpiderPost()throws Exception{
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //构造代理主机
        //HttpHost httpHost = new HttpHost("117.88.4.127",3000);
        //设置请求配置的代理地址
        //RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).build();
        //表单参数
        List<NameValuePair> form = new ArrayList<NameValuePair>();
        form.add(new BasicNameValuePair("email","jiance520@163.com"));
        form.add(new BasicNameValuePair("password","19831107"));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(form,"UTF-8");
        //创建post请求
        HttpPost httpPost = new HttpPost("https://i.huanqiu.com/login");
        //httpPost.setConfig(requestConfig);
        httpPost.setEntity(formEntity);
        //执行请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //获取响应的内容(实体)
        HttpEntity httpEntity = httpResponse.getEntity();
        //实体内容转字符串
        String str = EntityUtils.toString(httpEntity,"UTF-8");
        //打印,无法查看是乱码
        System.out.println("-----str:"+str);
        httpResponse.close();
        httpClient.close();
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
