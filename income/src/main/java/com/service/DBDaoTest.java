package com.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CursorPreparer;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class DBDaoTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    //保存一个。
    public <T> T save(T bean){
        return mongoTemplate.save(bean);
    }
    public <T> T save(T bean,String collectionName){
        return mongoTemplate.save(bean,collectionName);
    }
    //保存多个
    public <T> Collection<T> insert(Collection<? extends T> objectsToSave, Class<?> entityClass){
       return mongoTemplate.insert(objectsToSave,entityClass);
    }
    //保存多个
    public <T> Collection<T> insertAll(Collection<? extends T> objectsToSave){
       return mongoTemplate.insertAll(objectsToSave);
    }
    //根据键值查单个
    public <T> T findOne(Class<T> entityClass,String column,String value) {
        Query query = new Query(Criteria.where(column).is(value));
        return mongoTemplate.findOne(query, entityClass);
    }
    public <T> T findOne(Class<T> entityClass,String column,String value,String collectionName) {
        Query query = new Query(Criteria.where(column).is(value));
        return mongoTemplate.findOne(query, entityClass,collectionName);
    }

    public <T> List<T> findAll(Class<T> entityClass) {
        return mongoTemplate.findAll(entityClass);
    }

    public <T> List<T> findAll(Class<T> entityClass, String collectionName) {
        return mongoTemplate.findAll(entityClass,collectionName);
    }

    public DeleteResult remove(Object object) {
        return mongoTemplate.remove(object);
    }
    public DeleteResult remove(Object object, String collectionName) {
        return mongoTemplate.remove(object,collectionName);
    }
    //限制了范围，只能是map，需要改进
    public UpdateResult upsert( Class<?> entityClass,Map map,String key){
        Query query = new Query(Criteria.where(key).is(key));
        Update update = new Update().set(key, map.get(key));
        return mongoTemplate.upsert(query,update,entityClass);
    }
}

