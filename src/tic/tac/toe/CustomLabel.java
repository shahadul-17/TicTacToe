package tic.tac.toe;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CustomLabel extends JLabel {
	
	public int index;
	private static final long serialVersionUID = -851117000228552743L;
	
	private static final Font FONT = new Font("Arial", Font.PLAIN, 50);
	
	public CustomLabel(int index) {
		this.index = index;
		
		initialize();
	}
	
	private void initialize() {
		setOpaque(true);
		setBackground(Main.COLORS[0]);
		setForeground(Main.COLORS[3]);
		setFont(FONT);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
}