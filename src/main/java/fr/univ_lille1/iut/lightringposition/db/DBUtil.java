package fr.univ_lille1.iut.lightringposition.db;

import java.io.File;

import org.skife.jdbi.v2.DBI;

public class DBUtil {
	private static String dbUrl = "jdbc:h2:./lrp";
	private static DBI dbi;
	private static UniDAO dao;

	/**
	 * Crée (si elle n'existe pas) et retourne un objet permettant d'appeler
	 * les méthodes de manipulation de base de donnée fournies par UniDAO
	 * @return instance de UniDAO
	 */
	public static UniDAO getDAO() {
		if (dbi == null)
			dbi = new DBI(dbUrl, "dba", "");
		if (dao == null)
			dao = dbi.open(UniDAO.class);

		return dao;
	}

	public static void useTestDB() {
		dbUrl = "jdbc:h2:./test_lrp";
	}

	public static void deleteTestDB() {
		new File("test_lrp.mv.db").delete();
		new File("test_lrp.trace.db").delete();
	}

	/**
	 * Ferme le DAO et la DBI associée
	 */
	public static void closeDAO() {
		dao.close();
		dbi.close(UniDAO.class);
	}
}
