package com.cache.dao;

import com.cache.entity.Job;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by zhang_htao on 2019/8/22.
 */
public interface JobMapper {

    @Select("select * from jobs where job_id = #{id}")
    Job getJobById(String id);

    @Update("<script>" +
            "update jobs " +
            "<set>" +
            "<if test='jobTitle != null'>" +
            "job_title = #{jobTitle}," +
            "</if>" +
            "<if test = 'minSalary != null'>" +
            "min_salary=#{minSalary}," +
            "</if>" +
            "<if test = 'maxSalary != null'>" +
            " max_salary = #{maxSalary}," +
            "</if>" +
            "</set>" +
            "where job_id = #{jobId}" +
            "</script>")
    int updateJob(Job job);
}
