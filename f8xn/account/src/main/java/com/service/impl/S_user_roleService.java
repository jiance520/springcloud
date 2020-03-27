package com.service.impl;

import com.dao.S_user_roleMapper;
import com.entity.S_user_role;
import com.service.IS_user_roleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("s_user_roleService")
public class S_user_roleService implements IS_user_roleService {
    @Autowired
    private S_user_roleMapper s_user_roleMapper;
    @Override
    public int deleteByPrimaryKey(Integer sid) {

        return s_user_roleMapper.deleteByPrimaryKey(sid);
    }
    @Override
    public int insert(S_user_role s_user_role) {

        return s_user_roleMapper.insert(s_user_role);
    }
    @Override
    public int insertSelective(S_user_role s_user_role) {
        return s_user_roleMapper.insertSelective(s_user_role);
    }
    @Override
    public S_user_role selectByPrimaryKey(Integer sid) {
        return s_user_roleMapper.selectByPrimaryKey(sid);
    }
    @Override
    public int updateByPrimaryKeySelective(S_user_role s_user_role) {
        return s_user_roleMapper.updateByPrimaryKeySelective(s_user_role);
    }
    @Override
    public int updateByPrimaryKey(S_user_role s_user_role) {
        return s_user_roleMapper.updateByPrimaryKey(s_user_role);
    }
//WithBLOBs
}
