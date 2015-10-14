package sorryclient;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.table.JTableHeader;

import game.HelpMenu;
import game.scoreTable;

/*
 * The main window for Sorry!
 * */


public class SorryClientWindow extends JFrame{
	private static final long serialVersionUID = 5147395078473323173L;
	
	//Dimensions for the game
	private final static Dimension minSize = new Dimension(640,480);
	private final static Dimension maxSize = new Dimension(960,640);
	JMenuBar menuBar;
	
	{ //Construct
		setLayout(new BorderLayout());
		
		menuBar = new JMenuBar();
		
		JMenuItem helpButton = new JMenuItem("Help");
		helpButton.setMnemonic(KeyEvent.VK_H);
		helpButton.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		
		JMenuItem topScoreButton = new JMenuItem("Top Scores");
		topScoreButton.setMnemonic(KeyEvent.VK_S);
		topScoreButton.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		scoreTable ScoreTable = new scoreTable();
		HelpMenu helpmenu = new HelpMenu();
		try {
			helpmenu.initUI();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		helpButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				helpmenu.showMe();
			}
			
		});
		topScoreButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button clicked!");
				ScoreTable.setVisible(true);
			}
			
		});
			
		menuBar.add(helpButton);
		menuBar.add(topScoreButton);	
		
		setTitle("Sorry!");
		setSize(minSize);
		setMinimumSize(minSize);
		setMaximumSize(maxSize);
		add(new ClientPanel(), BorderLayout.CENTER);
		add(menuBar, BorderLayout.NORTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		//Create a new SorryClient Window
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	new SorryClientWindow();
		    }
		});
	}
}
