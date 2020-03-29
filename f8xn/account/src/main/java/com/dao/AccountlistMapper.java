package com.dao;

import com.entity.Accountlist;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountlistMapper {
    int deleteByPrimaryKey(Integer auserid);

    int insert(Accountlist record);

    int insertSelective(Accountlist record);

    Accountlist selectByPrimaryKey(Integer auserid);

    int updateByPrimaryKeySelective(Accountlist record);

    int updateByPrimaryKeyWithBLOBs(Accountlist record);

    int updateByPrimaryKey(Accountlist record);
}