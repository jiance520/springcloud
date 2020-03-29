package com.service;

import com.entity.S_user_role;

public interface IS_user_roleService {
    int deleteByPrimaryKey(Integer surid);

    int insert(S_user_role record);

    int insertSelective(S_user_role record);

    S_user_role selectByPrimaryKey(Integer surid);

    int updateByPrimaryKeySelective(S_user_role record);

    int updateByPrimaryKey(S_user_role record);
}