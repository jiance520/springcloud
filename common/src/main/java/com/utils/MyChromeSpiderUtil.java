package com.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.util.HashMap;

//无需代理，不会被封。
public class MyChromeSpiderUtil {
    static String usernameKey = "loginform-username";
    static String passwordKey = "loginform-password";
    static String username = "jiance520@163.com";
    static String password = "1983love";
    public static String chromeNoLogin(String url,String chromedriverPath) throws FileNotFoundException, InterruptedException {
        //设置驱动程序路径
        //System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",chromedriverPath);
        //首选项
        HashMap<String,Object> opts = new HashMap<String,Object>();
        opts.put("profile.managed_default_content_settings.images",2); //禁止加载图片
        opts.put("profile.default_content_settings.cookies",2); //禁止cookies

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs",opts);
        DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
        chromeCaps.setCapability(ChromeOptions.CAPABILITY,options);

        //新建一个谷歌浏览器对象(diver)
        WebDriver driver = new ChromeDriver(chromeCaps);
        //通过driver控制浏览器打开链接(url)
        //String url = "https://www.csdn.net";
        driver.get(url);

        //页面标题
        String tigle =driver.getTitle();
        //页面偌
        String html = driver.getPageSource();
        System.out.println("-----html:"+html);
        Thread.sleep(100000); //鼠标放上去显示html,打开复制。
        driver.close();
        return html;
    }
    public static String chromeLogin(String url,String chromedriverPath) throws FileNotFoundException, InterruptedException {
        //设置驱动程序路径
        //System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",chromedriverPath);
        //首选项
        HashMap<String,Object> opts = new HashMap<String,Object>();
        opts.put("profile.managed_default_content_settings.images",2); //禁止加载图片
        opts.put("profile.default_content_settings.cookies",2); //禁止cookies

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs",opts);
        DesiredCapabilities chromeCaps = DesiredCapabilities.chrome();
        chromeCaps.setCapability(ChromeOptions.CAPABILITY,options);

        //新建一个谷歌浏览器对象(diver)
        WebDriver driver = new ChromeDriver(chromeCaps);
        //通过driver控制浏览器打开链接(url) 登陆地址
        //String url = "https://home.51cto.com/index?reback=http://www.51cto.com/";
        driver.get(url);
        WebElement webElement = driver.findElement(By.id("login-wechat"));
        webElement = webElement.findElement(By.className("userPassLogin"));
        webElement = webElement.findElement(By.tagName("a"));
        webElement.click();
        //按照id和name元素，执行输入按键和点击
        //输入账号，按照id直找元素
        driver.findElement(By.id("loginform-username")).sendKeys("jiance520@163.com");
        //输入密码
        driver.findElement(By.id("loginform-password")).sendKeys("1983love");
        //Thread.sleep(5000);
        //点击登陆
        driver.findElement(By.name("login-button")).click();

        //页面标题
        String tigle =driver.getTitle();
        //页面偌
        String html = driver.getPageSource();
        System.out.println("-----html:"+html);
        Thread.sleep(100000); //鼠标放上去显示html,打开复制。
        driver.close();
        return html;
    }
}
