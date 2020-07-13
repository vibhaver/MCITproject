package com.mcit.project.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.project.model.McitProject;
import com.mcit.project.model.McitTask;
import com.mcit.project.model.McitUser;
import com.mcit.project.service.McitProjectService;
import com.mcit.project.service.McitTaskService;
import com.mcit.project.service.McitUserService;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private McitTaskService mcitTaskService;

	@Autowired
	private McitUserService mcitUserService;

	@Autowired
	private McitProjectService mcitProjectService;

	private Map<Integer, McitUser> taskAssigneeCache;
	private Map<Integer, McitProject> projectCache;

	@GetMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("taskIndex", "tasks", mcitTaskService.findAll());
	}

	@GetMapping("/createTask")
	public ModelAndView createTask() {
		ModelAndView modelAndView = new ModelAndView("taskForm", "task", new McitTask());
		updateCache(modelAndView);
		return modelAndView;
	}
	
	@GetMapping("/getDatesForProject/{projectId}")
	@ResponseBody
	public String getDatesForProject(@PathVariable(name = "projectId") Integer projectId) {
		McitProject findById = mcitProjectService.findById(projectId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dates = df.format(findById.getStartDate());
		dates += "," + df.format(findById.getEndDate());
		return dates;
	}

	@PostMapping("saveTask")
	public ModelAndView saveTask(McitTask mcitTask, BindingResult result) {
		mcitTaskService.saveOrUpdateTask(mcitTask);
		return new ModelAndView("redirect:/task/index");
	}

	@GetMapping("/viewTask/{taskId}")
	public ModelAndView viewProject(@PathVariable(name = "taskId") Integer taskId) {
		ModelAndView modelAndView = new ModelAndView("taskView", "task", mcitTaskService.findById(taskId));
		updateCache(modelAndView);
		return modelAndView;
	}

	@GetMapping("/editTask/{taskId}")
	public ModelAndView editTask(@PathVariable(name = "taskId") Integer taskId) {
		ModelAndView modelAndView = new ModelAndView("taskUpdateForm", "task", mcitTaskService.findById(taskId));
		updateCache(modelAndView);
		return modelAndView;
	}

	@PostMapping("/updateTask")
	public ModelAndView updateTask(McitTask mcitTask, BindingResult result) {
		mcitTaskService.saveOrUpdateTask(mcitTask);
		return new ModelAndView("redirect:/task/index");
	}

	@PostMapping("/deleteTask")
	public ModelAndView deleteTask(McitTask task) {
		if (task != null) {
			Integer taskId = task.getTaskId();
			mcitTaskService.deleteTaskById(taskId);
		}
		return new ModelAndView("redirect:/task/index");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(McitTask.class, "assignee", new AssigneeEditor());
		binder.registerCustomEditor(McitTask.class, "project", new ProjectEditor());
	}

	private class AssigneeEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String id) {
			int parseInt = Integer.parseInt(id);
			if (taskAssigneeCache.containsKey(parseInt))
				this.setValue(taskAssigneeCache.get(parseInt));
			else
				this.setValue(null);
		}
	}

	private class ProjectEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String id) {
			int parseInt = Integer.parseInt(id);
			if (projectCache.containsKey(parseInt))
				this.setValue(projectCache.get(parseInt));
			else
				this.setValue(null);
		}
	}

	void updateCache(ModelAndView modelAndView) {
		List<McitProject> projects = mcitProjectService.getEigibleProjectsForTaskCreation();
		List<McitUser> members = mcitUserService.getMembers();
		taskAssigneeCache = new HashMap<>();
		projectCache = new HashMap<>();
		for (McitUser user : members) {
			taskAssigneeCache.put(user.getUserId(), user);
		}
		for (McitProject project : projects) {
			projectCache.put(project.getProjectId(), project);
		}
		modelAndView.getModelMap().addAttribute("projects", projects);
		modelAndView.getModelMap().addAttribute("members", members);
	}

}
