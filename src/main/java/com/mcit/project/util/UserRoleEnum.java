package com.mcit.project.util;

public enum UserRoleEnum {

	ADMIN("ADMIN"), LEADER("LEADER"), MEMBER("MEMBER");
	private String userRole;

	private UserRoleEnum(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return userRole;
	}
}
