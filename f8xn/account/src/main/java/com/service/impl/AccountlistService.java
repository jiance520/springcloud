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
@Service("accountlistService")
public class AccountlistService implements  IAccountlistService{
    @Autowired
    private AccountlistMapper accountlistMapper;
    @Override
    public int deleteByPrimaryKey(Long aid) {

        return accountlistMapper.deleteByPrimaryKey(aid);
    }
    @Override
    public int insert(Accountlist accountlist) {

        return accountlistMapper.insert(accountlist);
    }
    @Override
    public int insertSelective(Accountlist accountlist) {
        return accountlistMapper.insertSelective(accountlist);
    }
    @Override
    public Accountlist selectByPrimaryKey(Long aid) {
        return accountlistMapper.selectByPrimaryKey(aid);
    }
    @Override
    public int updateByPrimaryKeySelective(Accountlist accountlist) {
        return accountlistMapper.updateByPrimaryKeySelective(accountlist);
    }
    @Override
    public int updateByPrimaryKey(Accountlist accountlist) {
        return accountlistMapper.updateByPrimaryKey(accountlist);
    }
//WithBLOBs
}
