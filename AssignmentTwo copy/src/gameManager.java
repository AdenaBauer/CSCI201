import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class gameManager{
	public static final long serialVersionUID = 1;
		
	static JButton cardDeckButton;
	static Tiles spaceTiles; 
	static JPanel gamePanel;
	static HomeStartButtons[] homeLabels;
	static HomeStartButtons[] startLabels;
	Play play;
	static Player [] players;

	
	/* 0 = yellow
	 * 1 = green
	 * 2 = red
	 * 3 = blue
	 */
	
	public void numOnStartAndHome(){
		//STARTLABELS
		
		//yellowStartLabel
		HomeStartButtons start = new HomeStartButtons();
		GridBagConstraints gbcStart = new GridBagConstraints();
		gbcStart.gridx = 4;
		gbcStart.gridy = 2;
		gamePanel.add(start,gbcStart);
		startLabels[0] = start;
		//greenStartLabel
		HomeStartButtons start1 = new HomeStartButtons();
		gbcStart.gridx = 13;
		gbcStart.gridy = 4;
		gamePanel.add(start1,gbcStart);
		startLabels[1] = start1;
		//redStartLabel
		HomeStartButtons start2 = new HomeStartButtons();
		gbcStart.gridx = 11;
		gbcStart.gridy = 13;
		gamePanel.add(start2,gbcStart);
		startLabels[2] = start2;
		//blueStartLabel
		HomeStartButtons start3 = new HomeStartButtons();
		gbcStart.gridx = 2;
		gbcStart.gridy = 11;
		gamePanel.add(start3,gbcStart);
		startLabels[3] = start3;
		
		//HOME LABELS
		//yellowHomeLabel
		HomeStartButtons home = new HomeStartButtons();
		GridBagConstraints gbcHome = new GridBagConstraints();
		gbcHome.gridx = 2;
		gbcHome.gridy = 7;
		gamePanel.add(home,gbcHome);
		homeLabels[0] = home;
		//greenHomeLabel
		HomeStartButtons home1 = new HomeStartButtons();
		gbcHome.gridx = 8;
		gbcHome.gridy = 2;
		gamePanel.add(home1,gbcHome);
		homeLabels[1] = home1;
		//redHomeLabel
		HomeStartButtons home2 = new HomeStartButtons();
		gbcHome.gridx = 13;
		gbcHome.gridy = 8;
		gamePanel.add(home2,gbcHome);
		homeLabels[2] = home2;
		//blueHomeLabel
		HomeStartButtons home3 = new HomeStartButtons();
		gbcHome.gridx = 7;
		gbcHome.gridy = 13;
		gamePanel.add(home3,gbcHome);
		homeLabels[3] = home3;	
	
	}
	public void makeHomeBase(int color){
		GridBagConstraints gbcHome = new GridBagConstraints();
		if (color == 0){
			gbcHome.gridx = 2;
			for(int i = 0; i < 6; i++){
				HomeBaseButton hb = new HomeBaseButton(color, i);
				gbcHome.gridy = 1 + i;
				gamePanel.add(hb, gbcHome);
				spaceTiles.addTilesToHome(hb, 0);
			}
		}		
		if (color == 1){
			gbcHome.gridy = 2;
			for(int i = 0; i < 6; i++){
				HomeBaseButton hb = new HomeBaseButton(color, i);
				gbcHome.gridx = 14 - i;
				gamePanel.add(hb, gbcHome);
				spaceTiles.addTilesToHome(hb, 1);

			}
		}
		if (color == 2){
			gbcHome.gridx = 13;
			for(int i = 0; i < 6; i++){
				HomeBaseButton hb = new HomeBaseButton(color, i);
				gbcHome.gridy = 14 - i;
				gamePanel.add(hb, gbcHome);
				spaceTiles.addTilesToHome(hb,2);

			}
		}
		if (color == 3){
			gbcHome.gridy = 13;
			for(int i = 0; i < 6; i++){
				HomeBaseButton hb = new HomeBaseButton(color, i);
				gbcHome.gridx = 1 + i;
				gamePanel.add(hb, gbcHome);
				spaceTiles.addTilesToHome(hb, 3);
			}
		}
	}
	
	//x and y pos in the slide signify where the slide starts
	public void MakeSlide(int slideLen, int slideColor, int xpos, int ypos, GridBagConstraints gbc){
		if(slideColor == 0){
			gbc.gridy = 0;
			for(int i = xpos; i < xpos + slideLen; i++){
				gbc.gridx = i;
				SlideButton sb = new SlideButton(slideColor);
				gamePanel.add(sb, gbc);
				spaceTiles.addTilesToLoop(sb, slideColor);
			}
		}
		if(slideColor == 1){
			gbc.gridx = 15; 
			for(int i = ypos; i < ypos + slideLen; i++){
				gbc.gridy = i;
				SlideButton sb = new SlideButton(slideColor);
				gamePanel.add(sb, gbc);
				spaceTiles.addTilesToLoop(sb, slideColor);
			}
		}
		if(slideColor == 2){
			gbc.gridy = 15; 
			for(int i = (slideLen + xpos); i > xpos; i--){
				gbc.gridx = i; 
				SlideButton sb = new SlideButton(slideColor);
				gamePanel.add(sb, gbc);
				spaceTiles.addTilesToLoop(sb, slideColor);
				
			}
		}
		if(slideColor == 3){
			gbc.gridx = 0;
			for(int i = (slideLen + ypos); i > ypos; i--){
				gbc.gridy = i; 
				SlideButton sb = new SlideButton(slideColor);
				gamePanel.add(sb, gbc);
				spaceTiles.addTilesToLoop(sb, slideColor);
				
			}
		}
	}
	//makes yellow side of the board
	public void createYellow(){
		GridBagConstraints gbcY = new GridBagConstraints();
		
		BlackButton b = new BlackButton();
		gbcY.gridx = 0; 
		gbcY.gridy = 0;
		gamePanel.add(b, gbcY);
		spaceTiles.addTilesToLoop(b, 4);
		
		MakeSlide(4, 0, 1, 0, gbcY);
		
		makeHomeBase(0);
		//make startbutton
		GridBagConstraints gbcStart = new GridBagConstraints();
		gbcStart.gridx = 4;
		gbcStart.gridy = 1;
		StartButton sb = new StartButton(0);
		gamePanel.add(sb, gbcStart);
		spaceTiles.addTilesToStart(sb, 0);
		//end of make startbutton
		
		for(int i = 0; i < 4; i++){
			BlackButton b1 = new BlackButton();
			gbcY.gridx = 5+i;
			gamePanel.add(b1,gbcY);
			spaceTiles.addTilesToLoop(b1, 4);
		}
		MakeSlide(5, 0, 9, 0, gbcY);
		
		BlackButton b2 = new BlackButton();
		gbcY.gridx = 14; 
		gbcY.gridy = 0;
		gamePanel.add(b2, gbcY);
		spaceTiles.addTilesToLoop(b2, 4);
	}
	//makes green side of the board
	public void createGreen(){
		GridBagConstraints gbcG = new GridBagConstraints();

		BlackButton b = new BlackButton();
		gbcG.gridx = 15; 
		gbcG.gridy = 0;
		gamePanel.add(b,gbcG);
		spaceTiles.addTilesToLoop(b, 4);
		
		MakeSlide(4, 1, 15, 1, gbcG);
		
		makeHomeBase(1);
		//make startbutton
		GridBagConstraints gbcStart = new GridBagConstraints();
		gbcStart.gridx = 14;
		gbcStart.gridy = 4;
		StartButton sb = new StartButton(1);
		gamePanel.add(sb, gbcStart);
		spaceTiles.addTilesToStart(sb, 1);
		//end of make startbutton
		
		for(int i = 0; i < 4; i++){
			BlackButton b1 = new BlackButton();
			gbcG.gridy = 5+i;
			gamePanel.add(b1, gbcG);
			spaceTiles.addTilesToLoop(b1, 4);
		}
		MakeSlide(5, 1, 15, 9, gbcG);
		
		BlackButton b2 = new BlackButton();
		gbcG.gridx = 15; 
		gbcG.gridy = 14;
		gamePanel.add(b2, gbcG);
		spaceTiles.addTilesToLoop(b2, 4);
	}
	
	//makes red side of the board
	public void createRed(){
		GridBagConstraints gbcR = new GridBagConstraints();

		BlackButton b = new BlackButton();
		gbcR.gridx = 15; 
		gbcR.gridy = 15;
		gamePanel.add(b,gbcR);
		spaceTiles.addTilesToLoop(b, 4);

		
		MakeSlide(4, 2, 10, 15, gbcR); // this is made going forward even tho the rest is made going backwards... 
		
		makeHomeBase(2);
		//make startbutton
		GridBagConstraints gbcStart = new GridBagConstraints();
		gbcStart.gridx = 11;
		gbcStart.gridy = 14;
		StartButton sb = new StartButton(2);
		gamePanel.add(sb, gbcStart);
		spaceTiles.addTilesToStart(sb, 2);
		//end of make startbutton
		
		for(int i = 0; i < 4; i++){
			BlackButton b1 = new BlackButton();
			gbcR.gridx = 10-i;
			gamePanel.add(b1, gbcR);
			spaceTiles.addTilesToLoop(b1, 4);

		}
		MakeSlide(5, 2, 1, 15, gbcR);
		
		BlackButton b2 = new BlackButton(); 
		gbcR.gridx = 1; 
		gbcR.gridy = 15;
		gamePanel.add(b2, gbcR);
		spaceTiles.addTilesToLoop(b2, 4);

	}
	//makes blue side of the board
	public void createBlue(){
		GridBagConstraints gbcB = new GridBagConstraints();

		BlackButton b = new BlackButton();
		gbcB.gridx = 0;
		gbcB.gridy = 15;
		gamePanel.add(b,gbcB);
		spaceTiles.addTilesToLoop(b, 4);
		
		MakeSlide(4, 3, 0, 10, gbcB); // this is made going forward even tho the rest is made going backwards... 
		
		makeHomeBase(3);
		//make startbutton
		GridBagConstraints gbcStart = new GridBagConstraints();
		gbcStart.gridx = 1;
		gbcStart.gridy = 11;
		StartButton sb = new StartButton(3);
		gamePanel.add(sb, gbcStart);
		spaceTiles.addTilesToStart(sb, 3);
		//end of make startbutton
		
		for(int i = 0; i < 4; i++){
			BlackButton b1 = new BlackButton();
			gbcB.gridy = 10-i;
			gamePanel.add(b1, gbcB);
			spaceTiles.addTilesToLoop(b1, 4);
		}
		MakeSlide(5, 3, 0, 1, gbcB);
		
		BlackButton b2 = new BlackButton();
		gbcB.gridx = 0; 
		gbcB.gridy = 1;
		gamePanel.add(b2, gbcB);
		spaceTiles.addTilesToLoop(b2, 4);
	}
	
	//manages board creation
	public void createBoard(){
		
		spaceTiles = new Tiles();
		
		createYellow(); //creating Yellow side
		createGreen(); //creating Green side
		createRed(); //creating Red side
		createBlue(); //creating Blue side
		
		numOnStartAndHome();
		play = new Play();		

		//card deck button  
		GridBagConstraints gbcCardDeck = new GridBagConstraints();
		gbcCardDeck.gridx = 8;
		gbcCardDeck.gridy = 8;
		cardDeckButton = new JButton(new AbstractAction("Cards"){
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e){
			
				while(true)
					if(play.turn() == -1){
						break;
					}
				}
			
			});		
		cardDeckButton.setFont(new Font("Arial", Font.PLAIN, 8));
		cardDeckButton.setOpaque(true);
		cardDeckButton.setMinimumSize(new Dimension(30, 25));
		cardDeckButton.setPreferredSize(new Dimension(30, 25));
		Border b = new LineBorder(Color.GRAY, 2);
		cardDeckButton.setBorder(b);
		gamePanel.add(cardDeckButton, gbcCardDeck);
		
		
	}
	
	public gameManager(JPanel p){
		startLabels = new HomeStartButtons[4];
		homeLabels = new HomeStartButtons[4];
		gamePanel = p;
		gamePanel.setLayout(new GridBagLayout());
		createBoard();
		
	}

}



