package com.mcit.project.service;

import java.util.List;

import com.mcit.project.model.McitTask;

public interface McitTaskService {

	List<McitTask> findAll();

	List<McitTask> findAllByProjectId(Integer projectId);
	
	void saveOrUpdateTask(McitTask task);

	int updateStatusByTaskId(String status, Integer taskId);

	int deleteTaskById(Integer taskId);

	int deleteTaskByProjectId(Integer projectId);

	McitTask findById(Integer taskId);
	
}
