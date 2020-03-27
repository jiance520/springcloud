package com.service.impl;

import com.dao.S_roleMapper;
import com.entity.S_role;
import com.service.IS_roleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("s_roleService")
public class S_roleService implements  IS_roleService{
    @Autowired
    private S_roleMapper s_roleMapper;
    @Override
    public int deleteByPrimaryKey(Integer sid) {

        return s_roleMapper.deleteByPrimaryKey(sid);
    }
    @Override
    public int insert(S_role s_role) {

        return s_roleMapper.insert(s_role);
    }
    @Override
    public int insertSelective(S_role s_role) {
        return s_roleMapper.insertSelective(s_role);
    }
    @Override
    public S_role selectByPrimaryKey(Integer sid) {
        return s_roleMapper.selectByPrimaryKey(sid);
    }
    @Override
    public int updateByPrimaryKeySelective(S_role s_role) {
        return s_roleMapper.updateByPrimaryKeySelective(s_role);
    }
    @Override
    public int updateByPrimaryKey(S_role s_role) {
        return s_roleMapper.updateByPrimaryKey(s_role);
    }
//WithBLOBs
}
