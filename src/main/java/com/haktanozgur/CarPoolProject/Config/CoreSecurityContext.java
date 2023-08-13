package com.haktanozgur.CarPoolProject.Config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.haktanozgur.CarPoolProject.Entity.User;

;

public class CoreSecurityContext {

	public static User getUserDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User)
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User details = new User();
		details.setUsername(principal.toString());

		return details;
	}

	public static OAuth2Authentication getOauth2Authentication() {
		return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
	}

}
