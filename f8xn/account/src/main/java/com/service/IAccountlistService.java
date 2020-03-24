package com.service;

import com.entity.Accountlist;

public interface IAccountlistService {
    int deleteByPrimaryKey(Long accountid);

    int insert(Accountlist record);

    int insertSelective(Accountlist record);

    Accountlist selectByPrimaryKey(Long accountid);

    int updateByPrimaryKeySelective(Accountlist record);

    int updateByPrimaryKey(Accountlist record);
}