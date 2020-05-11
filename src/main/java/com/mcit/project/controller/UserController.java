package com.mcit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.project.model.McitUser;
import com.mcit.project.service.McitUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private McitUserService mcitUserService;

	@GetMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("userIndex", "users", mcitUserService.findAll());
	}

	@GetMapping("/createUser")
	public ModelAndView createUser() {
		return new ModelAndView("userForm", "user", new McitUser());
	}

	@PostMapping("saveOrUpdateUser")
	public ModelAndView saveOrUpdateUser(McitUser user) {
		mcitUserService.saveOrUpdateUser(user);
		return new ModelAndView("userIndex", "users", mcitUserService.findAll());
	}

	@GetMapping("/editUser/{userId}")
	public ModelAndView editUser(@PathVariable(name = "userId") Integer userId) {
		return new ModelAndView("userForm", "user", mcitUserService.findById(userId));
	}

}
