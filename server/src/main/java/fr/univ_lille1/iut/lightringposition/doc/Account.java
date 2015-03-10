package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("account")
public class Account {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Youpi, j'ai récupéré une chaîne.";
    }
}
