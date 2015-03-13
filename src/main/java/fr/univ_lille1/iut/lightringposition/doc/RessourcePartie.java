package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import fr.univ_lille1.iut.lightringposition.game.Joueur;

@Path("partie")
public class RessourcePartie {
		
		@POST
		@Path("rejoindre")
		public void addJoueur(Joueur login) {
			//listeJoueur.addJoueur(login);
		}
		
		
}
