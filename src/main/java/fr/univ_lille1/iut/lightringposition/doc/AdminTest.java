package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.util.SecurityFilter;

@Path("admin")
public class AdminTest {
	@GET
	@Path("dbcreate")
	public Response createDB() {
		DBUtil.getDAO().initDB();

		return Response.ok().build();
	}

	@GET
	@Path("canihazcheezbrgr")
	public Response cheezbrgr(ContainerRequestContext context) {
		new SecurityFilter("admin").filter(context);

		return Response.ok("Ressource accessible").build();
	}
}
