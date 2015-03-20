package fr.univ_lille1.iut.lightringposition.game;

import java.util.ArrayList;
import java.util.List;

public class TestGame {
	
	public static void main(String[] args){
		List<Joueur> liste = new ArrayList<Joueur>();
		for(int i=0;i<4;i++) {
			liste.add(new Joueur("toto"));
			liste.get(i).setId(i);
			}
		Plateau p=new Plateau(20,20, liste);
		p.generation();
		p.placementJoueur();
		System.out.println(p.afficherPlateau());
	}
}
