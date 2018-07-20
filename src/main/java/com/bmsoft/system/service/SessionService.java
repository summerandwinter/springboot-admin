package com.bmsoft.system.service;

import java.util.List;
import com.bmsoft.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
