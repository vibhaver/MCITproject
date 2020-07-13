package com.mcit.project.dao;

import java.util.List;

import com.mcit.project.model.McitTask;

public interface McitTaskDao {

	List<McitTask> findAll();
	
	McitTask findById(Integer taskId);

	List<McitTask> findAllByProjectId(Integer projectId);
	
	void saveOrUpdateTask(McitTask task);

	int updateStatusByTaskId(String status, Integer taskId);

	int deleteTaskById(Integer taskId);

	int deleteTaskByProjectId(Integer projectId);
	
	List<McitTask> findAllByAssignee(Integer assigneeId);
	
	List<McitTask> findAllByLeader(Integer leaderId);

}
