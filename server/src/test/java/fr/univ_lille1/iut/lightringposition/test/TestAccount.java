package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.*;

import java.io.File;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import fr.univ_lille1.iut.lightringposition.doc.Account;
import fr.univ_lille1.iut.lightringposition.struct.User;

public class TestAccount extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(Account.class);
	}

	/**
	 * Vérifie qu'on récupère un compte utilisateur au format JSON
	 */
	@Test
	public void testGetAccount() {
		User user =	target("/account/test").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<User>(){});
		assertTrue(user instanceof User);
	}

	/**
	 * Vérifie qu'on peut créer un user valide
	 */
	@Test
	public void testCreateAccount() {
		User toCreate = new User("test", "pass", "addr@mail.tdl", "xX_d4rKpL4y3r_XxX",
				new File("img/img.png"));
		Entity<User> userEntity = Entity.entity(toCreate, MediaType.APPLICATION_JSON);
		int code = target("/account").request().post(userEntity).getStatus();
		assertEquals(201, code);

		User invalid = new User();
		invalid.setEmail("emailok");
		invalid.setNickname("nickok");
		invalid.setPassword("passok");
		userEntity = Entity.entity(invalid, MediaType.APPLICATION_JSON);
		code = target("/account").request().post(userEntity).getStatus();
		assertEquals(400, code);
	}
}
