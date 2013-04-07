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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class GUIs extends JFrame {

	private JPanel contentPane;
	private Logic game;
	private ShowMaze gamepanel;
	private GameOptions optionwindow=new GameOptions();
	private Won wonwindow=new Won();
	private String gameoptions="P P 1";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIs frame = new GUIs();
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
	public GUIs() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				gamepanel.requestFocusInWindow();
			}
		});

		setTitle("Dungeons & Dragons");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("New Game");

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Are you sure?")==0) {
					gameoptions=optionwindow.getOptions();
					CLI.setScanner(gameoptions);
					game=new Logic();
					gamepanel.updateStatus(game.getMaze());
				}
			}
		});
		btnNewButton.setBounds(10, 521, 106, 23);

		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		JButton btnExit = new JButton("Exit");

		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(416, 521, 89, 23);
		contentPane.add(btnExit);


		CLI.setScanner(gameoptions);
		game=new Logic();		
		gamepanel = new ShowMaze(game.getMaze());

		gamepanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				System.out.print(c);
				if(c!='\n' && c!=' ') {
					String input=String.valueOf(c).toUpperCase();
					/*
				System.out.println(input+"W");
				System.out.println(input!="W");
				System.out.println(input!="W" && input!="A" && input!="S" && input!="D");

				if(input!="W" && input!="A" && input!="S" && input!="D")
					return;
					 */
					CLI.setScanner(input);
				}
				int response=game.makePlay();
				
				if(response==1) {
					wonwindow.won(true);
					wonwindow.setVisible(true);
					gameoptions=optionwindow.getOptions();
					CLI.setScanner(gameoptions);
					game=new Logic();
					gamepanel.updateStatus(game.getMaze());
				} else if(response==-1) {					
					wonwindow.won(false);
					wonwindow.setVisible(true);
					gameoptions=optionwindow.getOptions();
					CLI.setScanner(gameoptions);
					game=new Logic();
					gamepanel.updateStatus(game.getMaze());
				} else gamepanel.updateStatus(game.getMaze());
			}
		});


		gamepanel.setBounds(10, 10, 500, 500);
		contentPane.add(gamepanel);

		JButton options = new JButton("Options");
		options.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				optionwindow.setVisible(true);
				repaint();
			}
		});
		options.setBounds(115, 521, 82, 23);
		contentPane.add(options);

		JButton btnSaveGame = new JButton("Save game");
		btnSaveGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ObjectOutputStream os = null;
				try {
					os = new ObjectOutputStream(new FileOutputStream("file.dat"));
					os.writeObject(game);
				}
				catch(IOException e) {
					System.out.println("Couldn't savegame.");
				}
				if (os != null)
					try {
						os.close();
						System.out.println("Gravou");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		});
		btnSaveGame.setBounds(194, 521, 101, 23);
		contentPane.add(btnSaveGame);

		JButton btnLoadGame = new JButton("Load game");
		btnLoadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ObjectInputStream is = null;
				try {
					is = new ObjectInputStream(new FileInputStream("file.dat"));
					game=(Logic) is.readObject();
				}
				catch(IOException e) {
					System.out.println("Couldn't loadgame.");
				} catch (ClassNotFoundException e) {
					System.out.println("Couldn't loadgame.");
				}
				finally { if (is != null)
					try {
						is.close();
						System.out.println("Carregou");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				gamepanel.updateStatus(game.getMaze());
			}
		});
		btnLoadGame.setBounds(294, 521, 101, 23);
		contentPane.add(btnLoadGame);
	}
}
