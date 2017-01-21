package tic.tac.toe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = -8332442016593758959L;
	
	private JMenuBar menuBar;
	private JMenu menuGame;
	private JMenu menuNew, menuItemPlayerVsPlayer;
	private JMenuItem menuItemPlayerVsCpu, menuItemLocal, menuItemLan, menuItemExit;
	
	private JPanel panelStatus;
	private PanelBoard panelBoard;
	private ContentPane contentPane;
	private JLabel labelStatus;
	
	public Frame() {
		initialize();
	}
	
	private void initialize() {
		setIconImage(new ImageIcon(Frame.class.getResource("/icons/logo.png")).getImage());
		setTitle(Main.TITLE);
		setResizable(false);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuGame = new JMenu("Game");
		menuBar.add(menuGame);
		
		menuNew = new JMenu("New");
		menuGame.add(menuNew);
		
		menuItemPlayerVsPlayer = new JMenu("Player vs Player");
		menuNew.add(menuItemPlayerVsPlayer);
		
		menuItemLocal = new JMenuItem("Local");
		menuItemLocal.addActionListener(this);
		menuItemPlayerVsPlayer.add(menuItemLocal);
		
		menuItemLan = new JMenuItem("LAN");
		menuItemPlayerVsPlayer.add(menuItemLan);
		
		menuItemPlayerVsCpu = new JMenuItem("Player vs CPU");
		menuItemPlayerVsCpu.addActionListener(this);
		menuNew.add(menuItemPlayerVsCpu);
		
		menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(this);
		menuGame.add(menuItemExit);
		
		contentPane = new ContentPane();
		setContentPane(contentPane);
		
		panelBoard = new PanelBoard();
		
		panelStatus = new JPanel();
		panelStatus.setBorder(new EmptyBorder(3, 3, 3, 3));
		panelStatus.setBackground(Main.COLORS[3]);
		contentPane.add(panelStatus, BorderLayout.SOUTH);
		panelStatus.setLayout(new BorderLayout(0, 0));
		
		labelStatus = new JLabel("Start new game from \"Game\" menu...");
		labelStatus.setFont(new Font("Arial", Font.BOLD, 13));
		panelStatus.add(labelStatus, BorderLayout.NORTH);
	}
	
	private void close() {
		int selectedOption = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", Main.TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (selectedOption == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public void setStatus(String status) {
		labelStatus.setText(status);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		Object object = event.getSource();
		
		boolean cpu = object.equals(menuItemPlayerVsCpu);
		
		if (cpu || object.equals(menuItemLocal)) {
			panelBoard.reset(cpu);
			
			contentPane.add(panelBoard, BorderLayout.CENTER);
			contentPane.revalidate();
			contentPane.repaint();
			
			setStatus(panelBoard.getTurn());
		}
		else if (object.equals(menuItemExit)) {
			close();
		}
	}

	@Override
	public void windowActivated(WindowEvent event) { }

	@Override
	public void windowClosed(WindowEvent event) { }

	@Override
	public void windowClosing(WindowEvent event) {
		if (event.getSource().equals(this)) {
			close();
		}
	}
	
	@Override
	public void windowDeactivated(WindowEvent event) { }

	@Override
	public void windowDeiconified(WindowEvent event) { }

	@Override
	public void windowIconified(WindowEvent event) { }

	@Override
	public void windowOpened(WindowEvent event) { }
	
}