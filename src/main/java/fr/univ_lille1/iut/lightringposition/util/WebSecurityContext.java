package fr.univ_lille1.iut.lightringposition.util;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public class WebSecurityContext implements SecurityContext {
	private final String login;
	private final String password;
	private final String role;

	public WebSecurityContext(String user, String pwd, String role) {
		this.login = user;
		this.password = pwd;
		this.role = role;
	}

	@Override
	public Principal getUserPrincipal() {
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		if (role != null) {
			if (role.trim().equals(this.role))
				return true;
		}

		return false;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public String getAuthenticationScheme() {
		return BASIC_AUTH;
	}

}
