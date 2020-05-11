package com.mcit.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.project.model.McitTask;
import com.mcit.project.service.McitTaskService;


@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private McitTaskService mcitTaskService;
	
	@GetMapping("/index")
	public ModelAndView index()
	{
		List<McitTask> findAll = mcitTaskService.findAll();
		return new ModelAndView("taskIndex", "tasks", findAll);
	}
	
	@GetMapping("/createTask")
	public ModelAndView createTask()
	{
		return new ModelAndView("taskForm", "task", new McitTask());
	}
	
	@PostMapping("saveOrUpdateTask")
	public ModelAndView saveTask(McitTask mcitTask)
	{
		mcitTaskService.saveOrUpdateTask(mcitTask);
		return null;
	}
	
	@GetMapping("/editTask")
	public ModelAndView editTask(Integer taskId)
	{
//		mcitTaskService.fin
		return null;
	}
	
	@PostMapping("/updateTask")
	public ModelAndView updateTask()
	{
//		return new ModelAndView("createForm", "book", new Book());
		return null;
	}
	
	@PostMapping("/deleteTask")
	public ModelAndView deleteTask()
	{
//		return new ModelAndView("createForm", "book", new Book());
		return null;
	}
	
	private static String createBook(String title, String author, Integer year, Float price)
	{
		final String uri = "http://localhost:8081/book/create/" + title + "/" + author + "/" +  year + "/" + price;
	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);
	    return result;
	}

}
