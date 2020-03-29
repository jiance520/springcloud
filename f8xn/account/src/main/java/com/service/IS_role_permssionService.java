package com.service;

import com.entity.S_role_permssion;

public interface IS_role_permssionService {
    int deleteByPrimaryKey(Integer srpid);

    int insert(S_role_permssion record);

    int insertSelective(S_role_permssion record);

    S_role_permssion selectByPrimaryKey(Integer srpid);

    int updateByPrimaryKeySelective(S_role_permssion record);

    int updateByPrimaryKey(S_role_permssion record);
}