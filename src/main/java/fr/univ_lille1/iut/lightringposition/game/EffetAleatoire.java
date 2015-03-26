package fr.univ_lille1.iut.lightringposition.game;

import java.util.List;
import java.util.Random;

public class EffetAleatoire {
	
	public EffetAleatoire() {
		
	}
	
	public void effect(int num, Plateau p, List<Joueur> liste, Joueur joueur, int x, int y) {
		if(num == 1) {
			Random r = new Random();
			int val; 
			do {
			val = r.nextInt(liste.size());
			} while(liste.get(val).equals(joueur));
			p.coloriage(p, liste.get(val), x, y);
		} else if (num == 2) {
			p.generation();
			p.effetAleatoire();
			p.placementJoueur();
		}
	}
}
