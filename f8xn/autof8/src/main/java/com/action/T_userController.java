package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.T_user;
import com.service.IT_userService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class T_userController implements ServletContextAware {
    private ServletContext application;
    @Resource
    private IT_userService iT_userService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_userInsert",produces = "application/json;chart=UTF-8")
    public String t_userInsert(HttpServletRequest request){
        String commandership_name = request.getParameter("commandership_name");
        T_user t_user = new T_user();
        t_user.setName(commandership_name);
        int i = iT_userService.insertSelective(t_user);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_userUpdate",produces = "application/json;chart=UTF-8")
    public String t_userUpdate(HttpServletRequest request){
        String commandership_id = request.getParameter("commandership_id");
        T_user t_user = new T_user();
        t_user.setId(Integer.valueOf(commandership_id));
        int i = iT_userService.updateByPrimaryKeySelective(t_user);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_userSelect",produces = "application/json;chart=UTF-8")
    public String t_userSelect(HttpServletRequest request){
        String commandership_id = request.getParameter("commandership_id");
        T_user t_user = iT_userService.selectByPrimaryKey(Integer.valueOf(commandership_id));
        return JSON.toJSONString(t_user);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_userDelete",produces = "application/json;chart=UTF-8")
    public String t_userDelete(HttpServletRequest request){
        String commandership_id = request.getParameter("commandership_id");
        int i = iT_userService.deleteByPrimaryKey(Integer.valueOf(commandership_id));
        return JSON.toJSONString(i);
    }
}
