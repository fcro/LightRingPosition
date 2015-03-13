package fr.univ_lille1.iut.lightringposition.game;

import java.util.List;
import java.util.Random;

public class Plateau {

	private List<Joueur> listeJoueurs;
	private int val;
	private Case[][]plateau;
	private int taille;
	private char valeurObstacles;

	public Plateau() {}

	public Plateau(int taille, List<Joueur> listeJoueurs){
		this.taille = taille;
		this.listeJoueurs = listeJoueurs;
		this.plateau=new Case[taille][taille];
	}

	public void generation(){

		for(int i=0;i<taille; ++i){
			for( int j=0; j<taille; ++j){
				plateau[i][j] = new Case();
			}
		}

		Random r= new Random();
		val = r.nextInt(plateau.length*plateau.length*10/100)+plateau.length*plateau.length*10/100;
		for(int i=0; i<=val;i++) {
			int x = r.nextInt(plateau.length);
			int y = r.nextInt(plateau.length);
			plateau[x][y].setEstObstacle(true);
			plateau[x][y].setEstVide(false);
		}
	}

	public int getVal() {
		return val;
	}


	public List<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}

	public void setListeJoueurs(List<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}

	public Case[][] getPlateau() {
		return plateau;
	}

	public void setPlateau(Case[][] plateau) {
		this.plateau = plateau;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public char getValeurObstacles() {
		return valeurObstacles;
	}

	public void setValeurObstacles(char valeurObstacles) {
		this.valeurObstacles = valeurObstacles;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public void placementJoueur(){
		for(int i=0; i<listeJoueurs.size();i++) {
			int x,y;
			Random r= new Random();
			do {
				x = r.nextInt(plateau.length);
				y = r.nextInt(plateau.length);
			}while(!plateau[x][y].getEstVide());
			plateau[x][y].setOccupant(listeJoueurs.get(i));
			plateau[x][y].setEstVide(false);
			plateau[x][y].setEstObstacle(false);
		}
	}
	
	public String afficherPlateau() {
		String ch =".";
		for(int i=0;i<plateau.length;i++) {
			for(int j=0;j<plateau.length;j++) {
				if(plateau[i][j].getEstVide()) ch+= ".";
				else if(plateau[i][j].getEstObstacle()) ch+="X";
				else if(plateau[i][j].getOccupant() != null)
					ch += listeJoueurs.indexOf(plateau[i][j].getOccupant());
			}
			ch+="\n";
		}
		return ch;
	}
}
