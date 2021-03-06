package com.qhwy.getway.service;

import java.util.Set;

import com.qhwy.getway.entity.User;

public interface UserService {

	public Set<String> findRolesByUserName(String userName);
	
	public Set<String> findPermissionsByUserName(String username);
	
	public User findUserByUserName(String username);
}
