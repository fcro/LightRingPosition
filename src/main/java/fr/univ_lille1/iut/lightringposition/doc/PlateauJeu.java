package fr.univ_lille1.iut.lightringposition.doc;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.univ_lille1.iut.lightringposition.game.Joueur;
import fr.univ_lille1.iut.lightringposition.game.Plateau;

@Path("jeu")

public class PlateauJeu{
	List<Joueur> liste = new ArrayList<Joueur>();

	Plateau p=new Plateau(20, liste);
	@GET
	@Path("plateau")
	@Produces(MediaType.APPLICATION_JSON)
	public Plateau getPlateau(){
		for(int i=0;i<4;i++) {
			liste.add(new Joueur("toto"));
			liste.get(i).setId(i);
		}
		p.generation();
		p.placementJoueur();
		return p; 
	}
	
	@POST
	@Path("coord/{x}/{y}")
	@Produces(MediaType.APPLICATION_JSON)
	public void postCoord(@PathParam("x") int x, @PathParam("y") int y) {
		p.getCoord()[0] = x;
		p.getCoord()[1] = y;
		System.out.println("Coord x :" + x + " Coord y :"+y);
	}
}
