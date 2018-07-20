package com.bmsoft.system.service;

import com.bmsoft.common.service.IService;
import com.bmsoft.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
