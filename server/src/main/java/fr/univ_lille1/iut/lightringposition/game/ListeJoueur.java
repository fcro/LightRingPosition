package fr.univ_lille1.iut.lightringposition.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListeJoueur {
	private List <String> listeJoueur = new ArrayList<String>();
	
	public ListeJoueur() {	}

	public List<String> getListeJoueur() {
		return listeJoueur;
	}

	public void setListeJoueur(List<String> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}

	public void addJoueur(String login) {
		listeJoueur.add(login);
	}
/*	public static void main(String[] args) {
		plateau.length*plateau.length*10/100
	}*/
	
	
}
