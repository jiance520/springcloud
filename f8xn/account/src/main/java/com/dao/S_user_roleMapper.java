package com.dao;

import com.entity.S_user_role;
import org.springframework.stereotype.Repository;

@Repository
public interface S_user_roleMapper {
    int insert(S_user_role record);

    int insertSelective(S_user_role record);
    int deleteByPrimaryKey(Integer integer);
    S_user_role selectByPrimaryKey(Integer integer);
    int updateByPrimaryKeySelective(S_user_role s_user_role);
    int updateByPrimaryKey(S_user_role s_user_role);
}