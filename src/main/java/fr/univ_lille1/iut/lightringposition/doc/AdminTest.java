package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;

@Path("admin")
public class AdminTest {
	@GET
	@Path("dbcreate")
	public void createDB() {
		DBUtil.getDAO().initDB();
		DBUtil.closeDAO();
	}
}
