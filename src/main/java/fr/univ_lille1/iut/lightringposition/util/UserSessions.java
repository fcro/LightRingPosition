package fr.univ_lille1.iut.lightringposition.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import fr.univ_lille1.iut.lightringposition.struct.User;

public class UserSessions {
	private static Map<User, HttpSession> users = new HashMap<User, HttpSession>();

	public static void addUser(User user, HttpSession session) {
		users.put(user, session);
	}

	public static void removeUser(User user) {
		users.remove(user);
	}

	public static HttpSession getSessionByUser(User user) {
		return users.get(user);
	}

	public static User getUserBySession(HttpSession session) {
		for (Map.Entry<User, HttpSession> entry : users.entrySet()) {
			if (entry.getValue().equals(session))
				return entry.getKey();
		}

		return null;
	}

	public static boolean isUserConnected(User user) {
		return users.containsKey(user);
	}
}
