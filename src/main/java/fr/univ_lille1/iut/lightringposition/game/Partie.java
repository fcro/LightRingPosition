package fr.univ_lille1.iut.lightringposition.game;

import java.util.HashMap;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

public class Partie {
	private int players;
	private String type;
	private String winner;
	private Map<Integer, Joueur> playersScores;
public Partie(){
	
}
public Partie(Map<Integer, Joueur>playersScores){
	this.setPlayers(players);
	this.setPlayersScores(new HashMap<Integer, Joueur>());
	this.setPlayersScores(playersScores);
}
public void generation(){
	setPlayers(playersScores.size());
		setType("A"+playersScores.size());

	Iterator i = (Iterator)getPlayersScores().keySet().iterator();
	int scoreMax = i.next();
	while(i.hasNext()){
		int tmp=i.next();
		if(tmp>scoreMax)
			scoreMax=tmp;
		
	}
	setWinner(playersScores.get(scoreMax).getNom());
}
public int getPlayers() {
	return players;
}
public void setPlayers(int players) {
	this.players = players;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getWinner() {
	return winner;
}
public void setWinner(String winner) {
	this.winner = winner;
}
public Map<Integer, Joueur> getPlayersScores() {
	return playersScores;
}
public void setPlayersScores(Map<Integer, Joueur> hashMap) {
	this.playersScores = hashMap;
}
	

}
