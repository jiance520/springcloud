package com.dao;

import com.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}