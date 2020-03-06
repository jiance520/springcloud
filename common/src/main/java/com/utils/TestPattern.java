package com.utils;

import java.util.regex.Pattern;

public class TestPattern {
    public static void main(String[] args) {
//        Boolean isTwoNum = Pattern.matches("a(.*|\n)*","a\na\n\n");
//        Boolean isTwoNum = Pattern.matches("java[\\s\\w-\\.:/\n]*-overwrite","java -jar D:/workspace/idea/com/shiro/src/main/resources/mybatisGenerator/mybatis-generator-core-1.3.2.jar-overwrite");
        String oldStr = "java -jar D:workspaceideaspringcloudf8xnsrcresourcesmybatisGeneratormybatis-generator-core-1.3.2.jar -configfile D:workspaceideaspringcloudf8xnsrcresourcesmybatisGeneratorgenerator.xml -overwrite";
        String regEx = "java[\\s\\w\\-\\.:/\n\\\\]*-overwrite[\\sa-z\n]*";
        //\\w小写w匹配字母或数字或下划线或汉字
        //\\.匹配一个点. \将后面的字符做特殊字符或原义字符。
        //\用\\\\表示！
        String newStr = "jdbc:mysql://localhost:3306/shiro";
        Boolean flag = Pattern.matches(regEx,oldStr);
        if(flag){
            oldStr=oldStr.replaceAll(oldStr,"D:/workspace/idea/springcloud/f8xn/src/resources/mybatisGenerator/");
            System.out.println("-----oldStr:"+oldStr);
        }
        else {
            System.out.println("-----没有匹配到");
        }
    }
}
