package com.bmsoft.system.service;

import java.util.List;
import com.bmsoft.common.domain.Tree;
import com.bmsoft.common.service.IService;
import com.bmsoft.system.domain.Dept;

public interface DeptService extends IService<Dept> {

	Tree<Dept> getDeptTree();

	List<Dept> findAllDepts(Dept dept);

	Dept findByName(String deptName);

	Dept findById(Long deptId);
	
	void addDept(Dept dept);
	
	void updateDept(Dept dept);

	void deleteDepts(String deptIds);
}
