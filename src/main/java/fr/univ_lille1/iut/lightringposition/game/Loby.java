package fr.univ_lille1.iut.lightringposition.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;

import fr.univ_lille1.iut.lightringposition.util.SecurityFilter;


@Path("loby")

public class Loby {

	private static Map<Integer, List<Joueur>> lobbies = new HashMap<Integer, List<Joueur>>();

	public static Map<Integer, List<Joueur>> getLobbies() {
		return lobbies;
	}

	public static void setLobbies(Map<Integer, List<Joueur>> lobbies) {
		Loby.lobbies = lobbies;
	}

	@GET
	@Path("creer")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Joueur> creerLoby(ContainerRequestContext context) {
		int idx=0;
		SecurityFilter sec = new SecurityFilter("USER");
		sec.filter(context);
		List<Joueur> list = new ArrayList<Joueur>();
		list.add(new Joueur(sec.getLogin()));
		while(lobbies.containsKey(idx)) {
			idx++;
		}
		lobbies.put(idx,list); 
		return lobbies.get(idx);
	}

	@GET
	@Path("liste")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer, List<Joueur>> listeLoby() {
		return lobbies;
	}

	@GET
	@Path("{numLoby}/rejoindre")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Joueur> rejoindreLoby(@PathParam("numLoby") int numLoby, ContainerRequestContext context) {
		SecurityFilter sec = new SecurityFilter("USER");
		sec.filter(context);
		lobbies.get(numLoby).add(new Joueur(sec.getLogin()));
		return lobbies.get(numLoby);
	}
	
	@GET
	@Path("{numLoby}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteLoby(@PathParam("numLoby") int numLoby) {
		lobbies.remove(numLoby);
	}

}

