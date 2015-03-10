package fr.univ_lille1.iut.lightringposition.doc;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.struct.User;

@Path("account")
public class Account {

	@GET
	@Path("{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserInfo(@PathParam("login") String login) {
		User user = new User(login, "azerty", "address@mai.tld", "meh",
				new File("img.png"));

		return user;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		if (user == null || !user.isValid())
			return Response.status(400).entity("Utilisteur invalide").build();

		return Response.status(201).entity("Utilisateur créé").build();
	}
}
