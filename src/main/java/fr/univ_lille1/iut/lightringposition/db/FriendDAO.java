package fr.univ_lille1.iut.lightringposition.db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface FriendDAO {
	@SqlQuery("SELECT friend_login FROM FRIEND " +
			"WHERE login = :login")
	List<String> findFriendsByLogin(@Bind("login") String login);

	@SqlUpdate("INSERT INTO FRIEND(login, friend_login)" +
			"VALUES(:login, :friend)")
	void insertFriend(@Bind("login") String login, @Bind("friend") String friend);

	@SqlUpdate("DELETE FROM FRIEND " +
			"WHERE login = :login AND friend_login = :friend")
	void deleteFriend(@Bind("login") String login, @Bind("friend") String friend);
}
