package fr.univ_lille1.iut.lightringposition.game;

import java.util.ArrayList;
import java.util.List;

public class ObjetTransfert {
	private List<Integer> score;
	
	public ObjetTransfert() {
		score = new ArrayList<Integer>();
	}

	public List<Integer> getScore() {
		return score;
	}

	public void setScore(List<Integer> score) {
		this.score = score;
	}
	
}
