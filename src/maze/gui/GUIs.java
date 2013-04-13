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

@SuppressWarnings("serial")
public class GUIs extends JFrame {

	private JPanel contentPane;
	private Logic game;
	private MakeMaze makemaze=new MakeMaze();
	private ShowMaze gamepanel;
	private GameOptions optionwindow=new GameOptions();
	private WonDial wonwindow=new WonDial();
	private CmdDial commandwindow=new CmdDial();
	private String gameoptions="P P 1";
	char up='W',down='S',left='A',right='D',eagle='V';
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
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				gamepanel.requestFocusInWindow();
			}
		});

		setTitle("Dungeons & Dragons");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 620);
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

					if(makemaze.getHero().getY()==-1)
						game=new Logic();
					else game=new Logic(makemaze.getMaze(),makemaze.getHero(),makemaze.getSword(),makemaze.getDragons(),3);

					makemaze.reset();
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
		btnExit.setBounds(416, 521, 89, 49);
		contentPane.add(btnExit);


		CLI.setScanner(gameoptions);
		game=new Logic();		
		gamepanel = new ShowMaze(game.getMaze());

		gamepanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				//Reads keyboard input and converts to the corresponding 
				//upper case string (if that's the case)
				//String input=String.valueOf(e.getKeyChar());
				char cmd=e.getKeyChar();
				String input;
				
				if(cmd==up)
					input="W";
				else if(cmd==down)
					input="S";
				else if(cmd==left)
					input="A";
				else if(cmd==right)
					input="D";
				else if(cmd==eagle)
					input="V";
				else return;
				
				CLI.setScanner(input);
				
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
		options.setBounds(115, 521, 94, 23);
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
					} catch (IOException e) {
						e.printStackTrace();
					}

				gamepanel.requestFocusInWindow();
			}
		});
		btnSaveGame.setBounds(207, 521, 101, 23);
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
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				gamepanel.updateStatus(game.getMaze());
				gamepanel.requestFocusInWindow();
			}
		});
		btnLoadGame.setBounds(305, 521, 101, 23);
		contentPane.add(btnLoadGame);

		JButton btnMakeMaze = new JButton("Make Maze");
		btnMakeMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				makemaze.setVisible(true);
			}
		});
		btnMakeMaze.setBounds(10, 547, 106, 23);
		contentPane.add(btnMakeMaze);
		
		JButton btnNewButton_1 = new JButton("Choose commands");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				commandwindow.setVisible(true);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(115, 547, 152, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update commands");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				char []commands=commandwindow.getCmds();
				
				up=commands[0];
				down=commands[1];
				left=commands[2];
				right=commands[3];
				eagle=commands[4];
				
				gamepanel.requestFocusInWindow();
			}
		});
		btnNewButton_2.setBounds(266, 547, 141, 23);
		contentPane.add(btnNewButton_2);
	}
}
