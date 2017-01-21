package tic.tac.toe;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelBoard extends JPanel implements MouseListener {
	
	private boolean winnerFound = false, cpu = false;
	private int index, randomIndex, clickCount = 0, moveCount = 0, player = 2;
	private static int[] board = new int[9], randomIndices = new int[board.length];
	private static final int[][] winT = {
		{0, 1, 2}, {3, 4, 5}, {6, 7, 8},	// horizontal...
		{0, 3, 6}, {1, 4, 7}, {2, 5, 8},	// vertical...
		{0, 4, 8}, {2, 4, 6}				// diagonal...
	};
	private static final long serialVersionUID = -5278366726153921284L;
	
	private static final String[] PLAYERS = { "X", "O" };
	private static final Random _RANDOM = new Random();
	
	private CustomLabel[] customLabels = new CustomLabel[board.length];
	
	public PanelBoard() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridLayout(3, 3, 3, 3));
		setBackground(Main.DODGER_BLUE);
		
		for (int i = 0; i < customLabels.length; i++) {
			customLabels[i] = new CustomLabel(i);
			customLabels[i].addMouseListener(this);
			add(customLabels[i]);
		}
	}
	
	private static boolean contains(int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		
		return false;
	}
	
	private void generateRandomIndices() {
		for (int i = 0; i < randomIndices.length; ) {
			randomIndex = _RANDOM.nextInt(board.length);
			
			if (!contains(randomIndices, randomIndex)) {
				randomIndices[i] = randomIndex;
				
				i++;
			}
		}
	}
	
	private boolean isWinner(int player) {
		int count = 0;
		
		for (int i = 0; i < winT.length; i++) {
			for (int j = 0; j < winT[i].length; j++) {
				if (board[winT[i][j]] == player) {
					count++;
				}
				else {
					break;
				}
			}
			
			if (count == 3) {
				return true;
			}
			else {
				count = 0;
			}
		}
		
		return false;
	}
	
	private void move() {
		player = getAlteredPlayer(player);
		customLabels[index].setText(PLAYERS[player - 1]);
		board[index] = player;
		
		Main.frame.setStatus(getTurn(player));
		
		if (clickCount == 4 && isWinner(player)) {		// will avoid unnecessary winner check...
			winnerFound = true;
			
			Main.frame.setStatus("Game over...!!! Winner: " + PLAYERS[player - 1]);
		}
		
		if (clickCount < 4) {
			clickCount++;
		}
		
		moveCount++;
		
		if (!winnerFound && moveCount == 9) {
			winnerFound = true;
			
			Main.frame.setStatus("Game over...!!! Match drawn.");
		}
	}
	
	private int getAlteredPlayer(int player) {
		return (player % 2) + 1;
	}
	
	private String getTurn(int player) {
		return "Turn: " + PLAYERS[getAlteredPlayer(player) - 1];
	}
	
	public String getTurn() {
		return getTurn(player);
	}
	
	public void reset(boolean cpu) {
		winnerFound = false;
		this.cpu = cpu;
		index = clickCount = moveCount = 0;
		player = 2;
		
		for (int i = 0; i < customLabels.length; i++) {
			board[i] = 0;
			randomIndices[i] = -1;
			customLabels[i].setText("");
		}
		
		if (this.cpu) {
			generateRandomIndices();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) { }

	@Override
	public void mouseEntered(MouseEvent event) { }

	@Override
	public void mouseExited(MouseEvent event) { }

	@Override
	public void mousePressed(MouseEvent event) { }

	@Override
	public void mouseReleased(MouseEvent event) {
		index = ((CustomLabel) event.getSource()).index;
		
		if (!winnerFound && board[index] == 0) {
			move();
			
			if (!winnerFound && cpu) {		// this portion is for CPU...
				Main.frame.setStatus("CPU is thinking...");
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						int i;
						
						for (i = 0; i < randomIndices.length; i++) {
							if (board[randomIndices[i]] == 0) {
								index = randomIndices[i];
								
								break;
							}
						}
						
						if (i < randomIndices.length) {
							try {
								Thread.sleep(1500);
							}
							catch (Exception exc) {
								exc.printStackTrace();
							}
							
							move();
						}
					}
				});
			}
		}
	}
	
}