package com.mcit.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcit.project.dao.McitUserDao;
import com.mcit.project.model.McitAuthorities;
import com.mcit.project.model.McitUser;

@Service
public class McitUserServiceImpl implements McitUserService, UserDetailsService {

	@Autowired
	private McitUserDao mcitUserDao;

	@Override
	@Transactional(readOnly = true)
	public List<McitUser> findAll() {
		List<McitUser> findAll = mcitUserDao.findAll();
		List<McitUser> users = new ArrayList<McitUser>();
		for(McitUser user : findAll){
			Set<McitAuthorities> mcitAuthorities = user.getMcitAuthorities();
			user.setUserRole(mcitAuthorities.iterator().next().getAuthority());
			users.add(user);
		}
		return users;
	}

	@Override
	public void saveUser(McitUser user) {
		String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encoded);
		mcitUserDao.saveOrUpdateUser(user);
	}

	@Override
	public void updateUser(McitUser user) {
		mcitUserDao.saveOrUpdateUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<McitUser> findAllMembersByProjectId(Integer projectId) {
		return mcitUserDao.findAllMembersByProjectId(projectId);
	}

	@Override
	@Transactional(readOnly = true)
	public McitUser findById(Integer userId) {
		McitUser findById = mcitUserDao.findById(userId);
		String authority = findById.getMcitAuthorities().iterator().next().getAuthority();
		findById.setUserRole(authority);
		return mcitUserDao.findById(userId);
	}

	@Override
	public List<McitUser> getLeaders() {
		return mcitUserDao.getLeaders();
	}

	@Override
	public List<McitUser> getMembers() {
		return mcitUserDao.getMembers();
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		McitUser user = mcitUserDao.findUserByUsername(username);
		UserBuilder builder = null;
		if (user != null) {

			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			String[] authorities = user.getMcitAuthorities().stream().map(a -> a.getAuthority()).toArray(String[]::new);

			builder.authorities(authorities);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		UserDetails build = builder.build();
		
		return builder.build();
	}

	@Override
	public McitUser getCurrentMcitUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		McitUser user = mcitUserDao.findUserByUsername(username);
		return user;
	}

	@Override
	public boolean ifUserExistsWithSameUserId(String username) {
		McitUser user = mcitUserDao.findUserByUsername(username);
		if(user != null)
			return true;
		return false;
	}
	
	@Override
    public boolean hasRole(String role) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority()))
                return true;
        }
        return false;
    }

}
