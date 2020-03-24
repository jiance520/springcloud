package com.dao;

import com.entity.Accountlist;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountlistMapper {
    int deleteByPrimaryKey(Long accountid);

    int insert(Accountlist record);

    int insertSelective(Accountlist record);

    Accountlist selectByPrimaryKey(Long accountid);

    int updateByPrimaryKeySelective(Accountlist record);

    int updateByPrimaryKey(Accountlist record);
}