package com.cache.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhang_htao on 2019/8/22.
 */
@Data
public class Job implements Serializable{
    private String jobId;
    private String jobTitle;
    private String minSalary;
    private String maxSalary;
}
