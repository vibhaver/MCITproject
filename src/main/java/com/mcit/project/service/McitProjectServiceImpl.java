package com.mcit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.project.dao.McitProjectDao;
import com.mcit.project.model.McitProject;
import com.mcit.project.model.McitUser;
import com.mcit.project.util.UserRoleEnum;

@Service
public class McitProjectServiceImpl implements McitProjectService {

	@Autowired
	private McitProjectDao mcitProjectDao;
	
	@Autowired
	private McitUserService mcitUserService;

	@Override
	@Transactional(readOnly = true)
	public List<McitProject> findAll() {
		if (mcitUserService.hasRole(UserRoleEnum.LEADER.toString())) {
			McitUser currentMcitUser = mcitUserService.getCurrentMcitUser();
			return mcitProjectDao.findAllByLeader(currentMcitUser.getUserId());
		} else {
			return mcitProjectDao.findAll();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<McitProject> findAllByCreatorId(Integer creatorId) {
		return mcitProjectDao.findAllByCreatorId(creatorId);
	}

	@Override
	public void saveProject(McitProject project) {
		project.setCreator(mcitUserService.getCurrentMcitUser());
		mcitProjectDao.saveOrUpdateProject(project);
	}
	
	@Override
	public void updateProject(McitProject project) {
		mcitProjectDao.saveOrUpdateProject(project);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteProjectById(Integer projectId) {
		 McitProject findById = mcitProjectDao.findById(projectId);
		 mcitProjectDao.deleteProject(findById);
	}

	@Override
	@Transactional(readOnly = true)
	public McitProject findById(Integer projectId) {
		return mcitProjectDao.findById(projectId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<McitProject> getEigibleProjectsForTaskCreation() {
		return mcitProjectDao.findAll();
	}

}
