package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univ_lille1.iut.lightringposition.util.PwdEncrypt;

public class TestPwdEncrypt {

	/**
	 * Vérifie que l'on obtient le même digest pour
	 * deux tentatives de chiffrements d'un même mot de passe
	 */
	@Test
	public void testEncrypt() {
		String plain = "têst de môt de pâsse";
		String plain2 = "test de mot de passe";

		String encrypted1 = PwdEncrypt.encrypt(plain);
		String encrypted2 = PwdEncrypt.encrypt(plain);
		String encrypted3 = PwdEncrypt.encrypt(plain2);

		assertEquals(encrypted1, encrypted2);
		assertNotEquals(encrypted1, encrypted3);
	}

}
