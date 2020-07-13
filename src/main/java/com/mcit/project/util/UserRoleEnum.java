package com.mcit.project.util;

public enum UserRoleEnum {

	ADMIN("ROLE_ADMIN"), LEADER("ROLE_LEADER"), MEMBER("ROLE_MEMBER");
	private String userRole;

	private UserRoleEnum(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return userRole;
	}
}
