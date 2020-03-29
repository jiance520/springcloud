package com.utils;

import com.alibaba.fastjson.JSON;
import com.entity.Userinfo;
import com.service.IUserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class UserinfoAddController implements ServletContextAware {
    private static String primarynameKey = "pidnamedjj";
    private ServletContext application;
    @Resource
    private IUserinfoService iUserinfoService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "loginAction",produces = "application/json;chart=UTF-8")
    public String loginAction(String username, String password, HttpSession session, HttpServletRequest request, @RequestParam(required = false) Map<String, Object> params) throws Exception {
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
    @ResponseBody
    @RequestMapping(value = "userinfoMzInsert",produces = "application/json;chart=UTF-8")
    public String userinfoMzInsert(HttpSession session, HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params-userinfoMzInsert:"+params.toString());
        String username = request.getParameter("mzusername");
        String password = request.getParameter("mzpassword1");
        try {
            password=UserPasswordUtil.encode(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String telphone = request.getParameter("mztelphone");
        String email = request.getParameter("mzemail");
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        params.put("telphone",telphone);
        params.put("email",email);
        Userinfo userinfo = (Userinfo)MapToBeanUtil.backInstanceMapBean(new Userinfo(),params);
        int i = iUserinfoService.insertSelective(userinfo);
        if(i==1){
            session.setAttribute("username",username);
            session.setAttribute("password",password);
        }
        return JSON.toJSONString(i);
    }
}