package fr.univ_lille1.iut.lightringposition.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import fr.univ_lille1.iut.lightringposition.struct.User;

@RegisterMapper(UserMapper.class)
public interface UniDAO {
	@SqlUpdate("CREATE TABLE USER(" +
			"login			VARCHAR(20)			PRIMARY KEY," +
			"password		VARCHAR(32)			NOT NULL," +
			"email			VARCHAR(50)			NOT NULL," +
			"nickname		VARCHAR(20)			NOT NULL," +
			"role			VARCHAR(5)			NOT NULL," +
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
			"FOREIGN KEY	(winner)			REFERENCES USER(login));" +

			"INSERT INTO USER(login, password, email, nickname, role)" +
			"VALUES('admin', 'admin', 'admin@localhost', 'administrateur', 'ADMIN');")
	void initDB();

	@SqlUpdate("INSERT INTO USER(login, password, email, nickname, role, avatar)" +
			"VALUES(:login, :password, :email, :nickname, :role, :avatar)")
	void insertUser(@Bind("login") String login, @Bind("password") String password,
			@Bind("email") String email, @Bind("nickname") String nickname,
			@Bind("role") String role, @Bind("avatar") String avatar);

	@SqlQuery("SELECT * FROM USER WHERE login = :login")
	User findUserByLogin(@Bind("login") String login);

	@SqlQuery("SELECT * FROM USER WHERE login = :login AND password = :password")
	User findUserByLoginPassword(@Bind("login") String login, @Bind("password") String password);

	void close();
}
