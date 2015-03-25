package fr.univ_lille1.iut.lightringposition.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plateau {

	private List<Joueur> listeJoueurs;
	private int val;
	private Case[][]plateau;
	private int largeur, hauteur;
	private char valeurObstacles;
	private int tour = 100;
	private int idx = 0;
	private int[] countCase;

	public Plateau() {}

	public Plateau(List<Joueur> listeJoueurs){
		this.listeJoueurs = listeJoueurs;
		for(int i=0;i<7;i++) {
			this.listeJoueurs.add(new Joueur("toto_"+i));
			this.listeJoueurs.get(i).setId(i);
		}
	
		this.largeur = this.listeJoueurs.size() * 6;
		this.hauteur = this.listeJoueurs.size() * 4;

		this.plateau=new Case[this.largeur][this.hauteur];
		

	}

	public void generation(){

		for(int i=0;i<largeur; ++i){
			for( int j=0; j<hauteur; ++j){
				plateau[i][j] = new Case();
			}
		}

		Random r= new Random();
		val = r.nextInt(largeur*hauteur*10/100)+largeur*hauteur*10/100;
		for(int i=0; i<=val;i++) {
			int x = r.nextInt(largeur);
			int y = r.nextInt(hauteur);
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

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public void placementJoueur(){
		for(int i=0; i<listeJoueurs.size();i++) {
			int x,y;
			Random r= new Random();
			do {
				x = r.nextInt(largeur);
				y = r.nextInt(hauteur);
			}while(!plateau[x][y].getEstVide());
			plateau[x][y].setOccupant(listeJoueurs.get(i));
			plateau[x][y].setEstVide(false);
			plateau[x][y].setEstObstacle(false);
			listeJoueurs.get(i).setCoordX(x);
			listeJoueurs.get(i).setCoordY(y);
		}
	}

	public boolean verifCavalier(Plateau p, Joueur joueur, int coordX, int coordY) {
		if(!p.getPlateau()[coordX][coordY].getEstVide()) return false;
		else if(((Math.abs(joueur.getCoordX()-coordX) == 1 && Math.abs(joueur.getCoordY() - coordY)==2)) 
				||((Math.abs(joueur.getCoordX()-coordX) == 2 && Math.abs(joueur.getCoordY() - coordY)==1)) ) return true;
		else return false;
	}

	public void deplacement(Plateau p, Joueur joueur, int coordX, int coordY) {
		if(verifCavalier(p,joueur,coordX,coordY)) {
			p.getPlateau()[joueur.getCoordX()][joueur.getCoordY()].setOccupant(null);
			p.getPlateau()[joueur.getCoordX()][joueur.getCoordY()].setProprietaire(joueur);
			p.getPlateau()[joueur.getCoordX()][joueur.getCoordY()].setEstVide(true);
			p.getPlateau()[coordX][coordY].setOccupant(joueur);
			p.getPlateau()[coordX][coordY].setEstVide(false);
			joueur.setCoordX(coordX);
			joueur.setCoordY(coordY);
			coloriage(p,joueur,coordX,coordY);
			p.setTour(p.getTour() - 1);
			if (p.getIdx() + 1>= listeJoueurs.size()){
				p.setIdx(0);
			} else {
				p.setIdx(p.getIdx()+1);
			}
		}
	}

	public int[] getCountCase() {
		return countCase;
	}

	public void setCountCase(int[] countCase) {
		this.countCase = countCase;
	}

	public void coloriage(Plateau p, Joueur joueur, int coordX, int coordY) {
		c_ligne(p, joueur, coordX, coordY);
		c_colonne(p, joueur, coordX, coordY);
		c_diagonale(p, joueur, coordX, coordY);
	}

	public void c_ligne(Plateau p, Joueur joueur, int CoordX, int CoordY) {
		int i = 1;
		int j = -1;
		while(CoordX+i < largeur  && (p.getPlateau()[CoordX+i][CoordY].getEstVide())) {
			if (p.getPlateau()[CoordX+i][CoordY].getProprietaire() != null && !p.getPlateau()[CoordX+i][CoordY].getProprietaire().equals(joueur)) { 
				p.getPlateau()[CoordX+i][CoordY].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX+i][CoordY].setProprietaire(joueur);
				i++;
			}
		}
		while (CoordX + j >= 0 && (p.getPlateau()[CoordX+j][CoordY].getEstVide())) {
			if (p.getPlateau()[CoordX+j][CoordY].getProprietaire() != null && !p.getPlateau()[CoordX+j][CoordY].getProprietaire().equals(joueur)) {
				p.getPlateau()[CoordX+j][CoordY].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX+j][CoordY].setProprietaire(joueur);
				j--;
			}
		}
	}

	public void c_colonne(Plateau p, Joueur joueur, int CoordX, int CoordY) {
		int i = 1;
		int j = -1;
		while(CoordY+i < hauteur  && (p.getPlateau()[CoordX][CoordY+i].getEstVide())) {
			if (p.getPlateau()[CoordX][CoordY+i].getProprietaire() != null && !p.getPlateau()[CoordX][CoordY+i].getProprietaire().equals(joueur)) {
				p.getPlateau()[CoordX][CoordY+i].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX][CoordY+i].setProprietaire(joueur);
				i++;
			}
		}

		while (CoordY + j >= 0 && (p.getPlateau()[CoordX][CoordY+j].getEstVide())) {
			if (p.getPlateau()[CoordX][CoordY+j].getProprietaire() != null && !p.getPlateau()[CoordX][CoordY+j].getProprietaire().equals(joueur)) {
				p.getPlateau()[CoordX][CoordY+j].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX][CoordY+j].setProprietaire(joueur);
				j--;
			}
		}
	}

	public void c_diagonale(Plateau p, Joueur joueur, int CoordX, int CoordY) {
		int i = 1;
		int j = -1;
		while(CoordX+i < largeur && CoordY+i < hauteur  && (p.getPlateau()[CoordX+i][CoordY+i].getEstVide())) {
			if (p.getPlateau()[CoordX+i][CoordY+i].getProprietaire() != null && !p.getPlateau()[CoordX+i][CoordY+i].getProprietaire().equals(joueur)) {
				p.getPlateau()[CoordX+i][CoordY+i].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX+i][CoordY+i].setProprietaire(joueur);
				i++;
			}
		}
		i=1;
		j=-1;
		while (CoordX + j >= 0 && CoordY + j >= 0 && (p.getPlateau()[CoordX+j][CoordY+j].getEstVide())) {
			if (p.getPlateau()[CoordX+j][CoordY+j].getProprietaire() != null && !p.getPlateau()[CoordX+j][CoordY+j].getProprietaire().equals(joueur)) {
				p.getPlateau()[CoordX+j][CoordY+j].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX+j][CoordY+j].setProprietaire(joueur);
				j--;
			}
		}
		i=1;
		j=-1;
		while(CoordX+i < largeur && CoordY+j >= 0  && (p.getPlateau()[CoordX+i][CoordY+j].getEstVide())) {
			if (p.getPlateau()[CoordX+i][CoordY+j].getProprietaire() != null && !p.getPlateau()[CoordX+i][CoordY+j].getProprietaire().equals(joueur)) {
				p.getPlateau()[CoordX+i][CoordY+j].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX+i][CoordY+j].setProprietaire(joueur);
				i++;
				j--;
			}
		}
		i=1;
		j=-1;
		while (CoordX + j >= 0 && CoordY + i < hauteur && (p.getPlateau()[CoordX+j][CoordY+i].getEstVide())) {
			if (p.getPlateau()[CoordX+j][CoordY+i].getProprietaire() != null && !p.getPlateau()[CoordX+j][CoordY+i].getProprietaire().equals(joueur)) {
				p.getPlateau()[CoordX+j][CoordY+i].setProprietaire(joueur);
				break;
			} else {
				p.getPlateau()[CoordX+j][CoordY+i].setProprietaire(joueur);
				i++;

				j--;
			}
		}
	}

	public List<Integer> totalCase() {
		countCase = new int[getListeJoueurs().size()];
		for(int i = 0; i < getCountCase().length; i++) {
			countCase[i] = 0;
		}
		for(int i = 0; i<getLargeur();i++) {
			for(int j = 0; j<getHauteur();j++) {
				for(int k = 0 ; k<getListeJoueurs().size() ; k++) {
					if((getPlateau()[i][j].getProprietaire() != null && getPlateau()[i][j].getProprietaire().getId() == k)
							||(getPlateau()[i][j].getOccupant() != null && getPlateau()[i][j].getOccupant().getId() == k)) {
						countCase[k]++;
					}
				}
			}
		}
		List<Integer> score = new ArrayList<Integer>();
		for(int i = 0 ; i <getListeJoueurs().size();i++) {
			score.add(countCase[i]);
		}
		return score;
	}

	public String afficherPlateau() {
		String ch =".";
		for(int i=0;i<largeur;i++) {
			for(int j=0;j<hauteur;j++) {
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
