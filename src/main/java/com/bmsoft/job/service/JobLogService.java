package com.bmsoft.job.service;

import java.util.List;
import com.bmsoft.common.service.IService;
import com.bmsoft.job.domain.JobLog;

public interface JobLogService extends IService<JobLog> {

	List<JobLog> findAllJobLogs(JobLog jobLog);

	void saveJobLog(JobLog log);
	
	void deleteBatch(String jobLogIds);
}
