package com.service.impl;

import com.dao.*;

import com.entity.*;
import com.service.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("t_userService")
public class T_userService implements  IT_userService{
    @Autowired
    private T_userMapper t_userMapper;
    @Override
    public int deleteByPrimaryKey(Integer tid) {

        return t_userMapper.deleteByPrimaryKey(tid);
    }
    @Override
    public int insert(T_user t_user) {

        return t_userMapper.insert(t_user);
    }
    @Override
    public int insertSelective(T_user t_user) {
        return t_userMapper.insertSelective(t_user);
    }
    @Override
    public T_user selectByPrimaryKey(Integer tid) {
        return t_userMapper.selectByPrimaryKey(tid);
    }
    @Override
    public int updateByPrimaryKeySelective(T_user t_user) {
        return t_userMapper.updateByPrimaryKeySelective(t_user);
    }
    @Override
    public int updateByPrimaryKey(T_user t_user) {
        return t_userMapper.updateByPrimaryKey(t_user);
    }
//WithBLOBs
}
