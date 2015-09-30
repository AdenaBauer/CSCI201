import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Sorry extends JFrame {
	public static final long serialVersionUID = 1;
	
	JPanel p, colors; 
	JLabel sorryLabel; //this becomes the label for numPlayers, and chooseColor
	JButton startButton, confirmButton, colorConfirmButton;
	ButtonGroup numPlayersRadio, colorBG;
	static JRadioButton two, three, four;
	static JButton red, green, blue, yellow;
	gameManager gm;
	static public int humanColor;
	static public int playerNum;
	
	
	
	public void playGame(){
		p.removeAll();
		
		gm = new gameManager(p);
		
		revalidate();
		repaint();
	}
	
	public void pickColor(){
		sorryLabel.setText("Pick a Color");
		p.remove(two);p.remove(three);p.remove(four);
		p.remove(confirmButton);
		
		colors = new JPanel(new GridLayout (2,2));	
		colorBG = new ButtonGroup();
		red = new JButton("Red");
		green = new JButton("Green");
		blue = new JButton("Blue");
		yellow = new JButton("Yellow");
		
		yellow.setOpaque(true);
		yellow.setBorderPainted(false);
		yellow.setBackground(Color.YELLOW);
				
		blue.setOpaque(true);
		blue.setBorderPainted(false);
		blue.setBackground(Color.BLUE);
		
		green.setOpaque(true);
		green.setBorderPainted(false);
		green.setBackground(Color.GREEN);
		
		red.setOpaque(true);
		red.setBorderPainted(false);
		red.setBackground(Color.RED);
		
		colorBG.add(yellow);
		colorBG.add(red);
		colorBG.add(blue);
		colorBG.add(green);
		
		colors.add(yellow);
		colors.add(red);
		colors.add(green);
		colors.add(blue);		
		p.add(colors);
		
		colorConfirmButton = new JButton(new AbstractAction("Confirm"){
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e){
				 
				playGame();
			}
		});
		
		colorConfirmButton.setEnabled(false);
				
		red.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				colorConfirmButton.setEnabled(true);
				red.setForeground(Color.GRAY);
				
				blue.setForeground(Color.BLACK);
				yellow.setForeground(Color.BLACK);
				green.setForeground(Color.BLACK);
				
				humanColor = 2;
			}
		});
		green.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				colorConfirmButton.setEnabled(true);
				green.setForeground(Color.GRAY);
				
				red.setForeground(Color.BLACK);
				yellow.setForeground(Color.BLACK);
				blue.setForeground(Color.BLACK);
				
				humanColor = 1;

			}
		});
		blue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				colorConfirmButton.setEnabled(true);
				blue.setForeground(Color.GRAY);
				
				red.setForeground(Color.BLACK);
				yellow.setForeground(Color.BLACK);
				green.setForeground(Color.BLACK);
				
				humanColor = 3;
				
			}
		});
		yellow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				colorConfirmButton.setEnabled(true);
				yellow.setForeground(Color.GRAY);
				
				red.setForeground(Color.BLACK);
				blue.setForeground(Color.BLACK);
				green.setForeground(Color.BLACK);
				
				humanColor = 0;

			}
		});
		
		p.add(colorConfirmButton);
	
	}
	
	public void pickPlayerNum(){
		sorryLabel.setText("Select the Number of Players");
		p.remove(startButton);
	    
		numPlayersRadio = new ButtonGroup();

		two = new JRadioButton("Two");
		three = new JRadioButton("Three");
		four = new JRadioButton("Four");
		
		numPlayersRadio.add(two);
		numPlayersRadio.add(three);
		numPlayersRadio.add(four);
		
		confirmButton = new JButton(new AbstractAction("Confirm"){
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e){
				pickColor();
			}
		});
		
		confirmButton.setEnabled(false);
				
		two.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(two.isSelected()){
					confirmButton.setEnabled(true);
					playerNum = 2;
				}
			}
		});
		three.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(three.isSelected()){
					confirmButton.setEnabled(true);
					playerNum = 3;
				}
			}
		});
		four.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(four.isSelected()){
					confirmButton.setEnabled(true);
					playerNum = 4;
				}
			}
		});
		
		p.add(two);
		p.add(three);
		p.add(four);
		p.add(confirmButton);
		
		

	}
	
	public void initializeComponents(){
		p = new JPanel();
		sorryLabel = new JLabel("Sorry!");
		sorryLabel.setAlignmentX(CENTER_ALIGNMENT);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		/*add functionality to start button */
		startButton = new JButton(new AbstractAction("Start"){
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e){
				pickPlayerNum();
			}
		});
		startButton.setAlignmentX(CENTER_ALIGNMENT);

		p.add(sorryLabel);
		p.add(startButton);
	
	}
	
	public Sorry() {
		super("Sorry!");
		setSize(640, 480);
		setLocation(100, 200);
		initializeComponents();
		
		add(p);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}

	public static void main(String [] args) {
		new Sorry();
	}
}

