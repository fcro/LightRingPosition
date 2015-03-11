package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.univ_lille1.iut.lightringposition.game.Plateau;

@Path("jeu")

public class PlateauJeu{
	Plateau p=new Plateau(20,4);
	@GET
	@Path("plateau")
	@Produces(MediaType.APPLICATION_JSON)
public Plateau getPlateau(){
 p.generation();
 return p;
}
}
