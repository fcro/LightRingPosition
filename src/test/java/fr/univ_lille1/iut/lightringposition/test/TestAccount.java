package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import fr.univ_lille1.iut.lightringposition.db.DBUtil;
import fr.univ_lille1.iut.lightringposition.doc.Account;
import fr.univ_lille1.iut.lightringposition.struct.LoginPwd;
import fr.univ_lille1.iut.lightringposition.struct.User;
import fr.univ_lille1.iut.lightringposition.util.InvalidUserException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAccount extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(Account.class);
	}

	@BeforeClass
	public static void useTestDB() {
		DBUtil.useTestDB();
		DBUtil.getDAO().initDB();
	}

	@AfterClass
	public static void deleteTestDB() {
		DBUtil.deleteTestDB();
	}

	/**
	 * Vérifie qu'on peut créer un user valide
	 */
	@Test
	public void test_A_createAccount() throws InvalidUserException {
		User toCreate = new User("test", "pass", "addr@mail.tld", "pseudo");
		Entity<User> userEntity = Entity.entity(toCreate, MediaType.APPLICATION_JSON);
		int code = target("/account").request().post(userEntity).getStatus();
		assertEquals(201, code);

		code = target("/account").request().post(userEntity).getStatus();
		assertEquals(409, code);

		User invalid = new User();
		invalid.setEmail("emailok");
		invalid.setNickname("nickok");
		invalid.setPassword("passok");
		userEntity = Entity.entity(invalid, MediaType.APPLICATION_JSON);
		code = target("/account").request().post(userEntity).getStatus();
		assertEquals(400, code);
	}

	/**
	 * Vérifie qu'on récupère un compte utilisateur au format JSON
	 * ou null s'il n'existe pas
	 */
	@Test
	public void test_B_getAccount() {
		User user =	target("/account/test").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<User>(){});
		assertEquals("addr@mail.tld", user.getEmail());

		user =	target("/account/gabuzomeu").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<User>(){});
		assertNull(user);
	}
}
