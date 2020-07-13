package com.mcit.project.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.project.model.McitProject;
import com.mcit.project.model.McitUser;
import com.mcit.project.service.McitProjectService;
import com.mcit.project.service.McitUserService;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private McitProjectService mcitProjectService;

	@Autowired
	private McitUserService mcitUserService;

	@GetMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("projectIndex", "projects", mcitProjectService.findAll());
	}

	private Map<Integer, McitUser> projectMembersCache;
	private Map<Integer, McitUser> leadersCache;

	@GetMapping("/createProject")
	public ModelAndView createProject() {
		ModelAndView modelAndView = new ModelAndView("projectForm", "project", new McitProject());
		List<McitUser> leaders = mcitUserService.getLeaders();
		List<McitUser> members = mcitUserService.getMembers();
		projectMembersCache = new HashMap<>();
		leadersCache = new HashMap<>();
		for (McitUser user : members) {
			projectMembersCache.put(user.getUserId(), user);
		}
		for (McitUser user : leaders) {
			leadersCache.put(user.getUserId(), user);
		}
		modelAndView.getModelMap().addAttribute("leaders", leaders);
		modelAndView.getModelMap().addAttribute("members", members);
		return modelAndView;
	}

	@GetMapping("/viewProject/{projectId}")
	public ModelAndView viewProject(@PathVariable(name = "projectId") Integer projectId) {
		ModelAndView modelAndView = new ModelAndView("projectView", "project", mcitProjectService.findById(projectId));
		updateCache(modelAndView);
		return modelAndView;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
		binder.registerCustomEditor(Set.class, "projectMembers", new CustomCollectionEditor(Set.class) {
			protected Object convertElement(Object element) {
				if (element instanceof McitUser) {
					return element;
				}
				if (element instanceof String) {
					Integer id = Integer.parseInt((String) element);
					McitUser user = projectMembersCache.get(id);
					return user;
				}
				return null;
			}
		});
		binder.registerCustomEditor(McitProject.class, "leader", new LeadersEditor());
	}

	@PostMapping("saveProject")
	public ModelAndView saveProject(McitProject project, BindingResult result) {
		mcitProjectService.saveProject(project);
		return new ModelAndView("redirect:/project/index");
	}

	@PostMapping("updateProject")
	public ModelAndView updateProject(McitProject project, BindingResult result) {
		mcitProjectService.updateProject(project);
		return new ModelAndView("redirect:/project/index");
	}

	@GetMapping("editProject/{projectId}")
	public ModelAndView editProject(@PathVariable(name = "projectId") Integer projectId) {
		McitProject findById = mcitProjectService.findById(projectId);
		ModelAndView modelAndView = new ModelAndView("projectUpdateForm", "project", findById);
		updateCache(modelAndView);
		if (findById != null && findById.getProjectMembers() != null && !findById.getProjectMembers().isEmpty()) {
			Iterator<McitUser> iterator = findById.getProjectMembers().iterator();
			String selectedMembers = "";
			int i = 0;
			while (iterator.hasNext()) {
				if (i == 0) {
					selectedMembers += "" + iterator.next().getUserId();
				} else {
					selectedMembers += "," + iterator.next().getUserId();
				}
				i++;
			}
			modelAndView.getModelMap().addAttribute("membersSelected", selectedMembers);
		}
		if (findById != null && findById.getLeader() != null) {
			modelAndView.getModelMap().addAttribute("leaderSelected", findById.getLeader().getUserId());
		}

		return modelAndView;
	}

	@PostMapping("deleteProject")
	public ModelAndView deleteProject(McitProject project, BindingResult result) {
		if (project != null) {
			Integer projectId = project.getProjectId();
			mcitProjectService.deleteProjectById(projectId);
		}
		return new ModelAndView("redirect:/project/index");
	}

	private class LeadersEditor extends PropertyEditorSupport {
		@Override
		public void setAsText(String id) {
			int parseInt = Integer.parseInt(id);
			if (leadersCache.containsKey(parseInt))
				this.setValue(leadersCache.get(parseInt));
			else
				this.setValue(null);
		}
	}

	void updateCache(ModelAndView modelAndView) {
		List<McitUser> leaders = mcitUserService.getLeaders();
		List<McitUser> members = mcitUserService.getMembers();
		projectMembersCache = new HashMap<>();
		leadersCache = new HashMap<>();
		for (McitUser user : members) {
			projectMembersCache.put(user.getUserId(), user);
		}
		for (McitUser user : leaders) {
			leadersCache.put(user.getUserId(), user);
		}
		modelAndView.getModelMap().addAttribute("leaders", leaders);
		modelAndView.getModelMap().addAttribute("members", members);
	}

}
