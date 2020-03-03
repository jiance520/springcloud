package com;

import com.utils.StringIndex;

import java.util.HashMap;
import java.util.List;

public class Mytest {
    public static void main(String[] args) {
        List<HashMap> hashMaps = JdbcUtil.exectueQuery("select * from milmajordb2012127.t_user");
        System.out.println("-----object2:"+hashMaps);
//        String connectionURL ="jdbc:oracle:thin:@localhost:1521:orcl";
//        String connectionURL ="jdbc:postgresql://localhost:5432/geodb?characterEncoding=utf8";
        String connectionURL ="jdbc:mysql://localhost:3306/milmajordb2012127?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8";
        int indexStart = StringIndex.indexOf(connectionURL,"/" ,3);
        int indexEnd = connectionURL.indexOf("?");
        connectionURL = connectionURL.replaceAll("&","&amp;");
        System.out.println("-----connectionURL:"+connectionURL);
    }
}
