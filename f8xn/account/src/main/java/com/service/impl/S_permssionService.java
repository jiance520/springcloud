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
@Service("s_permssionService")
public class S_permssionService implements  IS_permssionService{
    @Autowired
    private S_permssionMapper s_permssionMapper;
    @Override
    public int deleteByPrimaryKey(Integer sid) {

        return s_permssionMapper.deleteByPrimaryKey(sid);
    }
    @Override
    public int insert(S_permssion s_permssion) {

        return s_permssionMapper.insert(s_permssion);
    }
    @Override
    public int insertSelective(S_permssion s_permssion) {
        return s_permssionMapper.insertSelective(s_permssion);
    }
    @Override
    public S_permssion selectByPrimaryKey(Integer sid) {
        return s_permssionMapper.selectByPrimaryKey(sid);
    }
    @Override
    public int updateByPrimaryKeySelective(S_permssion s_permssion) {
        return s_permssionMapper.updateByPrimaryKeySelective(s_permssion);
    }
    @Override
    public int updateByPrimaryKey(S_permssion s_permssion) {
        return s_permssionMapper.updateByPrimaryKey(s_permssion);
    }
//WithBLOBs
}
