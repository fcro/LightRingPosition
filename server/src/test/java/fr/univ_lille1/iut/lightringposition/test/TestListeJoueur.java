package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.iut.lightringposition.game.ListeJoueur;



public class TestListeJoueur {
	ListeJoueur listeJoueur = new ListeJoueur(3);

	@Test
	public void testNombreJoueur() {
		assertTrue(listeJoueur.getListeJoueur().size()>0);
		assertEquals(listeJoueur.getListeJoueur().size(),3);
		assertTrue(listeJoueur.getListeJoueur().get(2).equals("toto"));
	}
	
	
}
