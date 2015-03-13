package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.univ_lille1.iut.lightringposition.game.Joueur;



public class TestListeJoueur {
	List<Joueur> liste = new ArrayList<Joueur>();
	
	@Test
	public void testNombreJoueur() {
		for(int i=0;i<liste.size();i++) {
			liste.add(new Joueur("toto"));
		}
		assertTrue(liste.size()>0);
		assertEquals(liste.size(),3);
	}
	
	
}
