package com.mcit.project.service;

import java.util.List;

import com.mcit.project.model.McitProject;

public interface McitProjectService {
	
	List<McitProject> findAll();

	List<McitProject> findAllByCreatorId(Integer creatorId);
	
	McitProject findById(Integer projectId);
	
	void saveProject(McitProject project);
	
	void updateProject(McitProject project);

	void deleteProjectById(Integer projectId);

	List<McitProject> getEigibleProjectsForTaskCreation();

}
