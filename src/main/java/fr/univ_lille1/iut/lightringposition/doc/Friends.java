package fr.univ_lille1.iut.lightringposition.doc;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.struct.User;

@Path("friends")
public class Friends {

	@GET
	@Path("{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getFriends(@PathParam("login") String login) {
		List<User> list = new ArrayList<User>();
		list.add(DBUtil.getDAO().findUserByLogin(login));
	
		return list;
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response addFriend(String login) {
		User toAdd = DBUtil.getDAO().findUserByLogin(login);
		if (toAdd != null)
			return Response.status(Response.Status.CREATED).
					entity("Utilisateur " + login + " ajouté en ami").build();

		return Response.status(Response.Status.NOT_FOUND).
				entity("L'utilisateur demandé n'existe pas").build();
	}

	@DELETE
	@Path("{login}")
	public Response deleteFriend(@PathParam("login") String login) {
		User toRemove = DBUtil.getDAO().findUserByLogin(login);
		if (toRemove != null)
			return Response.status(Response.Status.OK).
					entity("Utilisateur " + toRemove + " supprimé des amis").build();

		return Response.status(Response.Status.NOT_FOUND).
				entity("L'utilisateur demandé n'existe pas").build();
	}
}
