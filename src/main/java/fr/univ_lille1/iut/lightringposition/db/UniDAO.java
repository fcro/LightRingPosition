package fr.univ_lille1.iut.lightringposition.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import fr.univ_lille1.iut.lightringposition.struct.User;

@RegisterMapper(UserMapper.class)
public interface UniDAO {
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
