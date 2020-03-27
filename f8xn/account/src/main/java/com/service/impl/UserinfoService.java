package com.service.impl;

import com.dao.UserinfoMapper;
import com.entity.Userinfo;
import com.service.IUserinfoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional
@MapperScan(basePackages = "com.dao")
@Service("userinfoService")
public class UserinfoService implements IUserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public Userinfo selectLogin(Map map) {
        return userinfoMapper.selectLogin(map);
    }

    @Override
    public int deleteByPrimaryKey(Integer uid) {

        return userinfoMapper.deleteByPrimaryKey(uid);
    }
    @Override
    public int insert(Userinfo userinfo) {

        return userinfoMapper.insert(userinfo);
    }
    @Override
    public int insertSelective(Userinfo userinfo) {
        return userinfoMapper.insertSelective(userinfo);
    }
    @Override
    public Userinfo selectByPrimaryKey(Integer uid) {
        return userinfoMapper.selectByPrimaryKey(uid);
    }
    @Override
    public int updateByPrimaryKeySelective(Userinfo userinfo) {
        return userinfoMapper.updateByPrimaryKeySelective(userinfo);
    }
    @Override
    public int updateByPrimaryKey(Userinfo userinfo) {
        return userinfoMapper.updateByPrimaryKey(userinfo);
    }
    @Override
    public int updateByPrimaryKeyWithBLOBs(Userinfo userinfo) {
        return userinfoMapper.updateByPrimaryKeyWithBLOBs(userinfo);
    }
}
