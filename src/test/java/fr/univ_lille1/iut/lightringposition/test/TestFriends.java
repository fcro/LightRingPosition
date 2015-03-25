package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.doc.Friends;
import fr.univ_lille1.iut.lightringposition.struct.User;
import fr.univ_lille1.iut.lightringposition.util.InvalidUserException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFriends extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(Friends.class);
	}

	@BeforeClass
	public static void useTestDB() {
		DBUtil.useTestDB();
		DBUtil.deleteDB();
		DBUtil.initDB("admin");
		DBUtil.getUserDAO().insertUser("robert", "robert", "robert@caramail.fr", "Roro", "USER", null);
		DBUtil.getUserDAO().insertUser("roger", "roger", "roger@wanarty.fr", "Gégé", "USER", null);
	}

	@AfterClass
	public static void deleteTestDB() {
		DBUtil.deleteDB();
	}

	@Test
	public void test_A_addFriend() {
		Entity<String> roger = Entity.entity("roger", MediaType.TEXT_PLAIN);
		Entity<String> roro = Entity.entity("roro", MediaType.TEXT_PLAIN);

		assertEquals(Response.Status.OK, target().request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).put(roger).getStatus());

		assertEquals(Response.Status.CONFLICT, target().request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).put(roger).getStatus());

		assertEquals(Response.Status.NOT_FOUND, target().request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).put(roro).getStatus());

		assertEquals(Response.Status.UNAUTHORIZED, target().request().header("Authorization",
				"Basic " + Base64.encodeAsString("marcel:marcel")).put(roger).getStatus());
	}

	@Test
	public void test_B_getFriends() {
		List<User> list = new ArrayList<User>();
		try {
			list.add(new User("roger", "roger", "roger@wanarty.fr", "Gégé", "USER"));
		} catch (InvalidUserException e) {
			e.printStackTrace();
		}
 
		assertEquals(list, target("/robert").request(MediaType.APPLICATION_JSON).
				get(ArrayList.class));
	}

	@Test
	public void test_C_DelFriend() {
		assertEquals(Response.Status.OK, target("roger").request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).delete().getStatus());

		assertEquals(Response.Status.NOT_FOUND, target("roger").request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).delete().getStatus());
	}
}
