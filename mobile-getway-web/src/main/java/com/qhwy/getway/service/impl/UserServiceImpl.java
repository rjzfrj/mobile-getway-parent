package com.qhwy.getway.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.qhwy.getway.entity.User;
import com.qhwy.getway.service.UserService;

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
		set.add("index");
		return set;
	}

	@Override
	public User findUserByUserName(String username) {
		// TODO Auto-generated method stub
		User user=new User();
		if("test".equals(username)){
			
			user.setUserName("test");
			user.setPassWord("123456");
			user.setCredentialsSalt("888");
		}
		return user;
	}

}
