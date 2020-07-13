package com.mcit.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.project.dao.McitTaskDao;
import com.mcit.project.model.McitTask;
import com.mcit.project.model.McitUser;
import com.mcit.project.util.UserRoleEnum;

@Service
public class McitTaskServiceImpl implements McitTaskService {

	@Autowired
	private McitTaskDao mcitTaskDao;

	@Autowired
	private McitUserService userService;

	@Override
	@Transactional(readOnly = true)
	public List<McitTask> findAll() {
		if (userService.hasRole(UserRoleEnum.LEADER.toString())) {
			McitUser currentMcitUser = userService.getCurrentMcitUser();
			return mcitTaskDao.findAllByLeader(currentMcitUser.getUserId());
		} else if (userService.hasRole(UserRoleEnum.MEMBER.toString())) {
			McitUser currentMcitUser = userService.getCurrentMcitUser();
			return mcitTaskDao.findAllByAssignee(currentMcitUser.getUserId());
		} else {
			return mcitTaskDao.findAll();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<McitTask> findAllByProjectId(Integer projectId) {
		return mcitTaskDao.findAllByProjectId(projectId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrUpdateTask(McitTask task) {
		mcitTaskDao.saveOrUpdateTask(task);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateStatusByTaskId(String status, Integer taskId) {
		return mcitTaskDao.updateStatusByTaskId(status, taskId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteTaskById(Integer taskId) {
		return mcitTaskDao.deleteTaskById(taskId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteTaskByProjectId(Integer projectId) {
		return mcitTaskDao.deleteTaskByProjectId(projectId);
	}

	@Override
	public McitTask findById(Integer taskId) {
		return mcitTaskDao.findById(taskId);
	}

}
