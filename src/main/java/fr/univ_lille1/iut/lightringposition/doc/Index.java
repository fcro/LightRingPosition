package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

import fr.univ_lille1.iut.lightringposition.util.SecurityFilter;

@Path("index")
public class Index {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getIndex(ContainerRequestContext context) {
		new SecurityFilter("USER").filter(context);

		return "<html>" +
				"<head><title>Index d'utilisateur authentifié</title></head>" +
				"<body><h1>Cette page n'est visible que des utilisateurs qui se sont authentifiés</h1></body>" +
				"</html>";
	}
}
