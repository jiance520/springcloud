package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.T_flatweapon_correspond;
import com.service.IT_flatweapon_correspondService;
import com.utils.MapToBeanUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

@Controller
public class T_flatweapon_correspondController implements ServletContextAware {
    private static String primarynameKey = "pidname";
    private ServletContext application;
    @Resource
    private IT_flatweapon_correspondService iT_flatweapon_correspondService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_flatweapon_correspondInsert",produces = "application/json;chart=UTF-8")
    public String t_flatweapon_correspondInsert(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfile){
        System.out.println("-----params36:"+params.toString());
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()){
            String entryKey = iterator.next().toString();
            if(entryKey.equals("proname")||entryKey.equals("tabname")||entryKey.contains("pidname")||entryKey.equals("acttype")||entryKey.equals("excelfile")){
                iterator.remove();
            }
        }
        T_flatweapon_correspond t_flatweapon_correspond = (T_flatweapon_correspond)MapToBeanUtil.backInstanceMapBean(new T_flatweapon_correspond(),params);
        int i = iT_flatweapon_correspondService.insertSelective(t_flatweapon_correspond);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_flatweapon_correspondDelete",produces = "application/json;chart=UTF-8")
    public String t_flatweapon_correspondDelete(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        String primaryname2 = request.getParameter(primarynameKey+2);
        String primaryval2 = params.get(primaryname2).toString();
        int i = iT_flatweapon_correspondService.deleteByPrimaryKey(Integer.valueOf(primaryval),Integer.valueOf(primaryval2));
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_flatweapon_correspondUpdate",produces = "application/json;chart=UTF-8")
    public String t_flatweapon_correspondUpdate(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfile) throws Exception {
        System.out.println("-----params:"+params.toString());
        String path = application.getRealPath("img")+ File.separator;
        System.out.println("-----img/product:"+path);
        //List<HashMap<String,Object>> mapList = PoiUtil.inxlsx(excelfile);//把接收的文件中的数据转为listmap。
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()){
            String entryKey = iterator.next().toString();
            if(entryKey.equals("proname")||entryKey.equals("tabname")||entryKey.contains("pidname")||entryKey.equals("acttype")||entryKey.equals("excelfile")){
                iterator.remove();
            }
        }
        T_flatweapon_correspond t_flatweapon_correspond = (T_flatweapon_correspond) MapToBeanUtil.backInstanceMapBean(new T_flatweapon_correspond(),params);
        int i = iT_flatweapon_correspondService.updateByPrimaryKeySelective(t_flatweapon_correspond);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/t_flatweapon_correspondSelect",produces = "application/json;chart=UTF-8")
    public String t_flatweapon_correspondSelect(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        String primaryname2 = request.getParameter(primarynameKey+2);
        String primaryval2 = params.get(primaryname2).toString();
        T_flatweapon_correspond t_flatweapon_correspond = iT_flatweapon_correspondService.selectByPrimaryKey(Integer.valueOf(primaryval),Integer.valueOf(primaryval2));
        return JSON.toJSONString(t_flatweapon_correspond);
    }
}
