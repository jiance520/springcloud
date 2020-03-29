package com.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

//由于注入了jdbcUtil，所以必须能在当前工程扫描到jdbcUtil？
@Controller
public class CommonController implements ServletContextAware {
    private static String tablenameKey = "tabnamedjj";
    private static String primarynameKey = "pidnamedjj";
    @Autowired
    private JdbcUtil jdbcUtil;
    //slf4j与log4j、log4j2:https://blog.csdn.net/HarderXin/article/details/80422903?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
    //Spring Boot 日志配置(超详细),https://blog.csdn.net/Inke88/article/details/75007649?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
    //SpringBoot 项目中使用Log4j2详细（避坑） https://blog.csdn.net/RyanDon/article/details/82589989

    //slf4j是一个适配器(是一个为Java程序提供日志输出的统一接口，就比如JDBC一样，单独的slf4j是不能工作的，必须搭配其他log4j或者log4j2),可助log4j 1.x升级log4j2。slf4j-api
    //log4j是真正实现日志功能的产品，是apache实现的一个开源日志java组件,log4j.jar log4j-api+log4j-core
    //logback-spring.xml是springboot自带spring-boot-starter-logging(不用添加依赖),springboot不支持log4j,支持log4j2。
    //logback同样是由log4j的作者设计完成的，用来取代log4j的一个日志框架，是slf4j的原生实现
    //Log4j2是log4j 1.x和logback的改进版，(无锁异步、更快)，spring-boot-starter-log4j2
    //slf4j与log4j2的桥接包：log4j-slf4j-impl
    //使用slf4j接口的好处是当项目需要更换日志框架的时候，只需要更换jar和配置，不需要更改相关java代码
    //日志级别从低到高分为：TRACE < DEBUG < INFO < WARN < ERROR < FATAL。
    //Spring Boot中默认配置ERROR、WARN和INFO级别的日志输出到控制台。
    //Spring Boot默认情况下，将日志输出到控制台，不会写到日志文件。
    //Spring Boot官方推荐优先使用带有-spring的文件名作为你的日志配置（如使用logback-spring.xml
    //Logback：logback-spring.xml, logback-spring.groovy, logback.xml, logback.groovy
    //Log4j：log4j-spring.properties, log4j-spring.xml, log4j.properties, log4j.xml
    //Log4j2：log4j2-spring.xml, log4j2.xml
    //log4j、logback、log4j2都是一种日志具体实现框架，所以既可以单独使用也可以结合slf4j一起搭配使用
    //Log4j2：如项目中有导入spring-boot-starter-web依赖包记得去掉spring自带的日志依赖spring-boot-starter-logging

    //org.slf4j.Logger用于项目启动时输出日志
    //private final Logger logger = LoggerFactory.getLogger(getClass());
    //slf4j+logback直接启动类能在控制台输出日志
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    //org.apache.log4j.Logger 使用1万最多，最后更新2012年，启动类不能在控制台输出日志,只在项目启动时才在控制台输出和保存到文件。
    //private static Logger logger = Logger.getLogger(CommonController.class.getName());
    //private Logger logger = Logger.getLogger(getClass());
    private ServletContext application;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.application = servletContext;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/selectAll",produces = "application/json;chart=UTF-8")
    public String selectAll(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params);
        String tablename = request.getParameter(tablenameKey);
        String backStr = null;

        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");

