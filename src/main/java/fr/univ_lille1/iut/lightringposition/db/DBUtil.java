package fr.univ_lille1.iut.lightringposition.db;

import java.io.File;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;

public class DBUtil {
	private static String dbUrl = "jdbc:h2:./lrp";
	private static DataSource ds;
	private static DBI dbi;

	/**
	 * Crée (si elle n'existe pas) et retourne un objet permettant d'appeler
	 * les méthodes de manipulation de base de donnée fournies par UniDAO
	 * @return instance de UniDAO
	 */
	public static UniDAO getDAO() {
		if (ds == null)
			ds = JdbcConnectionPool.create(dbUrl, "dba", "");
		if (dbi == null)
			dbi = new DBI(ds);

		return dbi.open(UniDAO.class);
	}

	public static DataSource getDs() {
		return ds;
	}

	public static void useTestDB() {
		dbUrl = "jdbc:h2:./test_lrp";
	}

	public static void deleteTestDB() {
		new File("test_lrp.mv.db").delete();
		new File("test_lrp.trace.db").delete();
	}
}
