package fr.univ_lille1.iut.lightringposition.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PwdEncrypt {
	public static String encrypt(String pwd) {
		byte[] encrypted = ("!SALT!g('645cà'#" + pwd).getBytes();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(encrypted);
			encrypted = digest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return new String(encrypted);
	}
}