        List<HashMap> listMap = new ArrayList<>();
        String sql = "select * from "+tablename;
        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            listMap = jdbcUtil.exectueQuery(sql);
            System.out.println("-----listMap:"+listMap);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            listMap = jdbcUtil.exectueQuery(sql);
        }
        return JSON.toJSONString(listMap);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/selectOne",produces = "application/json;chart=UTF-8")
    public String selectOne(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params92:"+params);
        String tablename = request.getParameter(tablenameKey);
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = request.getParameter(primaryname);

        HashMap hashMaps = null;

        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        String sql = "SELECT * FROM "+tablename+" WHERE "+primaryname+" = "+primaryval;

//        String sendValue = redisTemplate.opsForValue().get("redisPassworddjj");//opsForValue()获取缓存。再get获取指定的key

        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            //为了保存注入bean里的属性值，永远不要新建JdbcUtil对象，只用set/get处理属性。
            //静态属性值是所有实例共享。
            //如果传递过来值，则使用set修改bean的默认值，使用默认方法0。
            //如果新构造对象，会替换注入的bean,不会保留配置文件属性值。并改变JdbcUtil的属性值。此操作不可逆！spring容器中的注解组件只加载一次，单例模式，所以永久保留修改！
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            hashMaps = jdbcUtil.queryOne(sql);
            System.out.println("-----hashMaps122:"+hashMaps);
        }
        else{
            //推荐使用此方法。
            //如果没有传递过来值，则使用注入的bean读取的配置文件的值。使用方法2.
            //调用动态的方法，使用动态的属性，动态的属性值可以通过bean获取配置文件值
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            hashMaps = jdbcUtil.queryOne2(sql);
            System.out.println("-----hashMap130s:"+hashMaps);
        }
        return JSON.toJSONString(hashMaps);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/deleteOne",produces = "application/json;chart=UTF-8")
    public Object deleteOne(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params);
        String tablename = request.getParameter(tablenameKey);
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = request.getParameter(primaryname);
        String backStr = null;

        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        int i = 0;
        String sql = "DELETE FROM "+tablename+" WHERE "+primaryname+" = "+primaryval;

        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate(sql);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate2(sql);
        }
        return JSON.toJSONString(i);
    }
   //不要把自增的主键，以及后台要加入的时间，传给后台。除必须key，key必须跟表字段一样。不能多也不能少。保证顺序。
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/insertOne",produces = "application/json;chart=UTF-8")
    public String insertOne(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params);
        String sql = "";
        String tablename = request.getParameter(tablenameKey);
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = request.getParameter(primaryname);
        String primaryname2 = request.getParameter(primarynameKey+2);
        String backStr = null;

        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        int i = 0;

        sql="INSERT INTO "+tablename+"(";
        for(HashMap.Entry<String,Object> entry:params.entrySet()){
            String entryKey = entry.getKey();
            if(!entryKey.equals("driverNamedjj")&&!entryKey.equals("datasourceUrldjj")&&!entryKey.equals("userNamedjj")&&!entryKey.equals("passworddjj")&&!entryKey.equals("pronamedjj")&&!entryKey.equals("tabnamedjj")&&!entryKey.contains("pidnamedjj")&&!entryKey.equals(primaryname2)&&!entryKey.equals("acttypedjj")&&!entryKey.equals("excelfiledjj")){
                sql = sql +","+ entry.getKey();
            }
        }
        sql=sql+") VALUES(";
        for(HashMap.Entry<String,Object> entry:params.entrySet()){
            String entryKey = entry.getKey();
            if(!entryKey.equals("driverNamedjj")&&!entryKey.equals("datasourceUrldjj")&&!entryKey.equals("userNamedjj")&&!entryKey.equals("passworddjj")&&!entryKey.equals("pronamedjj")&&!entryKey.equals("tabnamedjj")&&!entryKey.contains("pidnamedjj")&&!entryKey.equals(primaryname2)&&!entryKey.equals("acttypedjj")&&!entryKey.equals("excelfiledjj")){
                sql = sql +",\""+ entry.getValue().toString()+"\"";
            }
        }
        sql=sql+")";
        sql=sql.replaceAll("\\(,","(");
        System.out.println("-----insertOnesql:"+sql);

        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate(sql);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate2(sql);
        }
        return JSON.toJSONString(i);//return JSONSerializer.toJSON(json);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/insertOneAutoId",produces = "application/json;chart=UTF-8")
    public String insertOneAutoId(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params);
        String sql = "";
        String tablename = request.getParameter(tablenameKey);
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryname2 = request.getParameter(primarynameKey+2);
        String backStr = null;

        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        int i = 0;

        if(tablename==null||"".equals(tablename)){
            backStr="tablename为空,";
        }
        sql="INSERT INTO "+tablename+"(";
        for(HashMap.Entry<String,Object> entry:params.entrySet()){
            String entryKey = entry.getKey();
            if(!entryKey.equals("driverNamedjj")&&!entryKey.equals("datasourceUrldjj")&&!entryKey.equals("userNamedjj")&&!entryKey.equals("passworddjj")&&!entryKey.equals("pronamedjj")&&!entryKey.equals("tabnamedjj")&&!entryKey.contains("pidnamedjj")&&!entryKey.equals(primaryname2)&&!entryKey.equals("acttypedjj")&&!entryKey.equals("excelfiledjj")){
                sql = sql + ","+ entry.getKey();
            }
        }
        sql=sql+") VALUES(";
        for(HashMap.Entry<String,Object> entry:params.entrySet()){
            String entryKey = entry.getKey();
            if(!entryKey.equals("driverNamedjj")&&!entryKey.equals("datasourceUrldjj")&&!entryKey.equals("userNamedjj")&&!entryKey.equals("passworddjj")&&!entryKey.equals("pronamedjj")&&!entryKey.equals("tabnamedjj")&&!entryKey.contains("pidnamedjj")&&!entryKey.equals(primaryname2)&&!entryKey.equals("acttypedjj")&&!entryKey.equals("excelfiledjj")){
                sql = sql +",\""+ entry.getValue().toString()+"\"";
            }
        }
        sql=sql+")";
        System.out.println("-----insertOnesql:"+sql);

        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate(sql);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate2(sql);
        }
        return JSON.toJSONString(i);//return JSONSerializer.toJSON(json);
    }
    //不要把自增的主键，以及后台要加入的时间，传给后台。除必须key，key必须跟表字段一样。不能多也不能少。保证顺序。
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/updateOne",produces = "application/json;chart=UTF-8")
    public String updateOne(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params){
        System.out.println("-----params:"+params);
        String sql = "";
        String tablename = request.getParameter(tablenameKey);
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = request.getParameter(primaryname);
        String primaryname2 = request.getParameter(primarynameKey+2);

        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        int i = 0;

        sql="UPDATE "+tablename+" SET ";
        for(HashMap.Entry<String,Object> entry:params.entrySet()){
            String entryKey = entry.getKey();
            if(!entryKey.equals("driverNamedjj")&&!entryKey.equals("datasourceUrldjj")&&!entryKey.equals("userNamedjj")&&!entryKey.equals("passworddjj")&&!entryKey.equals("pronamedjj")&&!entryKey.equals("tabnamedjj")&&!entryKey.contains("pidnamedjj")&&!entryKey.equals(primaryname2)&&!entryKey.equals("acttypedjj")&&!entryKey.equals("excelfiledjj")){
                sql = sql +entry.getKey()+"=\""+ entry.getValue().toString()+"\",";
            }
        }
        sql = sql.substring(0,sql.length()-1);//去掉最后一个,号
        sql=sql+" WHERE "+primaryname+"='"+primaryval+"'";
        System.out.println("-----insertOnesql:"+sql);

        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate(sql);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate2(sql);
        }
        return JSON.toJSONString(i);//return JSONSerializer.toJSON(json);
    }
    //接收sql语句，执行查询
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/exectueQueryAction",produces = "application/json;chart=UTF-8")
    public String exectueQueryAction(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params) {
        System.out.println("-----params:" + params);
        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        String sql = request.getParameter("exectueSql");
        List<HashMap> arrayList = new ArrayList<>();
        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            arrayList = jdbcUtil.exectueQuery(sql);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            arrayList = jdbcUtil.exectueQuery2(sql);
        }
        return JSON.toJSONString(arrayList);//return JSONSerializer.toJSON(json);
    }
    //接收sql语句，执行增删改
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/executeUpdateAction",produces = "application/json;chart=UTF-8")
    public String executeUpdateAction(HttpServletRequest request,@RequestParam(required = false) Map<String,Object> params) {
        System.out.println("-----params:" + params);
        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        String sql = request.getParameter("exectueSql");
        int i = 0;
        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate(sql);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            i = jdbcUtil.executeUpdate2(sql);
        }
        return JSON.toJSONString(i);//return JSONSerializer.toJSON(json);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/actionAll",produces = "application/json;chart=UTF-8")
    public String actionAll(HttpServletRequest request, @RequestParam(required=false) Map<String,Object> params) {
        System.out.println("-----params:"+params);
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = request.getParameter(primaryname);
        /*if(!"".equals(primaryname)&&!"".equals(primaryval)&&!params.containsKey(primaryname)){
            params.put(primaryname,primaryval);
        }*/
        Object object = null;
        if(params.isEmpty()){
            object = "params是空";
        }
        else{
            Iterator iterator = params.keySet().iterator();
            while (iterator.hasNext()){
                String entryKey = iterator.next().toString();
                if(entryKey.equals("driverNamedjj")||entryKey.equals("datasourceUrldjj")||entryKey.equals("userNamedjj")||entryKey.equals("passworddjj")||entryKey.equals("pronamedjj")||entryKey.equals("pidnamedjj2")||entryKey.equals("excelfiledjj")){
                    iterator.remove();
                }
            }
            System.out.println("-----params236:"+params);
            object = ActionUtil.actionAll(this,params);
            System.out.println("-----object:"+object.toString());
        }
        return JSON.toJSONString(object);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/actionAllTwo",produces = "application/json;chart=UTF-8")
    public String actionAllTwo(HttpServletRequest request, @RequestParam(required=false) Map<String,Object> params) {
        System.out.println("-----params:"+params);
        String primaryname = request.getParameter(primarynameKey+1);
        String primaryval = request.getParameter(primaryname);
        /*if(!"".equals(primaryname)&&!"".equals(primaryval)&&!params.containsKey(primaryname)){
            params.put(primaryname,primaryval);
        }*/
        String primaryname2 = request.getParameter(primarynameKey+2);
        String primaryval2 = request.getParameter(primaryname2);
        /*if(!"".equals(primaryname2)&&!"".equals(primaryval2)&&!params.containsKey(primaryname2)){
            params.put(primaryname2,primaryval2);
        }*/
        Object object = null;
        if(params.isEmpty()){
            object = "params是空";
        }
        else{
            Iterator iterator = params.keySet().iterator();
            while (iterator.hasNext()){
                String entryKey = iterator.next().toString();
                if(entryKey.equals("driverNamedjj")||entryKey.equals("datasourceUrldjj")||entryKey.equals("userNamedjj")||entryKey.equals("passworddjj")||entryKey.equals("pronamedjj")||entryKey.equals("excelfiledjj")){
                    iterator.remove();
                }
            }

            object = ActionUtil.actionAllTwo(this,params);
        }
        return JSON.toJSONString(object);
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/actionAutoAdd",produces = "application/json;chart=UTF-8")
    public String actionAutoAdd(HttpServletRequest request, @RequestParam(required=false) Map<String,Object> params) {
        System.out.println("-----params362:"+params);
        String tabnamedjj = request.getParameter("tabnamedjj");
        String driverNamedjj = request.getParameter("driverNamedjj");
        String datasourceUrldjj = request.getParameter("datasourceUrldjj");
        String userNamedjj = request.getParameter("userNamedjj");
        String passworddjj = request.getParameter("passworddjj");
        Object object = null;
        if(!"".equals(driverNamedjj)&&driverNamedjj!=null&&!"".equals(datasourceUrldjj)&&datasourceUrldjj!=null&&!"".equals(userNamedjj)&&userNamedjj!=null&&!"".equals(passworddjj)&&passworddjj!=null){
            jdbcUtil.setDriverName(driverNamedjj);
            jdbcUtil.setDatasourceUrl(datasourceUrldjj);
            jdbcUtil.setUserName(userNamedjj);
            jdbcUtil.setPassword(passworddjj);
            System.out.println("-----1jdbcUtil.toString:"+jdbcUtil.toString());
            object = jdbcUtil.columnListMysql(tabnamedjj);
        }
        else{
            System.out.println("-----2jdbcUtil.toString:"+jdbcUtil.toString());
            object = jdbcUtil.columnListMysql2(tabnamedjj);
        }
        return JSON.toJSONString(object);//return JSONSerializer.toJSON(json);
    };
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/testActionConnParams",produces = "application/json;chart=UTF-8")
    public String testActionConnParams(HttpServletRequest request, @RequestParam(required=false) Map<String,Object> params){
        System.out.println("-----test452:"+params);
        Connection connection = jdbcUtil.getConn();
        Connection connection2 = jdbcUtil.getDruidConn2();
        if(connection==null&&connection2==null){
            params.put("conn","数据库连接失败");
        }else {
            if(connection!=null){
                params.put("getConn","数据库连接正常，连接的是："+jdbcUtil.getDatasourceUrl());
            }
            if(connection2!=null){
                params.put("getDruidConn2","数据库连接正常，连接的是："+jdbcUtil.getDatasourceUrl2());
            }
            try {
                connection.close();
                connection = null;
                connection2.close();
                connection2 = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        jdbcUtil.closeConn();
        return JSON.toJSONString(params);
    }
    public static void main(String[] args) throws Exception {
//        LoginController loginController = new LoginController();
//        loginController.action();
        //测试前，一定要注入Bean,不包括JdbcUtil
        String name = "tom";
        String password = "123";
//        String sql = "INSERT INTO t_user VALUES(2,?,?)";
        //String sql = "INSERT INTO t_user VALUES(2,\"tom\",\"123\")";
//        int i = JdbcUtil.executeUpdate(sql,name,password);
//        int i = JdbcUtil.executeUpdate(sql);
//        System.out.println("-----i:"+i);
        JdbcUtil jdbcUtil = new JdbcUtil();
        Object object = jdbcUtil.exectueQuery("select * from t_user");
        logger.debug("--logger---test:"+object);
        /*logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");*/
        System.out.println("---test:"+object);
    }
}
