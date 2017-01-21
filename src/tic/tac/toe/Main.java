package tic.tac.toe;

import java.awt.Color;

public class Main {
	
	public static final String TITLE = "TicTacToe";
	
	public static final Color DODGER_BLUE = new Color(30, 144, 255);
	public static final Color[] COLORS = {
		new Color(100, 180, 255),		// index = 0
		new Color(30, 144, 255),		// index = 1
		new Color(85, 15, 100),			// index = 2
		Color.WHITE						// index = 3
	};
	public static Frame frame;
	
	public static void main(String[] args) {
		try {
			frame = new Frame();
			frame.setVisible(true);
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
}