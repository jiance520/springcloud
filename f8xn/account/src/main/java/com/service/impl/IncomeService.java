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
@Service("incomeService")
public class IncomeService implements  IIncomeService{
    @Autowired
    private IncomeMapper incomeMapper;
    @Override
    public int deleteByPrimaryKey(Integer iid) {

        return incomeMapper.deleteByPrimaryKey(iid);
    }
    @Override
    public int insert(Income income) {

        return incomeMapper.insert(income);
    }
    @Override
    public int insertSelective(Income income) {
        return incomeMapper.insertSelective(income);
    }
    @Override
    public Income selectByPrimaryKey(Integer iid) {
        return incomeMapper.selectByPrimaryKey(iid);
    }
    @Override
    public int updateByPrimaryKeySelective(Income income) {
        return incomeMapper.updateByPrimaryKeySelective(income);
    }
    @Override
    public int updateByPrimaryKey(Income income) {
        return incomeMapper.updateByPrimaryKey(income);
    }
//WithBLOBs
}
