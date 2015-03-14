package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.struct.LoginPwd;
import fr.univ_lille1.iut.lightringposition.struct.User;
import fr.univ_lille1.iut.lightringposition.util.PwdEncrypt;

@Path("account")
public class Account {

	@GET
	@Path("{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserInfo(@PathParam("login") String login) {
		User user = DBUtil.getDAO().findUserByLogin(login);
		DBUtil.closeDAO();

		return user;
	}

	@POST
	@Path("auth")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response authenticate(LoginPwd loginPwd) {
		String loginInDB = DBUtil.getDAO().getLoginByLoginPassword
				(loginPwd.getLogin(),PwdEncrypt.encrypt(loginPwd.getPassword()));
		DBUtil.closeDAO();

		if (loginInDB != null && loginInDB.equals(loginPwd.getLogin()))
			return Response.ok("Identifiants valides").build();

		return Response.status(403).entity("Identifiants invalides").build();
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
		DBUtil.closeDAO();

		return Response.status(201).entity("Utilisateur créé").build();
	}
}
