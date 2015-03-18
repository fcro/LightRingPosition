package fr.univ_lille1.iut.lightringposition.util;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;

@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
	String roleToCheck;

	public SecurityFilter(String roleToCheck) {
		this.roleToCheck = roleToCheck;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) {
		String auth = requestContext.getHeaderString("AUTHORIZATION");

		if (auth == null) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}

		auth = auth.substring("Basic ".length());

		String usernameAndPassword = new String(Base64.decodeAsString(auth));
		String[] userPswd = usernameAndPassword.split(":");

		if (userPswd == null || userPswd.length != 2)
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);

		WebSecurityContext securityContext = new WebSecurityContext(userPswd[0], userPswd[1], roleToCheck);

		if (securityContext.isUserInRole(roleToCheck))
			requestContext.setSecurityContext(securityContext);
		else {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}
}