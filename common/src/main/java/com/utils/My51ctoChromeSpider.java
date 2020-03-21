package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.xsoup.XElement;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//无需代理，不会被封。
public class My51ctoChromeSpider {
    //String httpsUrl = "https://edu.51cto.com/";
    //String httpsUrl = "https://passport.csdn.net/login?code=public";
    //public static String httpsUrl = "https://home.51cto.com/index";
    public static String chromechromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromechromeDriver.exe";
    public static String searchHttpsUrl = "https://edu.51cto.com/center/course/index/search";
    public static String usernameKey = "loginform-username";
    public static String passwordKey = "loginform-password";
    public static String username = "jiance520@163.com";
    public static String password = "1983love";
    public static String searchStr = "java";
    public static String searchSelector = "#searchQ";
    public static String searchClick = "#Search > button";//不同页面的值不一样
    public static WebDriver chromeDriver = null;
    public static int pagenums = 3;//663是动态数据，无法获取，body > div.Search > div:nth-child(6) > div.Page > ul > li.last.disabled > span
    public static int page65 = 6;
    public static int pagemax = 0;

    public static String chromeNoLogin(){
        //设置驱动程序路径
        //System.setProperty("webchromeDriver.chrome.chromeDriver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromechromeDriver.exe");
        System.setProperty("webchromeDriver.chrome.chromeDriver",My51ctoChromeSpider.chromechromeDriverPath);
        //首选项
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        //hashMap.put("profile.managed_default_content_settings.images",2); //禁止加载图片
        //hashMap.put("profile.default_content_settings.cookies",2); //禁止cookies
        hashMap.put("profile.default_content_settings.popups", 0); //禁止弹窗

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs",hashMap);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);

        //新建一个谷歌浏览器对象(diver)
        My51ctoChromeSpider.chromeDriver = new ChromeDriver(desiredCapabilities);
        //通过chromeDriver控制浏览器打开链接(url)
        //String url = "https://www.csdn.net";
        chromeDriver.get(My51ctoChromeSpider.searchHttpsUrl);
        //页面标题，<title>
        String tigle = chromeDriver.getTitle();
        //页面内容
        String html = chromeDriver.getPageSource();
        System.out.println("-----tigle:"+tigle);
        System.out.println("-----html:"+html.length());
        try {
            Thread.sleep(5000); //鼠标放上去显示html,打开复制。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //chromeDriver.close();
        return html;
    }
    public static String chromeLogin() throws InterruptedException {
        //设置驱动程序路径
        //System.setProperty("webchromeDriver.chrome.chromeDriver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromechromeDriver.exe");
        System.setProperty("webchromeDriver.chrome.chromeDriver",My51ctoChromeSpider.chromechromeDriverPath);
        //首选项
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("profile.managed_default_content_settings.images",2); //禁止加载图片
        hashMap.put("profile.default_content_settings.cookies",2); //禁止cookies
        //hashMap.put("profile.default_content_settings.popups", 0); //禁止弹窗

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs",hashMap);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);

        //新建一个谷歌浏览器对象(diver)
        My51ctoChromeSpider.chromeDriver = new ChromeDriver(desiredCapabilities);
        //通过chromeDriver控制浏览器打开链接(url) 登陆地址
        //String url = "https://home.51cto.com/index?reback=http://www.51cto.com/";
        chromeDriver.get(My51ctoChromeSpider.searchHttpsUrl);
        WebElement webElement = chromeDriver.findElement(By.cssSelector("#login-wechat > div.userPassLogin.back_account > a"));
        webElement.click();
        //按照id和name元素，执行输入按键和点击
        //输入账号，按照id直找元素
        chromeDriver.findElement(By.id(My51ctoChromeSpider.usernameKey)).sendKeys(My51ctoChromeSpider.username);
        //输入密码
        chromeDriver.findElement(By.id(My51ctoChromeSpider.passwordKey)).sendKeys(My51ctoChromeSpider.password);
        //Thread.sleep(5000);
        //点击登陆,#login-form > div.clearfix.zxfDl > input.loginbtn.fl
        chromeDriver.findElement(By.name("login-button")).click();

        //页面标题
        String tigle = chromeDriver.getTitle();
        //页面偌
        String html = chromeDriver.getPageSource();
        System.out.println("-----tigle:"+tigle);
        System.out.println("-----html:"+html.length());
        Thread.sleep(5000); //鼠标放上去显示html,打开复制。
        //chromeDriver.close();
        return html;
    }
    public static List<HashMap> buy51cto(String searchStr) {
        if(searchStr!=null&&!"".equals(searchStr)){
            My51ctoChromeSpider.searchStr=searchStr;
        }
        WebElement webElement = null;
        String httpsStr = null;
        boolean flag = true;
        if(chromeDriver==null){
            httpsStr = chromeNoLogin();//加载页面
            webElement =  chromeDriver.findElement(By.cssSelector("#upopbox_mid > div > span"));//获取弹窗按钮
            webElement.click();//关闭弹窗
            webElement = chromeDriver.findElement(By.cssSelector(searchSelector));//获取输入框
            webElement.clear();// 清空输入框原来的内容
            webElement.sendKeys(My51ctoChromeSpider.searchStr);//输入搜索值
            webElement = chromeDriver.findElement(By.cssSelector(searchClick));//获取搜索按钮
            //System.out.println("-----chromeDriver2:"+chromeDriver);
            webElement.submit();//提交搜索

            //body > div.Search > div:nth-child(5) > div.Page > ul > li.last.disabled > span
            //body > div.Search > div:nth-child(6) > div.Page > ul > li.last.disabled > span
            webElement.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.Page > ul > li.last.disabled > span"));
            /// todo 自己修改 找不到元素
            httpsStr=webElement.getText();
            httpsStr=httpsStr.replaceAll("共","");
            httpsStr=httpsStr.replaceAll("条记录","");
            My51ctoChromeSpider.pagenums=Integer.parseInt(httpsStr);
            if(My51ctoChromeSpider.pagenums%22==0){
                My51ctoChromeSpider.pagenums=My51ctoChromeSpider.pagenums/22;
            }
            else {
                My51ctoChromeSpider.pagenums=My51ctoChromeSpider.pagenums/22+1;
            }

        }else {
            //body > div.Search > div:nth-child(6) > div.Page > ul > li.next > a
            if(pagemax<pagenums){
                webElement = chromeDriver.findElement(By.cssSelector("body > div.Search > div:nth-child("+My51ctoChromeSpider.page65+") > div.Page > ul > li.next > a"));
                /// todo 自己修改 找不到元素
                webElement.click();
            }
            else {
                System.out.println("-----已到最大页数，退出程序");
                System.exit(0);
            }
            pagemax++;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //单一窗口切换
//        Thread.sleep(3000);
//        String currHandle=chromeDriver.getWindowHandle();
//        chromeDriver=chromeDriver.switchTo().window(currHandle);
        //body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child(1) > div.right > div.con_01 > a
        //多窗口切换
//        String[] handles=new String[chromeDriver.getWindowHandles().size()];
//        System.out.println(handles.length);
//        chromeDriver.getWindowHandles().toArray(handles);
//        //切换到注册窗口
//        chromeDriver.switchTo().window(handles[1]);
        
        List<WebElement> webElementList=chromeDriver.findElements(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li"));
        System.out.println("-----webElementList.size():"+webElementList.size());
        //List<WebElement> webElementList=chromeDriver.findElements(By.xpath("//ul[@class=\"search_course_box_list\"]/li"));
        HashMap hashMap = null;
        String tempStr = "";
        int cntSscore=1;
        List<HashMap> mapArrayList = new ArrayList<>();
        for(WebElement webElement1:webElementList){
            flag=My51ctoChromeSpider.elementContains(webElement1,"");
            if(flag){
                cntSscore++;
                continue;//如果该元素包含指定内容，则不爬取。
            }
            hashMap = new HashMap();
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_01 > a");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_01 > a"));
                hashMap.put("stitle",webElement.getText());
            }
            else {
                hashMap.put("stitle","官方");
            }
            //webElement=webElement1.findElement(By.id("asdfnjnasod8"));查找不存在的节点会报错。
            flag = My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > p");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > p"));
                tempStr = webElement.getText();
                tempStr=tempStr.replaceAll("人学习","");
                hashMap.put("snumman",tempStr);
            }
            else {
                hashMap.put("snumman",0);
            }
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > div.star_bg > p.star_name.fr");
            if (flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > div.star_bg > p.star_name.fr"));
                hashMap.put("sscore",webElement.getText());
            }
            else {
                hashMap.put("sscore",0);
            }
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_04.mt10 > p.red");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_04.mt10 > p.red"));
                tempStr = webElement.getText();
                tempStr=tempStr.replaceAll("¥","");
                hashMap.put("sprice",tempStr);
            }
            else {
                hashMap.put("sprice",0);
            }
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_04.mt10 > p.old_price");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_04.mt10 > p.old_price"));
                tempStr = webElement.getText();
                tempStr=tempStr.replaceAll("¥","");
                hashMap.put("sprice2",tempStr);
            }
            else {
                hashMap.put("sprice2",0);
            }
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_04.mt10 > p.numZhe");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_04.mt10 > p.numZhe"));
                hashMap.put("shuodong",webElement.getText());
            }
            else {
                hashMap.put("shuodong",0);
            }
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > a:nth-child(2)");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > a:nth-child(2)"));
                tempStr = webElement.getText();
                tempStr=tempStr.replaceAll("共","");
                tempStr=tempStr.replaceAll("课时","");
                hashMap.put("stime",tempStr);
            }
            else {
                hashMap.put("stime",0);
            }
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > a:nth-child(1)");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > div.con_03.mt10 > a:nth-child(1)"));
                hashMap.put("sauthor",webElement.getText());
            }
            else{
                hashMap.put("sauthor","官方");
            }
            flag=My51ctoChromeSpider.elementExist(webElement1,"body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > a");
            if(flag){
                webElement=webElement1.findElement(By.cssSelector("body > div.Search > div:nth-child(6) > div.search_bottom_box_child > div > ul > li:nth-child("+cntSscore+") > div.right > a"));
                hashMap.put("sdetail",webElement.getText());
            }
            else {
                hashMap.put("sdetail","官方");
            }
            hashMap.put("sdate",new Date());
            mapArrayList.add(hashMap);
            cntSscore++;
        }
        System.out.println("-----mapArrayList:"+mapArrayList);
        //把网页存入本地。
        //MyStreamUtil.strWriteIntoFile(httpsStr,"D:\\workspace\\idea\\springcloud\\f8xn\\buy51cto\\src\\main\\resources\\1.html");

        //XElements xElements = Xsoup.select(httpsStr,"//body/div[@class=Search]/div/div[@class=search_bottom_box_child]/div/ul/li");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //chromeDriver.close();
        return mapArrayList;
    }
    public static void closeDrive(){
        if(My51ctoChromeSpider.chromeDriver !=null){
            My51ctoChromeSpider.chromeDriver.close();
        }
    }
    public static boolean elementExist (WebElement webElement,String selectorStr) {
        try{
            webElement.findElement(By.cssSelector(selectorStr));
            return true;
        }
        catch(org.openqa.selenium.NoSuchElementException ex) {
            return false;
        }
    }
    public static boolean elementContains(WebElement webElement,String str){
        boolean flag = true;
        System.out.println("-----webElement.getText():"+webElement.getText());
        String webElementText = webElement.getText();
        if(webElementText.contains("学习周期：")){
            return true;
        }
        if(webElementText.contains("专题")){
            return true;
        }
        if(str!=null&&!"".equals(str)){
            return webElementText.contains(str);
        }
        return false;
    }
}
