package fr.univ_lille1.iut.lightringposition.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjetTransfert {
	private List<Integer> score;
	private Map<Integer, List<Joueur>> lobbies = new HashMap<Integer, List<Joueur>>();
	
	public ObjetTransfert() {
		score = new ArrayList<Integer>();
	}

	public List<Integer> getScore() {
		return score;
	}

	public void setScore(List<Integer> score) {
		this.score = score;
	}

	public Map<Integer, List<Joueur>> getLobbies() {
		return lobbies;
	}

	public void setLobbies(Map<Integer, List<Joueur>> lobbies) {
		this.lobbies = lobbies;
	}

	
}
