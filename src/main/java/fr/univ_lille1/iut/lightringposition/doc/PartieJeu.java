package fr.univ_lille1.iut.lightringposition.doc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.univ_lille1.iut.lightringposition.game.Joueur;
import fr.univ_lille1.iut.lightringposition.game.Partie;


@Path("partieJeu")
public class PartieJeu {
	Map<Integer, Joueur> m1 = new HashMap<Integer, Joueur>();
	private static Partie p1;
	private static Partie p2;
	private static Partie p3;
	private static Partie p5;
	private static Partie p6;
	private static Partie p7;
	private static Partie p8;
	private static Partie p9;
	private static Partie p10;
	private static List<Partie> l=new ArrayList<Partie>();
	@GET
	@Path("partie")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Partie> getPlateau(){

		for(int i=0;i<4;++i)
			m1.put(i+10,new Joueur("toto"+i));
		p1 = new Partie(m1);
		p1.generation();
		l.add(p1);
		p2 = new Partie(m1);
		p2.generation();
		l.add(p2);
		p3 = new Partie(m1);
		p3.generation();
		l.add(p3);
		p5 = new Partie(m1);
		p5.generation();
		l.add(p5);
		p6 = new Partie(m1);
		p6.generation();
		l.add(p6);
		p7 = new Partie(m1);
		p7.generation();
		l.add(p7);
		p8 = new Partie(m1);
		p8.generation();
		l.add(p8);
		p9 = new Partie(m1);
		p9.generation();
		l.add(p9);
		p10 = new Partie(m1);
		p10.generation();
		l.add(p10);
		return l;

	}

}
