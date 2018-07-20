package com.bmsoft.job.dao;

import java.util.List;
import com.bmsoft.common.config.MyMapper;
import com.bmsoft.job.domain.Job;

public interface JobMapper extends MyMapper<Job> {
	
	List<Job> queryList();
}