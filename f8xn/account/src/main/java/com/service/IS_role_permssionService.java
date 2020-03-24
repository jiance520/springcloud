package com.service;

import com.entity.S_role_permssion;
import com.service.impl.S_role_permssionService;

public interface IS_role_permssionService {
    int insert(S_role_permssion record);

    int insertSelective(S_role_permssion record);
    int deleteByPrimaryKey(Integer integer);
    S_role_permssion selectByPrimaryKey(Integer integer);
    int updateByPrimaryKeySelective(S_role_permssion s_role_permssion);
    int updateByPrimaryKey(S_role_permssion s_role_permssion);
}