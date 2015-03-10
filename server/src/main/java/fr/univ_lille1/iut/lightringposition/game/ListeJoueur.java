package fr.univ_lille1.iut.lightringposition.game;

import java.util.HashMap;
import java.util.Map;

public class ListeJoueur {
	private Map<Integer, String> listeJoueur;
	
	public ListeJoueur(int nbJoueur) {
		listeJoueur = new HashMap<Integer,String>(nbJoueur);
		for(int i = 1; i <= nbJoueur; i++) {
			listeJoueur.put(i, "toto");
		}
	}

	public Map<Integer, String> getListeJoueur() {
		return listeJoueur;
	}

	public void setListeJoueur(Map<Integer, String> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

/*	public static void main(String[] args) {
		plateau.length*plateau.length*10/100
	}*/
	
	
}
