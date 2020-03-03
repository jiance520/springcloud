package com.service;

import com.entity.T_user;

public interface IT_userService {
    int deleteByPrimaryKey(Integer uid);

    int insert(T_user record);

    int insertSelective(T_user record);

    T_user selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(T_user record);

    int updateByPrimaryKey(T_user record);
}