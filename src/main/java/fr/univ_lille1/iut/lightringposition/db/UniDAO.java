package fr.univ_lille1.iut.lightringposition.db;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import fr.univ_lille1.iut.lightringposition.struct.User;
import fr.univ_lille1.iut.lightringposition.util.InvalidUserException;

public interface UniDAO {

	public class UserMapper implements ResultSetMapper<User> {
		public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
			if (r.getString("avatar") != null)
				try {
					return new User(r.getString("login"), r.getString("password"), r.getString("email"),
						r.getString("nickname"), new File(r.getString("avatar")));
				} catch (InvalidUserException e) {
					return null;
				}
			else
				try {
					return new User(r.getString("login"), r.getString("password"), r.getString("email"),
							r.getString("nickname"));
				} catch (InvalidUserException e) {
					return null;
				}
		}
	}

	@SqlUpdate("CREATE TABLE USER(" +
			"login			VARCHAR(20)			PRIMARY KEY," +
			"password		VARCHAR(31)			NOT NULL," +
			"email			VARCHAR(50)			NOT NULL," +
			"nickname		VARCHAR(20)			NOT NULL," +
			"avatar			VARCHAR(255));" +

			"CREATE TABLE FRIEND(" +
			"login			VARCHAR(20)," +
			"friend_login	VARCHAR(20)," +
			"FOREIGN KEY	(login)				REFERENCES USER(login)," +
			"FOREIGN KEY	(friend_login)		REFERENCES USER(login));" +

			"CREATE TABLE GAME(" +
			"id				INT AUTO_INCREMENT	PRIMARY KEY," +
			"login_a		VARCHAR(20)," +
			"login_b		VARCHAR(20)," +
			"login_c		VARCHAR(20)," +
			"winner			VARCHAR(20)," +
			"FOREIGN KEY	(login_a)			REFERENCES USER(login)," +
			"FOREIGN KEY	(login_b)			REFERENCES USER(login)," +
			"FOREIGN KEY	(login_c)			REFERENCES USER(login)," +
			"FOREIGN KEY	(winner)			REFERENCES USER(login));")
	void initDB();

	@SqlUpdate("INSERT INTO USER(login, password, email, nickname, avatar)" +
			"VALUES(:login, :password, :email, :nickname, :avatar)")
	void insertUser(@Bind("login") String login, @Bind("password") String password,
			@Bind("email") String email, @Bind("nickname") String nickname, @Bind("avatar") String avatar);

	@SqlQuery("SELECT * FROM USER WHERE login = :login")
	@Mapper(UserMapper.class)
	User findUserByLogin(@Bind("login") String login);

	@SqlQuery("SELECT login FROM USER WHERE login = :login AND password = :password")
	String getLoginByLoginPassword(@Bind("login") String login, @Bind("password") String password);

	void close();
}
