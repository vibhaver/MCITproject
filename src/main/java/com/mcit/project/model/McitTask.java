package com.mcit.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.mcit.project.util.TaskStatusEnum;

@Entity
@Table(schema = "HR", name = "MCIT_TASK")
public class McitTask {

	@Id
	@Column(name = "TASK_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
	@SequenceGenerator(name = "task_generator", sequenceName = "mcit_task_sequence", allocationSize = 1)
	private Integer taskId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	private McitProject project;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPTION")
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSIGNEE_ID")
	private McitUser assignee;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "TASK_STATE")
	private String taskState = TaskStatusEnum.PENDING.toString();

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public McitProject getProject() {
		return project;
	}

	public void setProject(McitProject project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public McitUser getAssignee() {
		return assignee;
	}

	public void setAssignee(McitUser leader) {
		this.assignee = leader;
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

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String status) {
		this.taskState = status;
	}

	public McitTask(Integer taskId, McitProject project, String title, String description, McitUser leader,
			Date startDate, Date endDate, String status) {
		super();
		this.taskId = taskId;
		this.project = project;
		this.title = title;
		this.description = description;
		this.assignee = leader;
		this.startDate = startDate;
		this.endDate = endDate;
		this.taskState = status;
	}

	public McitTask() {
	}

}
