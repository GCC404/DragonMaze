package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import maze.cli.CLI;
import maze.logic.Logic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private Logic game;
	private ShowMaze panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		setTitle("Dungeons & Dragons");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("New Game");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String input=String.valueOf(e.getKeyChar()).toUpperCase();
				/*
				System.out.println(input+"W");
				System.out.println(input!="W");
				System.out.println(input!="W" && input!="A" && input!="S" && input!="D");
				
				if(input!="W" && input!="A" && input!="S" && input!="D")
					return;
				*/
				CLI.setScanner(input);
				game.makePlay();
				panel.updateStatus(game.getMaze());
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure?")==0) {
					CLI.setScanner("P P 1");
					game=new Logic();
					panel.updateStatus(game.getMaze());
				}
			}
		});
		btnNewButton.setBounds(10, 521, 194, 23);
		
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		JButton btnExit = new JButton("Exit");
		btnExit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CLI.setScanner( String.valueOf(e.getKeyChar()).toUpperCase() );
				game.makePlay();
				panel.updateStatus(game.getMaze());
			}
		});
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(214, 521, 194, 23);
		contentPane.add(btnExit);

		
		CLI.setScanner("P P 1");
		game=new Logic();		
		panel = new ShowMaze(game.getMaze());
		
		panel.setBounds(10, 10, 500, 500);
		contentPane.add(panel);
	}
}
