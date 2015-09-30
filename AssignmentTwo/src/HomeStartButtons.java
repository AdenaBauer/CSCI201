import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;


public class HomeStartButtons extends JButton{
	public static final long serialVersionUID = 1;

	public HomeStartButtons(){	
		this.setForeground(Color.BLACK);
		this.setOpaque(true);
		this.setMinimumSize(new Dimension(30, 25));
		this.setPreferredSize(new Dimension(30, 25));
		this.setEnabled(false);
	}
}
