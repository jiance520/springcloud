package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.S_role;
import com.service.IS_roleService;
import com.utils.MapToBeanUtil;
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
import java.io.File;
import java.util.Iterator;
import java.util.Map;

@Controller
public class S_roleController implements ServletContextAware {
    private static String primarynameKey = "pidnamedjj";
    private ServletContext application;
    @Resource
    private IS_roleService iS_roleService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/s_roleInsert",produces = "application/json;chart=UTF-8")
    public String s_roleInsert(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfiledjj){
        System.out.println("-----params36:"+params.toString());
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()){
            String entryKey = iterator.next().toString();
            if(entryKey.equals("driverNamedjj")||entryKey.equals("datasourceUrldjj")||entryKey.equals("userNamedjj")||entryKey.equals("passworddjj")||entryKey.equals("pronamedjj")||entryKey.equals("tabnamedjj")||entryKey.contains("pidnamedjj")||entryKey.equals("acttypedjj")||entryKey.equals("excelfiledjj")){
                iterator.remove();
            }
        }
        S_role s_role = (S_role) MapToBeanUtil.backInstanceMapBean(new S_role(),params);
        int i = iS_roleService.insertSelective(s_role);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/s_roleDelete",produces = "application/json;chart=UTF-8")
    public String s_roleDelete(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        int i = iS_roleService.deleteByPrimaryKey(Integer.valueOf(primaryval));
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/s_roleUpdate",produces = "application/json;chart=UTF-8")
    public String s_roleUpdate(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfiledjj) throws Exception {
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
        S_role s_role = (S_role) MapToBeanUtil.backInstanceMapBean(new S_role(),params);
        int i = iS_roleService.updateByPrimaryKeySelective(s_role);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/s_roleSelect",produces = "application/json;chart=UTF-8")
    public String s_roleSelect(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        S_role s_role = iS_roleService.selectByPrimaryKey(Integer.valueOf(primaryval));
        return JSON.toJSONString(s_role);
    }
}