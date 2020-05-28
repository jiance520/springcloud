package com.action;

import com.alibaba.druid.support.json.JSONUtils;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.service.DBDaoTest;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/*
	* postman发送List参数，发送数据使用formdata,多个键名跟List接收变量名一致！>选Body>form-data>添加相同键值的多个参数。后台接收@RequestParam List<String> rowName //接收多个同名的参数，
	*
postman发送数组，发送数据同list,使用form-data,  后台接收多个同名的参数，@RequestParam String[] rowName，参数名跟发送变量名一样！可以用List或数组接收！
	*
postman发送带数组的json,（重点）POST>Body>raw>设置body的编码方式为raw，raw是发送纯文本，不包含任何空格的编码方式，同时将header的Content-Type设置为application/json，后台接收，使用@RequestBody Object object或@RequestBody Map<String,List>指定转换内容，不能使用 @RequestBody Map(因为如果map中有数组，则失败)和 @RequestParam Map!   json与map互相转换)

*/

@Controller
public class IncomeController {
    @Autowired
    private DBDaoTest dbDaoTest;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private  Mongo mongo;

    //如果不存在，则保存行，如果存在，则根据rowName修改行(删除再保存)。
    @RequestMapping(value="saveOrReplace",produces={"application/json; charset=UTF-8"})
    @ResponseBody
    //public Object save(Income map) {
    public Object saveOrReplace(HttpSession session,@RequestParam(required = false) Map<String, Object> map) {
        System.out.println("-----map:"+map);
        //情况一mongoTemplate.save(T 对象);  mongoTemplate.save(income);
        //java 插入成功后数据库的结果{ "_id" : "2", "price" : 23, "name" : "琅琊决", "_class" : "com.entity.Income" }，
        //情况二mongoTemplate.save(map,"income");如果插入map，必须指定表名！因为没有绑定集合@Document(collection = "income")，也没有绑定ID:@Id，插入结果会自动生成一个_id,没有_class
        //  { "_id" : ObjectId("5ec7974ba4174f4c246c0713"), "id" : "1", "name" : "琅琊决", "price" : "23" }
        String collectionName = null;
        Object object = map.get("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            object = session.getAttribute("collectionName");
            if(object!=null&&!"".equals(object)){
                collectionName = object.toString();
            }
        }
        map.remove("collectionName");
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }
        String rowName = null;
        object = map.get("rowName");
        if(object!=null&&!"".equals(object)){
            rowName = object.toString();
        }
        if(rowName==null||"".equals(rowName)){
            rowName = collectionName;
            map.put("rowName",rowName);
        }
        Query query = new Query(Criteria.where("rowName").is(rowName));
        boolean flag = mongoTemplate.exists(query,collectionName);
        System.out.println("-----flag:"+flag);
        if(!flag){
            return mongoTemplate.save(map,collectionName);//集合名默认就是用户名，用户登陆后用户名默认存在session中。键名collectionName，用户下的多个数据，在map中判断，键名rowName
            //return mongoTemplate.save(map);
        }
        else {
            //只能修改单个
            //Update update = new Update().set("price", map.get("price"));
            //return mongoTemplate.upsert(query,update,collectionName);

            //实现替换
            //mongoTemplate.remove(query,collectionName);
            //return mongoTemplate.save(map,collectionName);

            //官方自带的替换
            return mongoTemplate.findAndReplace(query,map,collectionName);

        }
    }

    //按字段rowName的查询条件删除行。
    @ResponseBody
    @RequestMapping(value="deleteRow",produces={"application/json; charset=UTF-8"})
    public Object deleteRow(HttpSession session,@RequestParam Map<String,Object> map){
        System.out.println("-----map:"+map);
        String collectionName = null;
        Object object = map.get("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            object = session.getAttribute("collectionName");
            if(object!=null&&!"".equals(object)){
                collectionName = object.toString();
            }
        }
        map.remove("collectionName");
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }
        String rowName = null;
        object = map.get("rowName");
        if(object!=null&&!"".equals(object)){
            rowName = object.toString();
        }
        if(rowName==null||"".equals(rowName)){
            rowName = collectionName;
        }
        Query query = new Query(Criteria.where("rowName").is(rowName)); //Query: { "rowName" : "income" }, Fields: { }, Sort: { }
        return mongoTemplate.remove(query,collectionName);

        //return mongoTemplate.findAllAndRemove();
        //return mongoTemplate.findAndRemove();
    }

    //条件查询主键rowName,in(1,2,3)
    @ResponseBody
    @RequestMapping(value = "queryIn",produces={"application/json;chart=UTF-8"})
    public Object queryIn(HttpSession session,@RequestParam List<String> rowName){//接收多个同名参数或使用数组代替？
        System.out.println("-----listParams:"+rowName);
        if(rowName.size()==0){
            rowName = new ArrayList();
            rowName.add("income2");
            rowName.add("oho");
        }

        String collectionName = null;
        Object object = session.getAttribute("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("rowName",new BasicDBObject("$in",rowName));

        List backList = new ArrayList();

        //方法三，查询有效
        MongoDatabase db = mongoTemplate.getDb();
        FindIterable<Document> findIterable = mongoTemplate.getCollection(collectionName).find(basicDBObject);//不能用bson，
        findIterable.forEach(new Block<Document>() {
            public void apply(Document _doc) {
                backList.add(_doc);
                System.out.println(_doc.toJson());//{ "_id" : { "$oid" : "5ecc6911a2f1d52df86055a8" }, "id" : "1", "name" : "琅琊决", "price" : "555", "rowName" : "income3" }
            }
        });
        return backList;
    }
    //条件查询主键rowName,in(1,2,3)
    @ResponseBody
    @RequestMapping(value = "queryArray",produces={"application/json;chart=UTF-8"})
    public Object queryArray(HttpSession session,@RequestParam String[] rowName){//接收多个同名参数或使用数组代替？
        System.out.println("-----String[]:"+rowName[0]);
        if(rowName.length==0){
            rowName[0]="oho";
            rowName[1]="income";
        }

        String collectionName = null;
        Object object = session.getAttribute("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("rowName",new BasicDBObject("$in",rowName));

        List backList = new ArrayList();

        //方法三，查询有效
        MongoDatabase db = mongoTemplate.getDb();
        FindIterable<Document> findIterable = mongoTemplate.getCollection(collectionName).find(basicDBObject);//不能用bson，
        findIterable.forEach(new Block<Document>() {
            public void apply(Document _doc) {
                backList.add(_doc);
                System.out.println(_doc.toJson());//{ "_id" : { "$oid" : "5ecc6911a2f1d52df86055a8" }, "id" : "1", "name" : "琅琊决", "price" : "555", "rowName" : "income3" }
            }
        });
        return backList;
    }
    //模糊查询0-n个字段rowName包含"come",并且name包含"xxx"
    @ResponseBody
    @RequestMapping(value = "queryRegex",produces = {"application/json; charset=UTF-8"})
    public Object queryRegex(HttpSession session,@RequestParam Map<String,Object> map){
        System.out.println("-----map:"+map);

        String collectionName = null;
        Object object = map.get("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            object = session.getAttribute("collectionName");
            if(object!=null&&!"".equals(object)){
                collectionName = object.toString();
            }
        }
        map.remove("collectionName");
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }

        //String rowName = null;
        //object = map.get("rowName");
        //if(object!=null&&!"".equals(object)){
        //    rowName = object.toString();
        //}
        //if(rowName==null||"".equals(rowName)){
        //    rowName = collectionName;
        //}

        Pattern pattern = null;
        BasicDBObject basicDBObject = new BasicDBObject();
        for(Map.Entry<String, Object> mapEntry: map.entrySet()){
            String key = mapEntry.getKey();
            Object value = mapEntry.getValue();
            if(value!=null){
                pattern=Pattern.compile(value.toString());
                basicDBObject=basicDBObject.append(key,pattern);
            }
        }
        List backList = new ArrayList();

        //方法三，查询有效
        FindIterable<Document> findIterable = mongoTemplate.getCollection(collectionName).find(basicDBObject);//不能用bson，
        findIterable.forEach(new Block<Document>() {
            public void apply(Document _doc) {
                backList.add(_doc);
                System.out.println(_doc.toJson());//{ "_id" : { "$oid" : "5ecc6911a2f1d52df86055a8" }, "id" : "1", "name" : "琅琊决", "price" : "555", "rowName" : "income3" }
            }
        });
        return backList;
    }
    //完全匹配查询0-n个字段rowName="income",并且name=""
    @ResponseBody
    @RequestMapping(value = "queryEquals",produces = {"application/json; charset=UTF-8"})
    public Object queryEquals(HttpSession session,@RequestParam Map<String,Object> map){
        System.out.println("-----map:"+map);

        String collectionName = null;
        Object object = map.get("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            object = session.getAttribute("collectionName");
            if(object!=null&&!"".equals(object)){
                collectionName = object.toString();
            }
        }
        map.remove("collectionName");
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }

        //String rowName = null;
        //object = map.get("rowName");
        //if(object!=null&&!"".equals(object)){
        //    rowName = object.toString();
        //}
        //if(rowName==null||"".equals(rowName)){
        //    rowName = collectionName;
        //}

        BasicDBObject basicDBObject = new BasicDBObject();
        for(Map.Entry<String, Object> mapEntry: map.entrySet()){
            String key = mapEntry.getKey();
            Object value = mapEntry.getValue();
            if(value!=null){
                basicDBObject=basicDBObject.append(key,value.toString());
            }
        }
        List backList = new ArrayList();

        //方法三，查询有效
        FindIterable<Document> findIterable = mongoTemplate.getCollection(collectionName).find(basicDBObject);//不能用bson，
        findIterable.forEach(new Block<Document>() {
            public void apply(Document _doc) {
                backList.add(_doc);
                System.out.println(_doc.toJson());//{ "_id" : { "$oid" : "5ecc6911a2f1d52df86055a8" }, "id" : "1", "name" : "琅琊决", "price" : "555", "rowName" : "income3" }
            }
        });
        return backList;
    }

    //条件查询0-n个字段,in(1,2,3)，posman header标记为Content-Type=application/json,使用body>raw发送map<String,数组/List>的json数据，
    //必须使用@RequestBody接收，
    //不能发送要查询的字段外的其它参数
    @ResponseBody
    @RequestMapping(value = "queryAnyIn",produces={"application/json;chart=UTF-8"})
    public Object queryAnyIn(HttpSession session,@RequestBody Map<String,List> map){//String是字段名 ，List包含字段的值
        //json与map互相转换,https://blog.csdn.net/cloudzpc/article/details/79458574
        System.out.println("-----map:"+map);

        List list = null;
        //测试数据
        //if(map.isEmpty()){
        //    list = new ArrayList();
        //    list.add("income");
        //    map.put("rowName",list);
        //}
        String collectionName = null;
        Object object = session.getAttribute("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }
//
//
        BasicDBObject basicDBObject = new BasicDBObject();
        BasicDBObject basicDBObjectand = new BasicDBObject();
//
        for(Map.Entry<String, List> mapEntry: map.entrySet()){
            String key = mapEntry.getKey();
            List value = mapEntry.getValue();
            if(value.size()!=0){

                //basicDBObject=basicDBObject.append(key,value.toString());
                basicDBObject.put(key,new BasicDBObject("$in",map.get(key)));
            }
            else{
                System.out.println("-----发送的list为空:"+value);
            }
        }
//
        //basicDBObject.put("rowName",new BasicDBObject("$in",map.get("rowName")));
//
        List backList = new ArrayList();
//
        //方法三，查询有效
        MongoDatabase db = mongoTemplate.getDb();
        FindIterable<Document> findIterable = mongoTemplate.getCollection(collectionName).find(basicDBObject);//不能用bson，
        findIterable.forEach(new Block<Document>() {
            public void apply(Document _doc) {
                backList.add(_doc);
                System.out.println(_doc.toJson());//{ "_id" : { "$oid" : "5ecc6911a2f1d52df86055a8" }, "id" : "1", "name" : "琅琊决", "price" : "555", "rowName" : "income3" }
            }
        });
        return backList;
    }
    private void init(HttpSession session,Map<String,Object> map){
    }
}
