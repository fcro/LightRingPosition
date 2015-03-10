package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.*;

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

}
