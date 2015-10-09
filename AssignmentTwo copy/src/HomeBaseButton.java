import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class HomeBaseButton extends JButton {
	public static final long serialVersionUID = 1;
	
	/* 0 = yellow
	 * 1 = green
	 * 2 = red
	 * 3 = blue
	 */
	
	
	public HomeBaseButton(int color, int homeBaseNum){
		
		this.setFont(new Font("Arial", Font.PLAIN, 8));
		
		if(homeBaseNum == 5){
			this.setText("Home");
		}
		this.setOpaque(true);
	   this.setMinimumSize(new Dimension(30, 25));
	    this.setPreferredSize(new Dimension(30, 25));

	    switch(color){
	    case 0:
	    	Border b = new LineBorder(Color.YELLOW, 2);
			this.setBorder(b);
	    	break;
	    case 1:
	    	Border b1 = new LineBorder(Color.GREEN, 2);
			this.setBorder(b1);
	    	break;
	    case 2:
	    	Border b2 = new LineBorder(Color.RED, 2);
			this.setBorder(b2);
	    	break;
	    case 3:
	    	Border b3 = new LineBorder(Color.BLUE, 2);
			this.setBorder(b3);
	    }
	}
}
