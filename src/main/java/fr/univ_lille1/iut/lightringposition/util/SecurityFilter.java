package fr.univ_lille1.iut.lightringposition.util;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.struct.User;

@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
	private String roleToCheck;
	private String login;
	
	public SecurityFilter(String roleToCheck) {
		this.roleToCheck = roleToCheck;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws WebApplicationException {
		String auth = requestContext.getHeaderString("AUTHORIZATION");

		if (auth == null)
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);

		auth = auth.substring("Basic ".length());

		String usernameAndPassword = new String(Base64.decodeAsString(auth));
		String[] userPswd = usernameAndPassword.split(":");

		if (userPswd == null || userPswd.length != 2)
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);

		User user = DBUtil.getUserDAO().findUserByLoginPassword(userPswd[0], PwdEncrypt.encrypt(userPswd[1]));

		if (user != null && roleToCheck != null)
			if (Role.containsRole(user.getRole()) && Role.containsRole(roleToCheck))
				if (Role.valueOf(user.getRole()) >= Role.valueOf(roleToCheck)) {
					login = user.getLogin();
					return;
				}
		
		throw new WebApplicationException(Response.Status.UNAUTHORIZED);
	}

	public String getRoleToCheck() {
		return roleToCheck;
	}

	public void setRoleToCheck(String roleToCheck) {
		this.roleToCheck = roleToCheck;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}