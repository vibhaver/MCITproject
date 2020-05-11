package com.mcit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.project.dao.McitProjectDao;
import com.mcit.project.model.McitProject;

@Service
public class McitProjectServiceImpl implements McitProjectService {

	@Autowired
	private McitProjectDao mcitProjectDao;

	@Override
	@Transactional(readOnly = true)
	public List<McitProject> findAll() {
		return mcitProjectDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<McitProject> findAllByCreatorId(Integer creatorId) {
		return mcitProjectDao.findAllByCreatorId(creatorId);
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateProject(McitProject project) {
		mcitProjectDao.saveOrUpdateProject(project);
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteProjectById(Integer projectId) {
		 McitProject findById = mcitProjectDao.findById(projectId);
		 mcitProjectDao.deleteProject(findById);
	}

	@Override
	@Transactional(readOnly = true)
	public McitProject findById(Integer projectId) {
		return mcitProjectDao.findById(projectId);
	}

}
