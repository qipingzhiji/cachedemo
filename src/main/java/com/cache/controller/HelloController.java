package com.cache.controller;

import com.cache.dao.JobMapper;
import com.cache.entity.Job;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhang_htao on 2019/8/22.
 */
@RestController
public class HelloController {
    @Autowired
    private JobMapper jodDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(cacheNames = {"jobs"},key = "#id")
    @GetMapping("/job/{id}")
    public Object getJobById(@PathVariable("id") String id) {
        Job jobById = jodDao.getJobById(id);
        return jobById;
    }

    @CachePut(cacheNames = {"jobs"},key = "#id")
    @RequestMapping("/updateJob/{id}/{maxSalary}")
    public Object updateJOb(@PathVariable("id") String id,@PathVariable(value = "maxSalary") String maxSalary) {
        Job job = new Job();
        job.setJobId(id);
        job.setMaxSalary(maxSalary);
        int i = jodDao.updateJob(job);
        return jodDao.getJobById(id);
    }


    @RequestMapping("redis/{id}")
    public String redisTest(@PathVariable(name = "id") String id){
        Job jobById = jodDao.getJobById(id);
        Object o = redisTemplate.opsForList().leftPush("jobs",jobById);
        Object jobs = redisTemplate.opsForList().leftPop("jobs");
        System.out.println(jobs);
        return "seccess";
    }

}
