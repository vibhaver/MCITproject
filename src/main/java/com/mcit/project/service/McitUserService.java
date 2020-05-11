package com.mcit.project.service;

import java.util.List;

import com.mcit.project.model.McitUser;

public interface McitUserService {

	List<McitUser> findAll();

	McitUser findById(Integer userId);

	void saveOrUpdateUser(McitUser user);

	List<McitUser> findAllMembersByProjectId(Integer projectId);

	List<McitUser> getLeaders();

	List<McitUser> getMembers();

}
