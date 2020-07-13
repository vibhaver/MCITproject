package com.mcit.project.util;

public enum TaskStatusEnum {

	PENDING("PENDING"), COMPLETE("COMPLETE"), IN_PROGRESS("IN_PROGRESS");
	private String taskStatus;

	private TaskStatusEnum(String userRole) {
		this.taskStatus = userRole;
	}

	@Override
	public String toString() {
		return taskStatus;
	}
}
