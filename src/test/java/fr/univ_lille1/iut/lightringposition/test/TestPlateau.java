package fr.univ_lille1.iut.lightringposition.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.univ_lille1.iut.lightringposition.game.Joueur;
import fr.univ_lille1.iut.lightringposition.game.Plateau;
import static org.junit.Assert.*;

public class TestPlateau {
	@Test
	public void test(){
		List<Joueur> liste = new ArrayList<Joueur>();
		for(int i=0;i<10;i++) {
			liste.add(new Joueur("toto"));
		}
		for(int i=0;i<10000;i++){
			Plateau p = new Plateau(20,liste);
			p.generation();
			assertTrue(p.getVal() <= 80);
			assertTrue(p.getVal() >=40);
		}
	}
}
