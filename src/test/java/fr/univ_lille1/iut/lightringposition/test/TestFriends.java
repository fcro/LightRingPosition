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
import fr.univ_lille1.iut.lightringposition.util.PwdEncrypt;

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
		DBUtil.getUserDAO().insertUser("robert", PwdEncrypt.encrypt("robert"),
				"robert@caramail.fr", "Roro", "USER", null);
		DBUtil.getUserDAO().insertUser("roger", PwdEncrypt.encrypt("roger"),
				"roger@wanarty.fr", "Gégé", "USER", null);
	}

	@AfterClass
	public static void deleteTestDB() {
		DBUtil.deleteDB();
	}

	@Test
	public void test_A_addFriend() {
		Entity<String> roger = Entity.entity("roger", MediaType.TEXT_PLAIN);
		Entity<String> roro = Entity.entity("roro", MediaType.TEXT_PLAIN);

		assertEquals(Response.Status.CREATED.getStatusCode(), target("/friends").request().header("AUTHORIZATION",
				"Basic " + Base64.encodeAsString("robert:robert")).post(roger).getStatus());

		assertEquals(Response.Status.CONFLICT.getStatusCode(), target("/friends").request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).post(roger).getStatus());

		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), target("/friends").request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).post(roro).getStatus());

		assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), target("/friends").request().header("Authorization",
				"Basic " + Base64.encodeAsString("marcel:marcel")).post(roger).getStatus());
	}

	@Test
	public void test_B_getFriends() {
		Entity<String> robert = Entity.entity("robert", MediaType.TEXT_PLAIN);
		target("/friends").request().header("AUTHORIZATION",
				"Basic " + Base64.encodeAsString("robert:robert")).post(robert);

		List<String> list = new ArrayList<String>();
		list.add("roger");
		list.add("robert");
 
		assertEquals(list, target("/friends/robert").request(MediaType.APPLICATION_JSON).
				get(List.class));
	}

	@Test
	public void test_C_DelFriend() {
		assertEquals(Response.Status.OK.getStatusCode(), target("/friends/roger").request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).delete().getStatus());

		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), target("/friends/roger").request().header("Authorization",
				"Basic " + Base64.encodeAsString("robert:robert")).delete().getStatus());
	}
}
