package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.Userinfo;
import com.service.IUserinfoService;
import com.utils.MapToBeanUtil;
import com.utils.UserPasswordUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

@Controller
public class UserinfoController implements ServletContextAware {
    private static String primarynameKey = "pidnamedjj";
    private ServletContext application;
    @Resource
    private IUserinfoService iUserinfoService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @RequestMapping(value = "testModelAndView",produces = "application/json;chart=UTF-8")
    public ModelAndView testModelAndView(ModelAndView modelAndView,HttpSession httpSession,HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----paramstestModelAndView:"+params.toString());
        request.setAttribute("test","testModelAndViewvalue");
        Map map = new HashMap();
        map.put("testmap","testmapvalue");
        modelAndView.addAllObjects(map);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "loginAction",produces = "application/json;chart=UTF-8")
    public String loginAction(String username, String password, HttpSession session,HttpServletRequest request,@RequestParam(required = false) Map<String, Object> params) throws Exception {
        System.out.println("-----params:"+params);
        System.out.println("-----username:"+username);
        password=params.get("password").toString();
        username=params.get("username").toString();
        Map map = new HashMap();
        map.put("username",username);
        String pwd_encoded = UserPasswordUtil.encode(password);//进行加密
        System.out.println("-----pwd_encoded:"+pwd_encoded);
        map.put("password",pwd_encoded);
//        map.put("password",password);//不加密登陆
        Userinfo userinfo = iUserinfoService.selectLogin(map);
//        以下注释部分用来代替不注册用户登陆
//        if(manager==null){
//            manager = new Manager();
//            manager.setUsername(username);
//            manager.setPassword(pwd_encoded);
//            manager.setStatus((long)0);
//            manager.setName("管理员");
//            int num = imanagerService.insertSelective(manager);
//            System.out.println("-----manager:"+manager);
//        }
        if(userinfo!=null){
            session.setAttribute("users",userinfo);
        }

        Map<String,Object> hashmap = new HashMap<>();
        if(userinfo!=null) {
            hashmap.put("users", userinfo);
            return JSON.toJSONString(hashmap);
        }else {
            hashmap.put("fail", "帐号或密码错误");
            return JSON.toJSONString(hashmap);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "loginAction2",produces = "application/json;chart=UTF-8")
    public ModelAndView loginAction2(ModelAndView mav,String username, String password, HttpSession session,HttpServletRequest request) throws Exception {
        System.out.println("-----password:"+password);
        System.out.println("-----username:"+username);
        Map map = new HashMap();
        map.put("username",username);
        String pwd_encoded = UserPasswordUtil.encode(password);//进行加密
        System.out.println("-----pwd_encoded:"+pwd_encoded);
        map.put("password",pwd_encoded);
//        map.put("password",password);
        Userinfo userinfo = iUserinfoService.selectLogin(map);
//        以下注释部分用来代替注册用户
//        if(manager==null){
//            manager = new Manager();
//            manager.setUsername(username);
//            manager.setPassword(pwd_encoded);
//            manager.setStatus((long)0);
//            manager.setName("管理员");
//            int num = imanagerService.insertSelective(manager);
//            System.out.println("-----manager:"+manager);
//        }
        if(userinfo!=null){
            session.setAttribute("users",userinfo);
        }
        request.setAttribute("username",username);
        Map<String,Userinfo> hashmap = new HashMap<String,Userinfo>();
        if(userinfo!=null) {
//            mav.addAllObjects(hashmap);
            mav.addObject("userinfo",userinfo);
            mav.setViewName("redirect:/password.jsp?username="+username);
            //mav.setViewName("redirect:/deng_web/hmanager.html?username="+username);
            return mav;
        }else {
            mav.addObject("fail","帐号或密码错误");
            mav.setViewName("/deng_index");
            return mav;
        }
    }
    //登陆验证码
    @ResponseBody
    @RequestMapping(value = "CheckCodeAction",produces = "application/json;chart=UTF-8")
    public String checkCodeAction( String value, HttpSession session){
        Object  vobj = session.getAttribute("rand");
        if(value.equalsIgnoreCase((String)vobj)){
            System.out.println("验证码正确");
            return "true";
        }
        else{
            System.out.println("验证码错误");
            return "false";
        }
    }
    @ResponseBody
    @RequestMapping("ImageAction")
    public void imageAction(String value, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String StrFont=this.randstr();
        //对session赋值
        HttpSession ses=request.getSession();
        ses.setAttribute("rand",StrFont);
        System.out.println("-----rand:"+StrFont);
        String fontName="宋体";
        int fontSize=30;
        int width=105;
        int height=40;
        Font font=new Font(fontName,Font.BOLD,fontSize);
        BufferedImage StrImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d=(Graphics2D)StrImage.getGraphics();
        //设置背景色
        g2d.setBackground(getRandColor(100,200));
        //填充背景
        g2d.clearRect(0,0,width,height);
        //设置字体色
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        /*
                      写入随机字符串
        StrFont - 要呈现的 String
        x - 呈现 String 位置的 x 坐标
        y - 呈现 String 位置的 y 坐标
        */
        g2d.drawString(StrFont,1,30);
        //释放此图形的上下文以及它使用的所有系统资源
        g2d.dispose();

        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream os = response.getOutputStream();
        //写入输出流
        ImageIO.write(StrImage,"jpg",os);
        //刷新输出流
        os.flush();
        //关闭输出流
        os.close();
    }
    //取得随机字符串
    protected String randstr(){
        String str="";
        String charstr="23456789ABDEFIJLQRTYabdefghijqrty";
        char[] arrchar=charstr.toCharArray();
        int length=arrchar.length;
        length=length-1;
        Random rd=new Random();
        char ct;
        int it;
        for(int i=0;i<6;i++)
        {
            it=rd.nextInt(length);
            ct=arrchar[it];
            str=str+String.valueOf(ct);
        }
        return "123456";//str
    }
    //获得随机颜色
    protected Color getRandColor(int fc, int bc){
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/userinfoInsert",produces = "application/json;chart=UTF-8")
    public String userinfoInsert(ModelAndView modelAndView, HttpSession httpSession, @RequestParam(required = false) Map<String, Object> params, @RequestParam(required = false) MultipartFile[] excelfiledjj){
        System.out.println("-----params36:"+params.toString());
        httpSession.setAttribute("test","testActionConnParamssessionback");
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()){
            String entryKey = iterator.next().toString();
            if(entryKey.equals("driverNamedjj")||entryKey.equals("datasourceUrldjj")||entryKey.equals("userNamedjj")||entryKey.equals("passworddjj")||entryKey.equals("pronamedjj")||entryKey.equals("tabnamedjj")||entryKey.contains("pidnamedjj")||entryKey.equals("acttypedjj")||entryKey.equals("excelfiledjj")){
                iterator.remove();
            }
        }
        Userinfo userinfo = (Userinfo) MapToBeanUtil.backInstanceMapBean(new Userinfo(),params);
        int i = iUserinfoService.insertSelective(userinfo);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/userinfoDelete",produces = "application/json;chart=UTF-8")
    public String userinfoDelete(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        int i = iUserinfoService.deleteByPrimaryKey(Integer.valueOf(primaryval));
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/userinfoUpdate",produces = "application/json;chart=UTF-8")
    public String userinfoUpdate(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfiledjj) throws Exception {
        System.out.println("-----params:"+params.toString());
        String path = application.getRealPath("img")+ File.separator;
        System.out.println("-----img/product:"+path);
        //List<HashMap<String,Object>> mapList = PoiUtil.inxlsx(excelfiledjj);//把接收的文件中的数据转为listmap。
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()){
            String entryKey = iterator.next().toString();
            if(entryKey.equals("driverNamedjj")||entryKey.equals("datasourceUrldjj")||entryKey.equals("userNamedjj")||entryKey.equals("passworddjj")||entryKey.equals("pronamedjj")||entryKey.equals("tabnamedjj")||entryKey.contains("pidnamedjj")||entryKey.equals("acttypedjj")||entryKey.equals("excelfiledjj")){
                iterator.remove();
            }
        }
        Userinfo userinfo = (Userinfo) MapToBeanUtil.backInstanceMapBean(new Userinfo(),params);
        int i = iUserinfoService.updateByPrimaryKeySelective(userinfo);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/userinfoSelect",produces = "application/json;chart=UTF-8")
    public String userinfoSelect(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        Userinfo userinfo = iUserinfoService.selectByPrimaryKey(Integer.valueOf(primaryval));
        return JSON.toJSONString(userinfo);
    }
}