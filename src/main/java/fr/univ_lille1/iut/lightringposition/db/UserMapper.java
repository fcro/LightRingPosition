package fr.univ_lille1.iut.lightringposition.db;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import fr.univ_lille1.iut.lightringposition.struct.User;
import fr.univ_lille1.iut.lightringposition.util.InvalidUserException;

/**
 * Permet de récupérer une instance de User lorsqu'on utilise UniDAO
 * pour trouver un utilisateur
 *
 */
public class UserMapper implements ResultSetMapper<User> {
	public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		if (r.getString("avatar") != null)
			try {
				return new User(r.getString("login"), r.getString("password"), r.getString("email"),
						r.getString("nickname"), r.getString("role"), new File(r.getString("avatar")));
			} catch (InvalidUserException e) {
				return null;
			}
		else
			try {
				return new User(r.getString("login"), r.getString("password"), r.getString("email"),
						r.getString("nickname"), r.getString("role"));
			} catch (InvalidUserException e) {
				return null;
			}
	}
}
