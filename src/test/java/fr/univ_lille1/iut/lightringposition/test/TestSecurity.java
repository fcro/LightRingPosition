package fr.univ_lille1.iut.lightringposition.test;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.internal.util.Base64;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import fr.univ_lille1.iut.lightringposition.doc.AdminTest;

public class TestSecurity extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(AdminTest.class);
	}

	@Test
	public void testCheezbrgr() {
		int code = target("/admin/canihazcheezbrgr").request().header("Authorization",
				"Basic " + Base64.encodeAsString("admin:admin")).get().getStatus();
		assertEquals(200, code);

		code = target("/admin/canihazcheezbrgr").request().header("Authorization",
				Base64.encodeAsString("admin:admin")).get().getStatus();
		assertEquals(401, code);
	}
}
