package com.mcit.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "HR", name = "MCIT_AUTHORITIES")
public class McitAuthorities {

	@Id
	@Column(name = "AUTH_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_generator")
	@SequenceGenerator(name = "auth_generator", sequenceName = "mcit_auth_sequence", allocationSize = 1)
	private Integer authId;

	@Column(name = "AUTHORITY")
	private String authority;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private McitUser mcitUser;

	public McitAuthorities() {
	}

	public McitAuthorities(String authority, McitUser mcitUser) {
		super();
		this.authority = authority;
		this.mcitUser = mcitUser;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public McitUser getMcitUser() {
		return mcitUser;
	}

	public void setMcitUser(McitUser user) {
		this.mcitUser = user;
	}

}
