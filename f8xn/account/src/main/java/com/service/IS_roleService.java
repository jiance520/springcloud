package com.service;

import com.entity.S_role;

public interface IS_roleService {
    int deleteByPrimaryKey(Integer roleid);

    int insert(S_role record);

    int insertSelective(S_role record);

    S_role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(S_role record);

    int updateByPrimaryKey(S_role record);
}