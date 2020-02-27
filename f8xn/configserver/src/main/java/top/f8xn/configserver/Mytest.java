package top.f8xn.configserver;

import java.util.HashMap;
import java.util.List;

public class Mytest {
    public static void main(String[] args) {
        List<HashMap> hashMaps = JdbcUtil.exectueQuery("select * from dog");
        System.out.println("-----object2:"+hashMaps);
    }
}
