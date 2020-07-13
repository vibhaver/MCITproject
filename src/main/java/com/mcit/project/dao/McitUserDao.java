package com.mcit.project.dao;

import java.util.List;

import com.mcit.project.model.McitUser;

public interface McitUserDao {

	List<McitUser> findAll();
	
	McitUser findById(Integer userId);
	
	void saveOrUpdateUser(McitUser user);

	List<McitUser> findAllMembersByProjectId(Integer projectId);

	List<McitUser> getLeaders();

	List<McitUser> getMembers();
	
	McitUser findUserByUsername(String username);

}
