package fr.univ_lille1.iut.lightringposition.util;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.struct.User;

public class WebSecurityContext implements SecurityContext {
	private final User user;
	private final String roleToCheck;

	public WebSecurityContext(String login, String pwd, String roleToCheck) {
		this.user = DBUtil.getDAO().findUserByLoginPassword(login, pwd);
		this.roleToCheck = roleToCheck;
	}

	@Override
	public Principal getUserPrincipal() {
		return null;
	}

	@Override
	public boolean isUserInRole(String roleToCheck) {
		if (user.getRole().equals(roleToCheck))
			return true;

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
