package com.service;

import com.entity.Course;

public interface ICourseService {
    int deleteByPrimaryKey(Integer sid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}