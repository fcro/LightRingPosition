package fr.univ_lille1.iut.lightringposition.game;

public class Case {
	private boolean estVide;
	private boolean estObstacle;
	private boolean effetAleatoire;
	private Joueur occupant;
	private Joueur proprietaire;
	
	public Case(){
		estVide = true;
		estObstacle = false;
		occupant = null;
		proprietaire = null;
	}
	
	public boolean isEffetAleatoire() {
		return effetAleatoire;
	}

	public void setEffetAleatoire(boolean effetAleatoire) {
		this.effetAleatoire = effetAleatoire;
	}

	public boolean getEstVide() {
		return estVide;
	}
	
	public void setEstVide(boolean estVide) {
		this.estVide = estVide;
	}
	
	public boolean getEstObstacle() {
		return estObstacle;
	}
	
	public void setEstObstacle(boolean estObstacle) {
		this.estObstacle = estObstacle;
	}
	
	public Joueur getOccupant() {
		return occupant;
	}
	
	public void setOccupant(Joueur occupant) {
		this.occupant = occupant;
	}
	
	public Joueur getProprietaire() {
		return proprietaire;
	}
	
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}

}
