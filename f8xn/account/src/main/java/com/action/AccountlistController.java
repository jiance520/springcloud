package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.Accountlist;
import com.service.IAccountlistService;
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
public class AccountlistController implements ServletContextAware {
    private static String primarynameKey = "pidnamedjj";
    private ServletContext application;
    @Resource
    private IAccountlistService iAccountlistService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/accountlistInsert",produces = "application/json;chart=UTF-8")
    public String accountlistInsert(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfiledjj){
        System.out.println("-----params36:"+params.toString());
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()){
            String entryKey = iterator.next().toString();
            if(entryKey.equals("driverNamedjj")||entryKey.equals("datasourceUrldjj")||entryKey.equals("userNamedjj")||entryKey.equals("passworddjj")||entryKey.equals("pronamedjj")||entryKey.equals("tabnamedjj")||entryKey.contains("pidnamedjj")||entryKey.equals("acttypedjj")||entryKey.equals("excelfiledjj")){
                iterator.remove();
            }
        }
        Accountlist accountlist = (Accountlist) MapToBeanUtil.backInstanceMapBean(new Accountlist(),params);
        int i = iAccountlistService.insertSelective(accountlist);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/accountlistDelete",produces = "application/json;chart=UTF-8")
    public String accountlistDelete(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        int i = iAccountlistService.deleteByPrimaryKey(Long.valueOf(primaryval));
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/accountlistUpdate",produces = "application/json;chart=UTF-8")
    public String accountlistUpdate(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfiledjj) throws Exception {
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
        Accountlist accountlist = (Accountlist) MapToBeanUtil.backInstanceMapBean(new Accountlist(),params);
        int i = iAccountlistService.updateByPrimaryKeySelective(accountlist);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/accountlistSelect",produces = "application/json;chart=UTF-8")
    public String accountlistSelect(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        Accountlist accountlist = iAccountlistService.selectByPrimaryKey(Long.valueOf(primaryval));
        return JSON.toJSONString(accountlist);
    }
}