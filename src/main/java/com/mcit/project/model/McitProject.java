package com.mcit.project.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "HR", name = "MCIT_PROJECT")
public class McitProject {

	@Id
	@Column(name = "PROJECT_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
	@SequenceGenerator(name="project_generator", sequenceName = "mcit_project_sequence", allocationSize = 1)
	private Integer projectId;
	@Column(name = "TITLE")
	private String title;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_ID")
	private McitUser creator;
	@Column(name = "DESCRIPTION")
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEADER_ID")
	private McitUser leader;
	@Column(name = "START_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@Column(name = "END_DATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	private List<McitTask> tasks;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MCIT_USER_PROJECT ", joinColumns = {
			@JoinColumn(name = "PROJECT_ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
	private Set<McitUser> projectMembers;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public McitUser getCreator() {
		return creator;
	}

	public void setCreator(McitUser creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public McitUser getLeader() {
		return leader;
	}

	public void setLeader(McitUser leader) {
		this.leader = leader;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<McitTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<McitTask> tasks) {
		this.tasks = tasks;
	}

	public Set<McitUser> getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(Set<McitUser> projectMembers) {
		this.projectMembers = projectMembers;
	}

}
