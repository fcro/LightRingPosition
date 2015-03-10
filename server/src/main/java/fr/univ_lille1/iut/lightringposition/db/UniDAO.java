package fr.univ_lille1.iut.lightringposition.db;

import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface UniDAO {
	@SqlUpdate("CREATE TABLE USER(" +
			"login			VARCHAR(20)			PRIMARY KEY," +
			"password		VARCHAR(20)," +
			"email			VARCHAR(50));" +

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
	void createDB();

	void close();
}
