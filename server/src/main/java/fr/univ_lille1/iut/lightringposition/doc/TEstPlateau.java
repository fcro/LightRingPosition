package fr.univ_lille1.iut.lightringposition.doc;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import static org.junit.Assert.*;

public class TEstPlateau {
	@Test
	public void test(){
		for(int i=0;i<1000000;i++){
			Plateau p = new Plateau(20,'X');
			p.generation();
			assertTrue(p.val <= 80);
			assertTrue(p.val >=40);
		}
	}
}
