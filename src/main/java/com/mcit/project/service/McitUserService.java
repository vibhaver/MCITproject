package com.mcit.project.service;

import java.util.List;

import com.mcit.project.model.McitUser;

public interface McitUserService {

	List<McitUser> findAll();

	McitUser findById(Integer userId);

	void saveUser(McitUser user);

	void updateUser(McitUser user);

	List<McitUser> findAllMembersByProjectId(Integer projectId);

	List<McitUser> getLeaders();

	List<McitUser> getMembers();
	
	McitUser getCurrentMcitUser();

	boolean ifUserExistsWithSameUserId(String username);
	
	boolean hasRole(String role);

}
