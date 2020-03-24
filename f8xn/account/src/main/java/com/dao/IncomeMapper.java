package com.dao;

import com.entity.Income;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeMapper {
    int deleteByPrimaryKey(Integer incomeid);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer incomeid);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);
}