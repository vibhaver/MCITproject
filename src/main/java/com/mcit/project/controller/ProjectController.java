package com.mcit.project.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
		return new ModelAndView("projectView", "project", mcitProjectService.findById(projectId));
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
		/*binder.registerCustomEditor(McitUser.class, "leader", new CustomCollectionEditor(McitUser.class) {
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
		});*/
	}

	@PostMapping("saveOrUpdateProject")
	public ModelAndView saveOrUpdateProject(McitProject project, BindingResult result) {
		mcitProjectService.saveOrUpdateProject(project);
		return new ModelAndView("projectIndex", "projects", mcitProjectService.findAll());
	}

	@PostMapping("editProject/{projectId}")
	public ModelAndView editProject(@PathVariable(name = "projectId") Integer projectId) {
		return new ModelAndView("projectForm", "project", mcitProjectService.findById(projectId));
	}

	@PostMapping("deleteProject")
	public ModelAndView deleteProject() {
		return null;
	}
	
	private class LeadersEditor extends PropertyEditorSupport 
	{
	    //This will be called when user HTTP Post to server a field bound to DepartmentVO
	    @Override
	    public void setAsText(String id) 
	    {
	    	int parseInt = Integer.parseInt(id);
	    	if(leadersCache.containsKey(parseInt))
	    		this.setValue(leadersCache.get(parseInt));
	    	else
	    		this.setValue(null);
	    }
	}

}
