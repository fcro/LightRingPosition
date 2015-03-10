package fr.univ_lille1.iut.lightringposition.doc;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.univ_lille1.iut.lightringposition.struct.User;

@Path("account")
public class Account {

    @GET
    @Path("{login}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserInfo(@PathParam("login") String login) {
    	User user = new User();
    	user.setLogin(login);
    	user.setPassword("azerty");
    	user.setEmail("address@mail.tld");
    	user.setNickname("me");
    	user.setAvatar(new File("img/avatar.png"));

        return user;
    }
}
