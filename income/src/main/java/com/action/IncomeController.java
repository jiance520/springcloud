package com.action;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.service.DBDaoTest;
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
    @RequestMapping(value = "queryIn",produces = {"application/json; charset=UTF-8"})
    public Object queryIn(HttpSession session,@RequestParam List listParams){
        System.out.println("-----listParams:"+listParams);

        String collectionName = null;
        Object object = session.getAttribute("collectionName");
        if(object!=null&&!"".equals(object)){
            collectionName = object.toString();
        }
        if(collectionName==null||"".equals(collectionName)){
            collectionName = "income";
        }

        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("rowName",new BasicDBObject("$in",listParams));
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
    //模糊查询n个字段rowName="come",name=""
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
    private void init(HttpSession session,Map<String,Object> map){
    }
}
