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
@Service("creditService")
public class CreditService implements  ICreditService{
    @Autowired
    private CreditMapper creditMapper;
    @Override
    public int deleteByPrimaryKey(Integer cid) {

        return creditMapper.deleteByPrimaryKey(cid);
    }
    @Override
    public int insert(Credit credit) {

        return creditMapper.insert(credit);
    }
    @Override
    public int insertSelective(Credit credit) {
        return creditMapper.insertSelective(credit);
    }
    @Override
    public Credit selectByPrimaryKey(Integer cid) {
        return creditMapper.selectByPrimaryKey(cid);
    }
    @Override
    public int updateByPrimaryKeySelective(Credit credit) {
        return creditMapper.updateByPrimaryKeySelective(credit);
    }
    @Override
    public int updateByPrimaryKey(Credit credit) {
        return creditMapper.updateByPrimaryKey(credit);
    }
//WithBLOBs
}
