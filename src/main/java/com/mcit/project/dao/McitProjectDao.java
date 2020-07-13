package com.mcit.project.dao;

import java.util.List;

import com.mcit.project.model.McitProject;

public interface McitProjectDao {

	List<McitProject> findAll();
	
	McitProject findById(Integer projectId);

	List<McitProject> findAllByCreatorId(Integer creatorId);
	
	void saveOrUpdateProject(McitProject project);

	void deleteProject(McitProject project);

	List<McitProject> findAllByLeader(Integer leaderId);

}
