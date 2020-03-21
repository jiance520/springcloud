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
@Service("courseService")
public class CourseService implements  ICourseService{
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public int deleteByPrimaryKey(Integer cid) {

        return courseMapper.deleteByPrimaryKey(cid);
    }
    @Override
    public int insert(Course course) {

        return courseMapper.insert(course);
    }
    @Override
    public int insertSelective(Course course) {
        return courseMapper.insertSelective(course);
    }
    @Override
    public Course selectByPrimaryKey(Integer cid) {
        return courseMapper.selectByPrimaryKey(cid);
    }
    @Override
    public int updateByPrimaryKeySelective(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }
    @Override
    public int updateByPrimaryKey(Course course) {
        return courseMapper.updateByPrimaryKey(course);
    }
//WithBLOBs
}
