package com.dao;

import com.entity.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserinfoMapper {
    Userinfo selectLogin(Map map);
    int deleteByPrimaryKey(Integer userid);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKeyWithBLOBs(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}