package fr.univ_lille1.iut.lightringposition.doc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.struct.LoginPwd;
import fr.univ_lille1.iut.lightringposition.struct.User;
import fr.univ_lille1.iut.lightringposition.util.UserSessions;
import fr.univ_lille1.iut.lightringposition.util.PwdEncrypt;

@Path("account")
public class Account {

	@GET
	@Path("{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserInfo(@Context HttpServletRequest req, @PathParam("login") String login) {
		User user = DBUtil.getDAO().findUserByLogin(login);

		return user;
	}

	@POST
	@Path("auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response authenticate(@Context HttpServletRequest req, LoginPwd loginPwd) {
		User userInDB = DBUtil.getDAO().findUserByLoginPassword
				(loginPwd.getLogin(), PwdEncrypt.encrypt(loginPwd.getPassword()));

		if (userInDB != null && req != null) {
			if (!UserSessions.isUserConnected(userInDB)) {
				HttpSession session = req.getSession(true);
				UserSessions.addUser(userInDB, session);
			}

			return Response.ok("Identifiants valides").build();
		}

		return Response.status(403).entity("Identifiants invalides").build();
	}

	@POST
	@Path("disconnect")
	@Produces(MediaType.TEXT_PLAIN)
	public Response disconnect(@Context HttpServletRequest req) {
		if (req != null) {
			HttpSession session = req.getSession();
			User user = UserSessions.getUserBySession(req.getSession());
			if (user != null && session != null &&
					session.equals(UserSessions.getSessionByUser(user))) {
				UserSessions.removeUser(user);

				return Response.ok().build();
			}
		}

		return Response.status(403).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		if (!user.isValidNewUser())
			return Response.status(400).entity("Champs incomplets ou incorrects").build();

		if (DBUtil.getDAO().findUserByLogin(user.getLogin()) != null)
			return Response.status(409).entity("Ce login est déjà utilisé").build();

		DBUtil.getDAO().insertUser(user.getLogin(), PwdEncrypt.encrypt(user.getPassword()),
				user.getEmail(), user.getNickname(), null);

		return Response.status(201).entity("Utilisateur créé").build();
	}
}
