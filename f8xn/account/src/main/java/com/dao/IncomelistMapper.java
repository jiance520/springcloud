package com.dao;

import com.entity.Incomelist;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomelistMapper {
    int deleteByPrimaryKey(Integer incomeid);

    int insert(Incomelist record);

    int insertSelective(Incomelist record);

    Incomelist selectByPrimaryKey(Integer incomeid);

    int updateByPrimaryKeySelective(Incomelist record);

    int updateByPrimaryKey(Incomelist record);
}