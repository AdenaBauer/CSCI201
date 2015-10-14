package game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TopScore implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public Map<String, Integer> playerScores;
	
	public TopScore(){
			playerScores = new HashMap<String, Integer>();
		
	}
		
	public void makeScore(){
		int score = 0;
		for(int i = 0; i < GameManager.numRealPlayers; i++){
			int pawnsHome = GameManager.numPawnsHome[i];
			if(i != 0){
				score = score + (4-pawnsHome)*3;
			}
			if(i == 0){
			score = score + pawnsHome*5;
			}
		}
		GameManager.winnerScore = score;
		
	}
	public void addScore(String name){
		playerScores.put(name, GameManager.winnerScore);
	}
	
	public boolean scoresEmpty(){
		return playerScores.isEmpty();
	}

	
}
