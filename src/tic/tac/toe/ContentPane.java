package tic.tac.toe;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ContentPane extends JPanel {
	
	private static final long serialVersionUID = -4823100385939665780L;
	
	private Image image = null;
	
	public ContentPane() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		
		image = new ImageIcon(ContentPane.class.getResource("/icons/background.jpg")).getImage();
	}
	
	@Override
	protected void paintComponent(Graphics graphics) {
		graphics.drawImage(image, 0, 0, null);
		super.paintComponent(graphics);
	}
	
}