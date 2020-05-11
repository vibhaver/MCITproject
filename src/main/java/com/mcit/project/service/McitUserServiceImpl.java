package com.mcit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.project.dao.McitUserDao;
import com.mcit.project.model.McitUser;

@Service
public class McitUserServiceImpl implements McitUserService {

	@Autowired
	private McitUserDao mcitUserDao;

	@Override
	@Transactional(readOnly = true)
	public List<McitUser> findAll() {
		return mcitUserDao.findAll();
	}

	@Override
	public void saveOrUpdateUser(McitUser user) {
		mcitUserDao.saveOrUpdateUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<McitUser> findAllMembersByProjectId(Integer projectId) {
		return mcitUserDao.findAllMembersByProjectId(projectId);
	}

	@Override
	@Transactional(readOnly = true)
	public McitUser findById(Integer userId) {
		return mcitUserDao.findById(userId);
	}

	@Override
	public List<McitUser> getLeaders() {
		return mcitUserDao.getLeaders();
	}

	@Override
	public List<McitUser> getMembers() {
		return mcitUserDao.getMembers();
	}

}
