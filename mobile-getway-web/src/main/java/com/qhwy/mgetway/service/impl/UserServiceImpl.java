package com.qhwy.mgetway.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.qhwy.mgetway.entity.User;
import com.qhwy.mgetway.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Set<String> findRolesByUserName(String userName) {
		Set<String> set=new HashSet<String>();
		set.add("admin");
		return set;
	}

	@Override
	public Set<String> findPermissionsByUserName(String username) {
		Set<String> set=new HashSet<String>();
		set.add("pms:card:add");
		set.add("pms:listMain:view");
		return set;
	}

	@Override
	public User findUserByUserName(String username) {
		// TODO Auto-generated method stub
		User user=new User();
		if("test".equals(username)){
			
			user.setLoginName("test");
			user.setPassWord("3362d681c3834ec5794ba1c1b3807c27");
			user.setSalt("888888");
		}
		return user;
	}

}
