package com.service;

import com.entity.Income;

public interface IIncomeService {
    int deleteByPrimaryKey(Integer incomeid);

    int insert(Income record);

    int insertSelective(Income record);

    Income selectByPrimaryKey(Integer incomeid);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);
}