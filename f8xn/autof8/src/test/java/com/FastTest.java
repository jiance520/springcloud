package com;

import com.utils.MyStreamUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FastTest {
    @Test
    public void fastTest() throws FileNotFoundException {
        //它支持“classpath:”和“file:”的地址前缀，它能够从指定的地址加载文件资源
        //getFile()不能嵌套在jar文件中，如果需要在SpringBoot项目中读取资源文件，最好使用getInputStream()。
        //https://shouji.jd.com/
//        String str = MyStreamUtil.readFilePathToString("D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\test\\java\\com\\log.log","UTF-8");
        File file = new File("D:\\workspace\\idea\\springcloud\\f8xn\\autof8\\src\\test\\java\\com\\log.log");
        FileReader fileReader = new FileReader(file);
        String str = MyStreamUtil.fileReaderToStr(fileReader);
        System.out.println("-----str:"+str);
    }
}
