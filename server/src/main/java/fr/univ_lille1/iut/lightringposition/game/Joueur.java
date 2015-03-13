package fr.univ_lille1.iut.lightringposition.game;

public class Joueur {
	private String nom;
	private int nbCase;
	private int score;
	
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
	public int getNbCase() {
		return nbCase;
	}
	public void setNbCase(int nbCase) {
		this.nbCase = nbCase;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
