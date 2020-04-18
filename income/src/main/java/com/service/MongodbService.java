package com.service;

import com.entity.Income;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class MongodbService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存对象
     * @param income
     * @return
     */
    public String saveObj(Income income) {
        income.setCreateTime(new Date());
        income.setUpdateTime(new Date());
        mongoTemplate.save(income);
        return "添加成功";
    }

    /**
     * 查询所有
     * @return
     */
    public List<Income> findAll() {
        return mongoTemplate.findAll(Income.class);
    }

    /***
     * 根据id查询
     * @param id
     * @return
     */
    public Income getIncomeById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Income.class);
    }

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    public Income getIncomeByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Income.class);
    }
    //根据指定字段的指定值查询
    public Income getIncomeByColumn(String column,String value) {
        Query query = new Query(Criteria.where(column).is(value));
        return mongoTemplate.findOne(query, Income.class);
    }


    /**
     * 更新对象
     *
     * @param income
     * @return
     */
    public String updateIncome(Income income) {
        Query query = new Query(Criteria.where("_id").is(income.getId()));
        Update update = new Update().set("publish", income.getPublish()).set("info", income.getInfo()).set("updateTime",
                new Date());
        // updateFirst 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, Income.class);
        // updateMulti 更新查询返回结果集的全部
        // mongoTemplate.updateMulti(query,update,Income.class);
        // upsert 更新对象不存在则去添加
        // mongoTemplate.upsert(query,update,Income.class);
        return "success";
    }

    /***
     * 删除对象
     * @param income
     * @return
     */
    public String deleteIncome(Income income) {
        mongoTemplate.remove(income);
        return "success";
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public String deleteIncomeById(String id) {
        // findOne
        Income income = getIncomeById(id);
        // delete
        deleteIncome(income);
        return "success";
    }

    /**
     * 模糊查询
     * @param search
     * @return
     */
    public List<Income> findByLikes(String search){
        Query query = new Query();
        Criteria criteria = new Criteria();
        //criteria.where("name").regex(search);
        Pattern pattern = Pattern.compile("^.*" + search + ".*$" , Pattern.CASE_INSENSITIVE);
        criteria.where("name").regex(pattern);
        List<Income> lists = mongoTemplate.findAllAndRemove(query, Income.class);
        return lists;
    }
}
