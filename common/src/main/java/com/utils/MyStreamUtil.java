package com.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
//spider爬虫工具，爬取数据，数据流工具
public class MyStreamUtil {
    //读取本地文件，返回字符串。
    public static String filePathToStr(String filePath, Object... utf8Str){
        FileReader fileReader = null;
        String backStr = null;
        BufferedReader bufferedReader = null;
        File generatorFile = new File(filePath);
        StringBuilder stringBuffer = new StringBuilder();
        try {
            fileReader = new FileReader(generatorFile);
            bufferedReader = new BufferedReader(fileReader);
            /*int unicode = bufferedReader.read();
            while (unicode!=-1){
                stringBuffer.append((char)unicode);
                unicode = bufferedReader.read();
            }*///方式一可用
            String str = bufferedReader.readLine();
            while(str != null){
                stringBuffer.append(str).append("\n");
                str = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();

            backStr = stringBuffer.toString();
            //解码，为了解决中文乱码
            String decoderStr = null;
            if(utf8Str.length==0) {
                decoderStr = "UTF-8";
            }
            else {
                decoderStr = utf8Str[0].toString();
            }
            backStr = URLDecoder.decode(backStr,decoderStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return backStr;
    }
    //读取本地resources下的文件名，返回字符串。
    public static String resourcesFileNameToStr(String name, Object... utf8Str){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        return inputStreamToStr(inputStream);
    }
    //把字符输入流转为字符串
    public static String fileReaderToStr(FileReader fileReader, Object... utf8Str){
        String backStr = null;
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        String str = null;
        try {
            str = bufferedReader.readLine();
            while(str!= null){
                stringBuffer.append(str).append("\n");
                str = bufferedReader.readLine();
            }
            backStr = stringBuffer.toString();
            //解码，为了解决中文乱码
            String decoderStr = null;
            if(utf8Str.length==0) {
                decoderStr = "UTF-8";
            }
            else {
                decoderStr = utf8Str[0].toString();
            }
            backStr = URLDecoder.decode(backStr,decoderStr);
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return backStr;
    }
    //把字节输入流写入文件， //一次性读取,执行过程不能处理返回字符串。会乱码。
    public static void inputStreamToFile(InputStream inputStream, String filePath){
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = null;
        int len = -1;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            len = bufferedInputStream.read();
            while(len!=-1){
                bufferedOutputStream.write(len);
                len=bufferedInputStream.read();
            }
            bufferedInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //把字节输入流转为字符串，//使用缓存
    public static String inputStreamToStr(InputStream inputStream){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        try {
            while((len=inputStream.read(buf))!=-1){
                byteArrayOutputStream.write(buf,0,len);
            }
            byteArrayOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(byteArrayOutputStream.toByteArray());
    }
    //把字符串写入本地
    public static void strWriteIntoFile(String str, String filePath){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        File generatorFile = new File(filePath);
        try {
            fileWriter = new FileWriter(generatorFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str);
            fileWriter.flush();
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //自写爬取工具，爬取https网站内容,不转码的原格式。只能爬取静态服务器资源。封IP就用爬取代理，西刺代理www.xici.daili.com。
    public static String spiderHttps(String httpsStr){
        if(!httpsStr.startsWith("http")) {
            httpsStr="https://"+httpsStr;
        }
        String strUrl = httpsStr;
        String str = null;
        URL url = null;
        try {
            url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            int codeint = connection.getResponseCode();
            if(codeint==200){
                InputStream inputStream = connection.getInputStream();
                str = MyStreamUtil.inputStreamToStr(inputStream);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
    //字符串转json
    public static String getJsonStrByUrlStr(String urlStr){
        //String paramStr = "a=a1&b=b1&c=c1";
        String[] params = urlStr.split("&");
        JSONObject obj = new JSONObject();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                try {
                    obj.put(key,value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj.toString();
    }
    //优化视觉
    public static void printUrlAll(String urlAllStr){
        String[] arr = urlAllStr.split("&");
        for(String a:arr){
            System.out.println("===>"+a);
        }
    }
    public static void printUrlLog(String urlLogStr){
        if(urlLogStr==null||"".equals(urlLogStr)){
            urlLogStr="je=0$sc=24-bit$sr=1536x864$ul=zh-cn$cs=GBK$dt=手机_手机通讯_运营商手机_手机配件-京东$hn=shouji.jd.com$fl=-$os=win$br=chrome$bv=80.0.3987.116$wb=1584271020$xb=1584271021$yb=1584285320$zb=2$cb=5$usc=baidu-pinzhuan$ucp=t_288551095_baidupinzhuan$umd=cpc$uct=0f3d30c8dba7459bb52f2eb5eba8ac7d_0_653f40d6552d42acb58270f8636554d9$lt=0$ct=1584285452625$tad=-$mba_finger=v001eyJiIjoiNmVmOTE3MTAtY2ZjNi05NGIxLWZmYjMtYTYzMjM2ZWE4Zjc4LTE1ODQyNzEwMjIiLCJjIjoieVUvbWJ3SHJxZUUva3pidURrTEZueUE9PSIsImQiOiJXaW4zMiIsImYiOjgsImciOjAsImgiOiJjYW52YXMgd2luZGluZzp5ZXN+Y2FudmFzIGZwOmU5MDkzZDJhNDRhNDUxY2MwYzBhMTFiZmYwZWE4NDk3IiwiaSI6MzEsImoiOiIyMDIwLTAzLTE1IDIzOjE3OjMyIiwiayI6IjExZWIxYjI1MGQ2ZDhiZTdlYmNiY2YyNWI4ZWYxMWIwIiwibCI6IjY1ZjI5NTE2NjE5ZTUxMjI4NWU0NTZkMTQxODk4N2FlIiwibSI6IiIsIm4iOiJDaHJvbWUgUERGIFBsdWdpbjtDaHJvbWUgUERGIFZpZXdlcjtOYXRpdmUgQ2xpZW50OyIsIm8iOjMsInAiOiIxNTM2Kjg2NCJ9$fpftime=35$pinid=-$jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_653f40d6552d42acb58270f8636554d9|1584285416489$dataver=0.1$unpl";
        }
        String[] arr2 = urlLogStr.split("\\$");
        for(String a:arr2){
            System.out.println("===>"+a);
        }
    }

}
