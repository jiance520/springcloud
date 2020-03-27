package com.service;

import com.entity.Userinfo;

import java.util.Map;

public interface IUserinfoService {
    Userinfo selectLogin(Map map);
    int deleteByPrimaryKey(Integer userid);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKeyWithBLOBs(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}