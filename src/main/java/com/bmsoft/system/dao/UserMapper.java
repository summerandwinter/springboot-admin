package com.bmsoft.system.dao;

import java.util.List;
import com.bmsoft.common.config.MyMapper;
import com.bmsoft.system.domain.User;
import com.bmsoft.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}