package com.mcit.project.service;

import java.util.List;

import com.mcit.project.model.McitProject;

public interface McitProjectService {
	
	List<McitProject> findAll();

	List<McitProject> findAllByCreatorId(Integer creatorId);
	
	McitProject findById(Integer projectId);
	
	void saveOrUpdateProject(McitProject project);

	void deleteProjectById(Integer projectId);

}
