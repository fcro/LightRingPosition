package fr.univ_lille1.iut.lightringposition.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Role {
	private static final ArrayList<String> roles = new ArrayList<String>
			(Arrays.asList("ANON", "USER", "ADMIN"));

	public static boolean containsRole(String role) {
		return roles.contains(role);
	}

	public static int valueOf(String role) {
		if (containsRole(role)) {
			return roles.indexOf(role);
		}

		return -1;
	}
}