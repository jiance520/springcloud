package com;

import com.entity.Course;
import com.service.ICourseService;
import com.utils.MapToBeanUtil;
import com.utils.My51ctoChromeSpider;
import com.utils.MyStreamUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;

import javax.swing.plaf.IconUIResource;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class FastTest {

    @Test
    public void fastTest(){
        String str = MyStreamUtil.resourcesFileNameToStr("1.xml");
        XElements xElements = Xsoup.select(str,"//book/title/text()");// //表示根目录下的所有。text()表示标签文本innerHTML
        //找出超链接地址
        //按照xpath语法直接查找所有a元素
        //urls = driver.find_elements_by_xpath("");
        //获取a元素的href属性
        //urls.get(0).get_attribute("href");
        for(String xElement:xElements.list()){
            System.out.println("-----xElement:"+xElement);
        }
    }
    //提取内容
    @Test
    public void test2() throws FileNotFoundException, InterruptedException {
//        String str = MyStreamUtil.resourcesFileNameToStr("1.html");
//        //body > div.Search > section > article.fl.right > ul > li:nth-child(1) > a
//        //XElements xElements = Xsoup.select(str,"/html/body/div/section/article/ul/li/a/text()");
//        //XElements xElements = Xsoup.select(str,"//body/div/section/article/ul/li/a/text()");// //表示根目录下的所有。text()表示标签文本innerHTML
//        XElements xElements = Xsoup.select(str,"//body/div/section/article/ul/li/a[@title]/text()");
//        for(String xElement:xElements.list()){
//            System.out.println("-----xElement:"+xElement);
//        }
    }
    //提取超链接
    @Test
    public void test3() throws FileNotFoundException, InterruptedException {
//        String str = MyStreamUtil.resourcesFileNameToStr("1.html");
//        XElements xElements = Xsoup.select(str,"//body/div/section/article/ul/li/a/@href");
//        for(String xElement:xElements.list()){
//            System.out.println("-----xElement:"+xElement);
//        }
    }
    @Test
    public void test4() throws FileNotFoundException, InterruptedException {

    }

}
