package com.service;

import com.entity.S_role_permssion;
import com.entity.S_user_role;

public interface IS_user_roleService {
    int insert(S_user_role record);

    int insertSelective(S_user_role record);
    int deleteByPrimaryKey(Integer integer);
    S_user_role selectByPrimaryKey(Integer integer);
    int updateByPrimaryKeySelective(S_user_role s_user_role);
    int updateByPrimaryKey(S_user_role s_user_role);
}