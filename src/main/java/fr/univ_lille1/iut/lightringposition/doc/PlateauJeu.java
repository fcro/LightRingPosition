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
	static List<Joueur> liste = new ArrayList<Joueur>();
	private static Plateau p=new Plateau(20, liste);
	public static int idx = 0;	
	@GET
	@Path("plateau")
	@Produces(MediaType.APPLICATION_JSON)
	public Plateau getPlateau(){
		p.generation();
		p.placementJoueur();
		return p; 
	}
		
	@GET
	@Path("coord/{x}/{y}/move")
	public Plateau deplacement(@PathParam("x") int x, @PathParam("y") int y){
		System.out.println(p.getPlateau()[x][y].getEstVide());
		if(p.verifCavalier(p.getPlateau(),p.getListeJoueurs().get(idx),x,y)) {
			System.out.println("Coup correcte");
			p.deplacement(p.getPlateau(), p.getListeJoueurs().get(idx), x, y);
		} else {
			System.out.println("Coup FAUX");
		}
		return p; 
	}
}
