package com.dao;

import com.entity.Credit;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditMapper {
    int deleteByPrimaryKey(Integer credit);

    int insert(Credit record);

    int insertSelective(Credit record);

    Credit selectByPrimaryKey(Integer credit);

    int updateByPrimaryKeySelective(Credit record);

    int updateByPrimaryKey(Credit record);
}