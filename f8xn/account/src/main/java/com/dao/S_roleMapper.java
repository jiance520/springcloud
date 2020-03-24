package com.dao;

import com.entity.S_role;
import org.springframework.stereotype.Repository;

@Repository
public interface S_roleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(S_role record);

    int insertSelective(S_role record);

    S_role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(S_role record);

    int updateByPrimaryKey(S_role record);
}