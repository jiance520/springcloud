package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.Course;
import com.service.ICourseService;
import com.utils.JdbcUtil;
import com.utils.MapToBeanUtil;
import com.utils.My51ctoChromeSpider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController implements ServletContextAware {
    private static String primarynameKey = "pidnamedjj";
    private ServletContext application;
    @Resource
    private ICourseService iCourseService;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/courseInsert",produces = "application/json;chart=UTF-8")
    public String courseInsert(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfiledjj){
        System.out.println("-----params36:"+params.toString());
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()){
            String entryKey = iterator.next().toString();
            if(entryKey.equals("driverNamedjj")||entryKey.equals("datasourceUrldjj")||entryKey.equals("userNamedjj")||entryKey.equals("passworddjj")||entryKey.equals("pronamedjj")||entryKey.equals("tabnamedjj")||entryKey.contains("pidnamedjj")||entryKey.equals("acttypedjj")||entryKey.equals("excelfiledjj")){
                iterator.remove();
            }
        }
        Course course = (Course)MapToBeanUtil.backInstanceMapBean(new Course(),params);
        int i = iCourseService.insertSelective(course);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/courseDelete",produces = "application/json;chart=UTF-8")
    public String courseDelete(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        int i = iCourseService.deleteByPrimaryKey(Integer.valueOf(primaryval));
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/courseUpdate",produces = "application/json;chart=UTF-8")
    public String courseUpdate(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params,@RequestParam(required = false) MultipartFile[] excelfiledjj) throws Exception {
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
        Course course = (Course) MapToBeanUtil.backInstanceMapBean(new Course(),params);
        int i = iCourseService.updateByPrimaryKeySelective(course);
        return JSON.toJSONString(i);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/courseSelect",produces = "application/json;chart=UTF-8")
    public String courseSelect(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params.toString());
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = params.get(primaryname).toString();
        Course course = iCourseService.selectByPrimaryKey(Integer.valueOf(primaryval));
        return JSON.toJSONString(course);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/spider51cto",produces = "application/json;chart=UTF-8")
    public String spider51cto(HttpServletRequest request, @RequestParam(required = false) Map<String,Object> params) throws InterruptedException {
        System.out.println("-----params:"+params.toString());
        //String primaryname = request.getParameter(primarynameKey+1);
        //String primaryval = params.get(primaryname).toString();
        Course course = new Course();
        int j = 0;
        for (int i = 0; i <My51ctoChromeSpider.pagenums ; i++) {
            //for (int i = 0; i <1 ; i++) {
            if(i>=2){
                My51ctoChromeSpider.pageclick65=5; //下一页节点改变。
            }
            if(i>=1){
                My51ctoChromeSpider.page65=5; //下一页节点改变。
            }
            if(My51ctoChromeSpider.pagemax>=2){
                My51ctoChromeSpider.pageclick65=5; //下一页节点改变。
            }
            if(My51ctoChromeSpider.pagemax>=1){
                My51ctoChromeSpider.page65=5; //下一页节点改变。
            }

            List<HashMap> hashMapList = My51ctoChromeSpider.buy51cto("docker");
            for(HashMap hashMap:hashMapList){
                course=new Course();
                Object object = MapToBeanUtil.backInstanceMapBean(course,hashMap);
                System.out.println("-----object:"+object.toString());
                course = (Course)object;
                //int j = iCourseService.insertSelective(course);
                JdbcUtil jdbcUtil = new JdbcUtil();
                String sql = "INSERT INTO course(stitle,snumman,sscore,sprice,sprice2,shuodong,stime,sauthor,sdetail,sdate) VALUES (\"" +
                        course.getStitle()+"\",\""+course.getSnumman()+"\",\""+course.getSscore()+"\","+course.getSprice()+",\""
                        +course.getSprice2()+"\",\""+course.getShuodong()+"\",\""+course.getStime()+"\",\""+course.getSauthor()+"\",\""+
                        course.getSdetail()+"\",NOW())";
                j=jdbcUtil.executeUpdate(sql);
            }
        }
        My51ctoChromeSpider.closeDrive();
        return JSON.toJSONString(j);
    }
}