package com.dao;

import com.entity.S_user_role;
import org.springframework.stereotype.Repository;

@Repository
public interface S_user_roleMapper {
    int deleteByPrimaryKey(Integer surid);

    int insert(S_user_role record);

    int insertSelective(S_user_role record);

    S_user_role selectByPrimaryKey(Integer surid);

    int updateByPrimaryKeySelective(S_user_role record);

    int updateByPrimaryKey(S_user_role record);
}