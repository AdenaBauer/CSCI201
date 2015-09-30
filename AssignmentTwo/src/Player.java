import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Player {
	int playerColor;
	JButton[] currentSpaces;
	int[]currentNumSpaces;
	boolean playerWon;
	ImageIcon playerPawnImage;
	int numOnStart;
	int numAtHome;
	int loopStartingPlace;
	int [] noSlideSpots;
	int turnToHomePlace;
	int[] slideSpots = {1, 9, 16, 24, 31, 39, 46, 54}; //even indices are 4 space slides, odd are 5 space slides
	boolean []pawnIsInHomeBase;
	boolean []pawnIsHome;
	boolean [] pawnIsOnStart;
	String playerColorString;
	
	
	public Player(int color){
		currentSpaces = new JButton [4];
		currentNumSpaces = new int[4];
		pawnIsInHomeBase = new boolean[4];
		pawnIsOnStart = new boolean[4];
		pawnIsHome = new boolean[4];
		playerWon = false;
		playerColor = color;
		assignThings();
		Arrays.fill(currentSpaces, Tiles.boardLoop[loopStartingPlace]);
		Arrays.fill(currentNumSpaces, loopStartingPlace);
		Arrays.fill(pawnIsInHomeBase, false);
		Arrays.fill(pawnIsHome, false);
		Arrays.fill(pawnIsOnStart, true);
	}
	
	public int checkSpaceF(int currentSpace, int spacesForward){
		int proposedSpace = (currentSpace + spacesForward)%60;
		for(int i = 0; i < slideSpots.length; i++){
			if(proposedSpace == slideSpots[i]){
				if(proposedSpace == noSlideSpots[0] || proposedSpace == noSlideSpots[1]){//it is a slide spot but its a home slide spot
					break;	
				}
				else{
					if (i%2 == 0){// it is a slide spot, and the slide is length 4
						proposedSpace = proposedSpace + 4;	
					}
					else{ //it is a slide spot, and the slide is length 5
						proposedSpace = proposedSpace + 5;
					}
					break;
				}
			}
		}
		for(int i = 1; i < spacesForward; i++){
			if(((currentSpace + i)%60) == turnToHomePlace){
				int intoHome = spacesForward - i;
				proposedSpace = intoHome * -1;//negative signals that the pawn has turned the corner into the home
			}
		}
		return proposedSpace;
	}
	
	/*
	 * returns:
	 *  0 = cant move forward with that piece, choose another
	 *  1 = moved forward, everything is cool 
	 *  2 = player just won the game
	 */
	public int moveForward(int pawn, int numSpacesForward){
		if(pawnIsHome[pawn]){//If pawn is already home you cant move forward any more
			return 0;
		}
		
		if(!pawnIsInHomeBase[pawn]){
			System.out.print("hi");
			currentSpaces[pawn].setIcon(null);
			int newSpace = (checkSpaceF(currentNumSpaces[pawn], numSpacesForward));
			
			if(newSpace <= -1){ //case where new space turns corner into the safety zone
				currentNumSpaces[pawn] = newSpace * (-1); 
				pawnIsInHomeBase[pawn] = true;
				currentSpaces[pawn] = Tiles.homeBaseButtons[playerColor][newSpace*(-1)];
			}
			else{//case where all is gucci, this takes into account the slides:) 
				currentSpaces[pawn] = Tiles.boardLoop[newSpace];
				currentNumSpaces[pawn] = newSpace;	
				currentSpaces[pawn] = Tiles.boardLoop[currentNumSpaces[pawn]];
				currentSpaces[pawn].setIcon(playerPawnImage);
			}	
		}
		else{
			if (currentNumSpaces[pawn] + numSpacesForward <= 6){//normal case of pawn moving forward into the home more
				currentSpaces[pawn].setIcon(null);
				int newSpace = currentNumSpaces[pawn] + numSpacesForward;
				currentNumSpaces[pawn] = newSpace;
				currentSpaces[pawn] = Tiles.homeBaseButtons[playerColor][currentNumSpaces[pawn]];
				currentSpaces[pawn].setIcon(playerPawnImage);
			}
			else{
				pawnIsHome[pawn] = true;
				//a pawn just got home! check to see if this player won
				numAtHome = numAtHome + 1;
				for(int i = 0; i < 4; i++){
					if(pawnIsHome[i] == false){
						return 1;
					}
				}
				playerWon = true;
				return 2;
			}
		}
		return 1;
	}
	
	public void moveBackwards(int pawn, int numSpacesBackward){
		//TODO: need to implement backwardsness
	}
	
	public void assignThings(){
		if (playerColor == 0){
			playerPawnImage = new ImageIcon("Resources/yellow.png");
			loopStartingPlace = 4;
			turnToHomePlace = 2;
			noSlideSpots = new int[]{1, 9};
			playerColorString = "Yellow";
		}
		if(playerColor == 1){
			playerPawnImage = new ImageIcon("Resources/green.png");
			loopStartingPlace = 19;
			turnToHomePlace = 17;
			noSlideSpots = new int[]{16, 24};
			playerColorString = "Green";

		}
		if(playerColor == 2){
			playerPawnImage = new ImageIcon("Resources/red.png");
			loopStartingPlace = 34;
			turnToHomePlace = 32;
			noSlideSpots = new int[]{31, 39};
			playerColorString = "Red";

		}
		if(playerColor == 3){
			playerPawnImage = new ImageIcon("Resources/blue.png");
			loopStartingPlace = 49;
			turnToHomePlace = 47;
			noSlideSpots = new int[]{46, 54};
			playerColorString = "Blue";

		}
	}
}
