import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class BlackButton extends JButton {
	public static final long serialVersionUID = 1;

	public BlackButton(){
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		 this.setMinimumSize(new Dimension(40, 30));
		    this.setMaximumSize(new Dimension(40, 30));	}

}
