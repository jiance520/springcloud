package com.dao;

import com.entity.Credibilitylist;
import org.springframework.stereotype.Repository;

@Repository
public interface CredibilitylistMapper {
    int deleteByPrimaryKey(Integer creditid);

    int insert(Credibilitylist record);

    int insertSelective(Credibilitylist record);

    Credibilitylist selectByPrimaryKey(Integer creditid);

    int updateByPrimaryKeySelective(Credibilitylist record);

    int updateByPrimaryKey(Credibilitylist record);
}