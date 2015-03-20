package fr.univ_lille1.iut.lightringposition.test.util;

public final class TestUtil {
	private static boolean dbCreated = false;

	public static boolean isDbCreated() {
		return dbCreated;
	}

	public static void setDbCreated(boolean dbCreated) {
		TestUtil.dbCreated = dbCreated;
	}
}
