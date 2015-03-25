package fr.univ_lille1.iut.lightringposition.doc;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.univ_lille1.iut.lightringposition.game.Joueur;
import fr.univ_lille1.iut.lightringposition.game.Loby;
import fr.univ_lille1.iut.lightringposition.game.ObjetTransfert;
import fr.univ_lille1.iut.lightringposition.game.Plateau;
import fr.univ_lille1.iut.lightringposition.util.SecurityFilter;

@Path("jeu")

public class PlateauJeu{

	private static Map<Integer,Plateau> partie = new HashMap<Integer,Plateau>();
	@GET
	@Path("plateau/{partie}")
	@Produces(MediaType.APPLICATION_JSON)
	public Plateau getPlateau(@PathParam("partie") int numPartie){
		/*if(partie.containsKey(numPartie)) {
			return partie.get(numPartie);
		} else {*/
			List<Joueur> liste = Loby.getLobbies().get(numPartie);
			Plateau p=new Plateau(liste);
			partie.put(numPartie,p);
			partie.get(numPartie).generation();
			partie.get(numPartie).placementJoueur();
			return partie.get(numPartie);
		}
	

	@GET
	@Path("{partie}/coord/{x}/{y}/move")
	@Produces(MediaType.APPLICATION_JSON)
	public Plateau deplacement(@PathParam("partie") int numPartie, @PathParam("x") int x, @PathParam("y") int y, ContainerRequestContext context){
		SecurityFilter sec = new SecurityFilter("USER");
		sec.filter(context);
		if (partie.get(numPartie).getTour() == 0 ) {
			System.out.println("Partie Termine");
		} else if(partie.get(numPartie).verifCavalier(partie.get(numPartie),partie.get(numPartie).getListeJoueurs().get(partie.get(numPartie).getIdx()),x,y)
				&& partie.get(numPartie).verifJoueur(partie.get(numPartie).getListeJoueurs().get(partie.get(numPartie).getIdx()), sec.getLogin())) {
			partie.get(numPartie).deplacement(partie.get(numPartie), partie.get(numPartie).getListeJoueurs().get(partie.get(numPartie).getIdx()), x, y);
			return partie.get(numPartie); 
		}
		throw new WebApplicationException(Response.Status.FORBIDDEN); 
	}

	@GET
	@Path("{partie}/count")
	@Produces(MediaType.APPLICATION_JSON)
	public ObjetTransfert countCase(@PathParam("partie") int numPartie){
		ObjetTransfert obj = new ObjetTransfert();
		List<Integer> liste = partie.get(numPartie).totalCase();
		for(int i = 0 ; i<partie.get(numPartie).getListeJoueurs().size();i++) {
			obj.getScore().add(liste.get(i));
		}
		return obj;
	}
}
