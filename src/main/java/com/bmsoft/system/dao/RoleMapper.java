package com.bmsoft.system.dao;

import java.util.List;
import com.bmsoft.common.config.MyMapper;
import com.bmsoft.system.domain.Role;
import com.bmsoft.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}