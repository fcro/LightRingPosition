package fr.univ_lille1.iut.lightringposition.doc;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
