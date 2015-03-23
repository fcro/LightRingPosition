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
	private static Plateau p=new Plateau(40,20, liste);
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
		if (p.getTour() == 0 ) {
			System.out.println("Partie Termine");
		} else if(p.verifCavalier(p,p.getListeJoueurs().get(idx),x,y)) {
			p.deplacement(p, p.getListeJoueurs().get(idx), x, y);
		}
		return p; 
	}
	
	@GET
	@Path("count")
	public List<Integer> countCase(){
		List<Integer> a;
		try {
			a = p.totalCase();
		} catch(Exception e) {
			a = new ArrayList<Integer>();
			System.out.println(e.getMessage());
		}
		return a;
	}
}
