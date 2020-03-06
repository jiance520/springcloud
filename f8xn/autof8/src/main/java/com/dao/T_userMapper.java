package com.dao;

import com.entity.T_user;
import org.springframework.stereotype.Repository;

@Repository
public interface T_userMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(T_user record);

    int insertSelective(T_user record);

    T_user selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T_user record);

    int updateByPrimaryKey(T_user record);
}