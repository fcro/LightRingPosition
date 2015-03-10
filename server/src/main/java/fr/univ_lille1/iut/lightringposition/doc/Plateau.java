package fr.univ_lille1.iut.lightringposition.doc;

import java.util.Random;

public class Plateau {
	int val;
	char[][]plateau;
	int taille;
	char valeurObstacles;
	public Plateau(int taille, char valeurObstacles){
		this.taille = taille;
		this.valeurObstacles = valeurObstacles;
		this.plateau= new char[taille][taille];

	}

	public void generation(){
	
		for(int i=0;i<taille; ++i){
			for( int j=0; j<taille; ++j){
				plateau[i][j]='.';

			}
		}
		Random r= new Random();
		val = r.nextInt(plateau.length*plateau.length*10/100)+plateau.length*plateau.length*10/100;
		for(int i=0; i<=val;i++)
			plateau[r.nextInt(plateau.length)][r.nextInt(plateau.length)]=valeurObstacles;



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
		Plateau p= new Plateau(20,'Y');
		p.generation();
		System.out.println(p);
		
	}
}
