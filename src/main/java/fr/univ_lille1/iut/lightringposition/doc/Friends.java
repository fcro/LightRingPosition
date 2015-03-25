package fr.univ_lille1.iut.lightringposition.doc;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.struct.User;
import fr.univ_lille1.iut.lightringposition.util.SecurityFilter;

@Path("friends")
public class Friends {

	@GET
	@Path("{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getFriends(@PathParam("login") String login) {
		List<String> friends;

		if (DBUtil.getUserDAO().findUserByLogin(login) == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);

		friends = DBUtil.getFriendDAO().findFriendsByLogin(login);

		return friends;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response addFriend(@Context ContainerRequestContext context, String friend) {
		SecurityFilter sf = new SecurityFilter("USER");
		sf.filter(context);
		String login = sf.getLogin();

		User toAdd = DBUtil.getUserDAO().findUserByLogin(friend);

		if (toAdd == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (DBUtil.getFriendDAO().findFriendByLoginAndFriend(login, friend) != null)
			return Response.status(Response.Status.CONFLICT).build();

		DBUtil.getFriendDAO().insertFriend(login, friend);

		return Response.status(Response.Status.CREATED).build();
	}

	@DELETE
	@Path("{friend}")
	public Response deleteFriend(@Context ContainerRequestContext context,
			@PathParam("friend") String friend) {
		SecurityFilter sf = new SecurityFilter("USER");
		sf.filter(context);
		String login = sf.getLogin();

		if (DBUtil.getUserDAO().findUserByLogin(friend) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (DBUtil.getFriendDAO().findFriendByLoginAndFriend(login, friend) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		DBUtil.getFriendDAO().insertFriend(login, friend);

		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String deleteFriends() {
		return "<h1>YOU CAN'T GET FRIENDS !</h1>" +
				"<iframe src=\"http://www.youtube.com/embed/2VbODnX0dVs?autoplay=1\" " +
				"width=\"960\" height=\"447\" frameborder=\"0\" allowfullscreen></iframe>\"";
	}
}
