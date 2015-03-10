package fr.univ_lille1.iut.lightringposition.game;

import java.util.Random;

public class Plateau {
	int val;
	Case[][]plateau;
	int taille;
	public Plateau(int taille){
		this.taille = taille;
		this.plateau= new Case[taille][taille];

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
	public String toString(){

		String aString;     
		aString = "";
		int column;
		int row;

		for (row = 0; row < plateau.length; row++) {
			for (column = 0; column < plateau[0].length; column++ ) {
				aString = aString + " " + plateau[row][column];
			}
			aString = aString + "\n";
		}

		return aString;
	}




	public static void main(String[]args){
		Plateau p= new Plateau(20);
		p.generation();
		System.out.println(p);

	}
	public void placementJoueur(){

	}

	public int getVal() {
		return val;
	}
}
