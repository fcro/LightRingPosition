package fr.univ_lille1.iut.lightringposition.db;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

import fr.univ_lille1.iut.lightringposition.util.PwdEncrypt;

public class DBUtil {
	private static String dbUrl = System.getProperty("user.dir") +
			System.getProperty("file.separator") + "lrp.db";
	private static SQLiteDataSource ds;
	private static DBI dbi;

	/**
	 * Crée (si elle n'existe pas) et retourne un objet permettant d'appeler
	 * les méthodes de manipulation de base de donnée fournies par UniDAO
	 * @return instance de UniDAO
	 */
	public static UniDAO getDAO() {
		if (ds == null) {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:" + dbUrl);
		}
		if (dbi == null)
			dbi = new DBI(ds);

		return dbi.open(UniDAO.class);
	}

	public static DataSource getDs() {
		return ds;
	}

	public static void useTestDB() {
		dbUrl += ".test";
	}

	public static void deleteDB() {
		File f = new File(dbUrl);
		if (f.exists()) {
			f.delete();
		}
	}

	public static void initDB(String adminPwd) {
		final String USERTABLE = "CREATE TABLE USER(" +
				"login			VARCHAR(20)			PRIMARY KEY," +
				"password		VARCHAR(32)			NOT NULL," +
				"email			VARCHAR(50)			NOT NULL," +
				"nickname		VARCHAR(20)			NOT NULL," +
				"role			VARCHAR(5)			NOT NULL," +
				"avatar			VARCHAR(255))";

		final String FRIENDTABLE = "CREATE TABLE FRIEND(" +
				"login			VARCHAR(20)," +
				"friend_login	VARCHAR(20)," +
				"FOREIGN KEY	(login)				REFERENCES USER(login)," +
				"FOREIGN KEY	(friend_login)		REFERENCES USER(login))";

		final String GAMETABLE = "CREATE TABLE GAME(" +
				"id				INT AUTO_INCREMENT	PRIMARY KEY," +
				"login_a		VARCHAR(20)," +
				"login_b		VARCHAR(20)," +
				"login_c		VARCHAR(20)," +
				"winner			VARCHAR(20)," +
				"FOREIGN KEY	(login_a)			REFERENCES USER(login)," +
				"FOREIGN KEY	(login_b)			REFERENCES USER(login)," +
				"FOREIGN KEY	(login_c)			REFERENCES USER(login)," +
				"FOREIGN KEY	(winner)			REFERENCES USER(login))";

		final String INSERTADMIN = "INSERT INTO USER(login, password, email, nickname, role)" +
				"VALUES('admin', ?, 'admin@localhost', 'administrateur', 'ADMIN')";

		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:" + dbUrl);
			ds.getConnection().createStatement().executeUpdate(USERTABLE);
			ds.getConnection().createStatement().executeUpdate(FRIENDTABLE);
			ds.getConnection().createStatement().executeUpdate(GAMETABLE);

			PreparedStatement stmt = ds.getConnection().prepareStatement(INSERTADMIN);
			stmt.setString(1, PwdEncrypt.encrypt(adminPwd));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
