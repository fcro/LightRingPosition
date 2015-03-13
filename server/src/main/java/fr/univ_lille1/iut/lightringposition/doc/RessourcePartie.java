package fr.univ_lille1.iut.lightringposition.doc;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import fr.univ_lille1.iut.lightringposition.game.ListeJoueur;

@Path("partie")
public class RessourcePartie {
		ListeJoueur listeJoueur = new ListeJoueur();
		
		@POST
		@Path("rejoindre")
		public void addJoueur(String login) {
			listeJoueur.addJoueur(login);
		}
		
		
}
