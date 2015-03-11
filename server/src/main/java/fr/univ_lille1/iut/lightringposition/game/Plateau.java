package fr.univ_lille1.iut.lightringposition.game;

import java.util.Random;

public class Plateau {
	private int nbrejoueurs;
	private int val;
	private Case[][]plateau;
	private int taille;
	private char valeurObstacles;

	public Plateau(){

	}

	public Plateau(int taille, int nbreJoueurs){
		this.taille = taille;
		this.nbrejoueurs= nbreJoueurs;
		this.plateau=new Case[taille][taille];
	}

	public void generation(){
		for(int k=0;k<taille;++k){
			for(int l=0;l<taille;++l)
				plateau[k][l]=new Case();
		}
		int reussi= 0;
		Random r= new Random();
		val = r.nextInt(plateau.length*plateau.length*10/100)+plateau.length*plateau.length*10/100;
		for(int i=0; i<=val;i++){
			int x = r.nextInt(plateau.length);
			int y= r.nextInt(plateau.length);
			plateau[x][y].setEstObstacle(true);
			plateau[x][y].setEstVide(false);
		}
		for(int j=0;reussi!=nbrejoueurs;++j){
			int x = r.nextInt(plateau.length);
			int y= r.nextInt(plateau.length);
			if(plateau[x][y].getEstVide()){
				plateau[x][y].setOccupant(new Joueur("joueur"+j+1));
				reussi++;
			}
		}




	}


	public int getVal() {
		return val;
	}
	public static void main(String[]args){
		Plateau p=new Plateau(20, 4);
		p.generation();
		System.out.println(p);
	}

	public int getNbrejoueurs() {
		return nbrejoueurs;
	}

	public void setNbrejoueurs(int nbrejoueurs) {
		this.nbrejoueurs = nbrejoueurs;
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


}
