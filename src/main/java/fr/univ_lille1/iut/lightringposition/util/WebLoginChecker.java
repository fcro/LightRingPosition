package fr.univ_lille1.iut.lightringposition.util;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.struct.LoginPwd;

/**
 * Cette classe est faite pour être appelée par des pages JSP pour vérifier si
 * l'utilisateur s'est déjà authentifié avec un login et un mot de passe valides.
 *
 */
public class WebLoginChecker {
	LoginPwd loginPwd;

	public WebLoginChecker(String login, String password) throws InvalidUserException {
		this.loginPwd = new LoginPwd(login, password);
	}

	public void checkAndRedirect(String uri) {
		if (DBUtil.getUserDAO().findUserByLoginPassword(loginPwd.getLogin(),
				loginPwd.getPassword()) != null) {

			try {
				Response.seeOther(new URI(uri)).build();
			} catch (URISyntaxException urise) {
				urise.printStackTrace();
			}
		}
	}
}
