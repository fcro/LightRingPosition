package fr.univ_lille1.iut.lightringposition.test;

import org.junit.Test;

import fr.univ_lille1.iut.lightringposition.game.Plateau;
import static org.junit.Assert.*;

public class TestPlateau {
	@Test
	public void test(){
		for(int i=0;i<1000000;i++){
			Plateau p = new Plateau(20);
			p.generation();
			assertTrue(p.getVal() <= 80);
			assertTrue(p.getVal() >=40);
		}
	}
}
