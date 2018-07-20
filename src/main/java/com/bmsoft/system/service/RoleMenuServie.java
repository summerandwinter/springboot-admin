package com.bmsoft.system.service;

import com.bmsoft.common.service.IService;
import com.bmsoft.system.domain.RoleMenu;

public interface RoleMenuServie extends IService<RoleMenu> {

	void deleteRoleMenusByRoleId(String roleIds);

	void deleteRoleMenusByMenuId(String menuIds);
}
