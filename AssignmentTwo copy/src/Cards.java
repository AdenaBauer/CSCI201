import java.util.Arrays;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cards {
	static String deck[];
	static int leftInDeck[];
	
	public Cards(){
		deck = new String[13];
		leftInDeck = new int [13];
		Arrays.fill(leftInDeck, 4);	
		initializeDeck();
	}
	
	public void initializeDeck(){
	
		deck[0] = "Sorry! Move any one pawn from Start to a square occupied by any opponent, sending that pawn back to its own Start.";
		deck[1] = "Move a pawn from Start or move a pawn one space forward.";
		deck[2] = "Move a pawn from Start or move a pawn two spaces forward. Drawing a two entitles the player to draw again at the end of their turn";
		deck[3] = "Move a pawn three spaces forward.";
		deck[4] = "Move a pawn four spaces backwards.";
		deck[5] = "Move a pawn five spaces forward.";
		deck[6] = null;
		deck[7] = "Move one pawn seven spaces forward or split the seven spaces between two pawns.";
		deck[8] = "Move a pawn eight spaces forward.";
		deck[9] = null;
		deck[10] = "Move a pawn ten spaces forward or one space backward.";
		deck[11] = "Move a pawn eleven spaces forward, or switch spaces with an opposing pawn.";
		deck[12] = "Move a pawn twelve spaces forward.";	
	}
	
	public void checkIfShuffle(){
		for(int i = 0; i < 13; i++){
			if (leftInDeck[i] != 0){
				return;
			}
		}
		Arrays.fill(leftInDeck, 4);
	}
	
	public int drawCard(){
		
		checkIfShuffle();		
		Random randomGenerator = new Random();
		int card;
		while(true){
			card = randomGenerator.nextInt(12);	
			if(leftInDeck[card] != 0 && (card != 6 || card != 9)){
				break;
			}
		}	
		leftInDeck[card] = leftInDeck[card] - 1;	
		return card;
	}
	
	public void cardFunctionalityManager(int card, Player p){
			
		Random r = new Random();
		//TODO: make this not random...
		
		int r1 = r.nextInt(4);
		genericOutputCard(card, p, r1);	
		p.moveForward(r1, card);

	}
	
	public void genericOutputCard(int card, Player p, int r){
		JLabel cardInfo;
		JFrame jp = new JFrame();
		JPanel j = new JPanel();
		jp.setSize(400, 200);
		jp.setLocation(400, 200);
		j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
		JLabel playerName = new JLabel(p.playerColorString + "'s Turn!" + " You drew a " + Integer.toString(card));
		

		cardInfo = new JLabel(deck[card] + "\n");		

		j.add(playerName);
		j.add(cardInfo);
		jp.add(j);
		jp.setVisible(true);

		for(int i = 0; i < 4; i++){
			p.currentSpaces[i].setEnabled(true);
			Tiles.startButtons[i].setEnabled(true);
		}
	}

}
