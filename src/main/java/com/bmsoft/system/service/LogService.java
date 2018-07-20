package com.bmsoft.system.service;

import java.util.List;
import com.bmsoft.common.service.IService;
import com.bmsoft.system.domain.SysLog;


public interface LogService extends IService<SysLog> {
	
	List<SysLog> findAllLogs(SysLog log);
	
	void deleteLogs(String logIds);
}
