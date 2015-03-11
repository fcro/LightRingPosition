package fr.univ_lille1.iut.lightringposition.game;

public class Joueur {
	private String nom;
	public Joueur(){
		
	}
	public Joueur(String nom){
		this.setNom(nom);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
