package fr.univ_lille1.iut.lightringposition.test.util;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;

public final class TestUtil {
	private static final int NBRTESTCLASSES = 2;
	private static boolean dbCreated = false;
	private static int finishedTestClasses = 0;

	public static boolean isDbCreated() {
		return dbCreated;
	}

	public static void setDbCreated(boolean dbCreated) {
		TestUtil.dbCreated = dbCreated;
	}

	public static void testFinished() {
		if (++finishedTestClasses == NBRTESTCLASSES) {
			DBUtil.deleteDB();
		}
	}
}
