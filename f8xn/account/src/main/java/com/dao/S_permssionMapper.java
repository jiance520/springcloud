package com.dao;

import com.entity.S_permssion;
import org.springframework.stereotype.Repository;

@Repository
public interface S_permssionMapper {
    int deleteByPrimaryKey(Integer permssionid);

    int insert(S_permssion record);

    int insertSelective(S_permssion record);

    S_permssion selectByPrimaryKey(Integer permssionid);

    int updateByPrimaryKeySelective(S_permssion record);

    int updateByPrimaryKey(S_permssion record);
}