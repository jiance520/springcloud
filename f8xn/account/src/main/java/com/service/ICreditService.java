package com.service;

import com.entity.Credit;

public interface ICreditService {
    int deleteByPrimaryKey(Integer credit);

    int insert(Credit record);

    int insertSelective(Credit record);

    Credit selectByPrimaryKey(Integer credit);

    int updateByPrimaryKeySelective(Credit record);

    int updateByPrimaryKey(Credit record);
}