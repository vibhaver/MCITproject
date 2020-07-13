package com.mcit.project.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcit.project.model.McitAuthorities;
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

	@PostMapping("saveUser")
	public ModelAndView saveUser(McitUser user) {
		if(user.getUsername() != null) {
			boolean ifUserExistsWithSameUserId = mcitUserService.ifUserExistsWithSameUserId(user.getUsername());
			if(ifUserExistsWithSameUserId)
				throw new RuntimeException("User with same username already exists");
		}
		Set<McitAuthorities> authorities = new HashSet<McitAuthorities>();
		if (user.getUserRole() == null) {
			authorities.add(new McitAuthorities("ROLE_ADMIN", user));
			
		} else {
			authorities.add(new McitAuthorities(user.getUserRole(), user));
		}
		user.setMcitAuthorities(authorities);
		mcitUserService.saveUser(user);
		return new ModelAndView("redirect:/user/index");
	}
	
	@PostMapping("updateUser")
	public ModelAndView updateUser(McitUser user) {
		McitUser findById = mcitUserService.findById(user.getUserId());
		if (user.getUserRole() != findById.getUserRole()) {
			Set<McitAuthorities> mcitAuthorities = findById.getMcitAuthorities();
			mcitAuthorities.iterator().next().setAuthority(user.getUserRole());
			findById.setMcitAuthorities(mcitAuthorities);
		}
		findById.setFirstName(user.getFirstName());
		findById.setLastName(user.getLastName());
		mcitUserService.updateUser(findById);
		return new ModelAndView("redirect:/user/index");
	}

	@GetMapping("/editUser/{userId}")
	public ModelAndView editUser(@PathVariable(name = "userId") Integer userId) {
		return new ModelAndView("userUpdateForm", "user", mcitUserService.findById(userId));
	}

}
