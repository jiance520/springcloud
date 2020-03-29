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
@Service("s_role_permssionService")
public class S_role_permssionService implements  IS_role_permssionService{
    @Autowired
    private S_role_permssionMapper s_role_permssionMapper;
    @Override
    public int deleteByPrimaryKey(Integer sid) {

        return s_role_permssionMapper.deleteByPrimaryKey(sid);
    }
    @Override
    public int insert(S_role_permssion s_role_permssion) {

        return s_role_permssionMapper.insert(s_role_permssion);
    }
    @Override
    public int insertSelective(S_role_permssion s_role_permssion) {
        return s_role_permssionMapper.insertSelective(s_role_permssion);
    }
    @Override
    public S_role_permssion selectByPrimaryKey(Integer sid) {
        return s_role_permssionMapper.selectByPrimaryKey(sid);
    }
    @Override
    public int updateByPrimaryKeySelective(S_role_permssion s_role_permssion) {
        return s_role_permssionMapper.updateByPrimaryKeySelective(s_role_permssion);
    }
    @Override
    public int updateByPrimaryKey(S_role_permssion s_role_permssion) {
        return s_role_permssionMapper.updateByPrimaryKey(s_role_permssion);
    }
//WithBLOBs
}
