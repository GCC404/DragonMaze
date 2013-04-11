package maze.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import maze.generate.Maze;
import maze.logic.Hero;
import maze.logic.Sword;
import javax.swing.JToggleButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class MakeMaze extends JFrame {

	private JPanel contentPane;
	private ShowMaze gamepanel;
	private char[][] mazen, deepclone;
	private char drawing=' ';
	private int exitx=-1, exity=-1, herox=-1, heroy=-1, swordx=-1, swordy=-1;
	private ArrayList<Integer> dragons=new ArrayList<Integer>();
	private JToggleButton previousbtn;
	private IncMazeDial incompleteMaze = new IncMazeDial();

	public void reset() {
		mazen=new char[5][5];
		for(char[] line: mazen)
			Arrays.fill(line, 'x');
		drawing=' ';
		exitx=-1; exity=-1; herox=-1; heroy=-1; swordx=-1; swordy=-1;
		dragons=new ArrayList<Integer>();
		gamepanel.updateStatus(mazen);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeMaze frame = new MakeMaze();
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
	public MakeMaze() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				gamepanel.requestFocusInWindow();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 521, 500, 88);
		contentPane.add(panel_1);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setBounds(10, 13, 37, 17);
		panel_1.setLayout(null);
		panel_1.add(lblSize);

		final JSlider slider = new JSlider();
		slider.setValue(5);
		slider.setMaximum(40);
		slider.setMinimum(5);

		slider.setBounds(47, 13, 250, 25);
		panel_1.add(slider);

		final JToggleButton btnDragon = new JToggleButton("Dragon");
		btnDragon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawing='D';
				btnDragon.setSelected(true);

				previousbtn.setSelected(false);
				previousbtn=btnDragon;
			}
		});
		btnDragon.setBounds(10, 54, 89, 23);
		panel_1.add(btnDragon);

		final JToggleButton btnHero = new JToggleButton("Hero");
		btnHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawing='H';
				btnHero.setSelected(true);

				previousbtn.setSelected(false);
				previousbtn=btnHero;
			}
		});
		btnHero.setBounds(109, 54, 89, 23);
		panel_1.add(btnHero);

		final JToggleButton btnExit = new JToggleButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawing='S';
				btnExit.setSelected(true);

				previousbtn.setSelected(false);
				previousbtn=btnExit;
			}
		});
		btnExit.setBounds(208, 54, 89, 23);
		panel_1.add(btnExit);

		final JToggleButton btnSword = new JToggleButton("Sword");
		btnSword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawing='E';
				btnSword.setSelected(true);

				previousbtn.setSelected(false);
				previousbtn=btnSword;
			}
		});
		btnSword.setBounds(307, 54, 89, 23);
		panel_1.add(btnSword);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(exitx!=-1 && dragons.size()>0 && herox!=-1 && swordx!=-1)
					setVisible(false);
				else incompleteMaze.setVisible(true);
			}
		});
		btnFinish.setBounds(401, 54, 89, 23);
		panel_1.add(btnFinish);


		final JToggleButton tglbtnBlank = new JToggleButton("Blank");
		previousbtn=tglbtnBlank;
		tglbtnBlank.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				drawing=' ';
				tglbtnBlank.setSelected(true);

				previousbtn.setSelected(false);
				previousbtn=tglbtnBlank;
			}
		});
		tglbtnBlank.setSelected(true);
		tglbtnBlank.setBounds(307, 15, 89, 23);
		panel_1.add(tglbtnBlank);
		
		final JToggleButton tglbtnWall = new JToggleButton("Wall");
		tglbtnWall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(tglbtnWall.getModel().isSelected()) {
					drawing='x';
					tglbtnWall.setSelected(true);

					previousbtn.setSelected(false);
					previousbtn=tglbtnWall;
				}
				
			}
		});
		tglbtnWall.setBounds(401, 15, 89, 23);
		panel_1.add(tglbtnWall);

		mazen=new char[5][5];
		for(char[] line: mazen)
			Arrays.fill(line, 'x');

		gamepanel=new ShowMaze(mazen);
		gamepanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * Draws accordingly to mouse input.
				 * Blocks drawing on invalid spots
				 * and guarantees presence of only
				 * one instance of unique objects
				 * (sword, hero and exit).
				 */
				
				int size=500/mazen.length, x=arg0.getX()/size, y=arg0.getY()/size;

				switch(drawing) {
				case 'E':
					if(!((x==0 || x==mazen.length-1) || (y==0 || y==mazen.length-1))){

						if(x==0 && y==mazen.length-1)
							break;

						if(y==0 && x==mazen.length-1)
							break;

						if(mazen[y][x]!='x' && mazen[y][x]!=' ')
							break;

						if(swordx!=-1)
							mazen[swordy][swordx]='x';

						swordx=x;
						swordy=y;
						mazen[swordy][swordx]='E';
					}
					break;
				case 'H':
					if(!((x==0 || x==mazen.length-1) || (y==0 || y==mazen.length-1))){

						if(x==0 && y==mazen.length-1)
							break;

						if(y==0 && x==mazen.length-1)
							break;

						if(mazen[y][x]!='x' && mazen[y][x]!=' ')
							break;

						if(herox!=-1)
							mazen[heroy][herox]='x';

						herox=x;
						heroy=y;
						mazen[heroy][herox]='H';
					}

					break;
				case 'S':
					if(x!=y && ((x==0 || x==mazen.length-1) || (y==0 || y==mazen.length-1)) ){

						if(x==0 && y==mazen.length-1)
							break;

						if(y==0 && x==mazen.length-1)
							break;

						if(mazen[y][x]!='x' && mazen[y][x]!=' ')
							break;

						if(exitx!=-1)
							mazen[exity][exitx]='x';

						exitx=x;
						exity=y;
						mazen[exity][exitx]='S';
					}

					break;
				case 'D':
					if(!((x==0 || x==mazen.length-1) || (y==0 || y==mazen.length-1))){

						if(x==0 && y==mazen.length-1)
							break;

						if(y==0 && x==mazen.length-1)
							break;

						if(mazen[y][x]!='x' && mazen[y][x]!=' ')
							break;

						dragons.add(y);
						dragons.add(x);
						mazen[y][x]='D';
					}
					break;
				case ' ':
					if(!((x==0 || x==mazen.length-1) || (y==0 || y==mazen.length-1)))
						if(mazen[y][x]=='x')
							mazen[y][x]=' ';
					break;
				case 'x':
					if(!((x==0 || x==mazen.length-1) || (y==0 || y==mazen.length-1)))
						if(mazen[y][x]==' ')
							mazen[y][x]='x';
					break;
				}

				gamepanel.updateStatus(mazen);
			}
		});
		gamepanel.setBounds(10, 10, 500, 500);
		contentPane.add(gamepanel);

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int mazesize=slider.getValue();
				mazen=new char[mazesize][mazesize];

				for(char[] line: mazen)
					Arrays.fill(line, 'x');

				gamepanel.updateStatus(mazen);
			}
		});
	}

	public Hero getHero() {
		return new Hero(heroy,herox);
	}

	public Sword getSword() {
		return new Sword(swordy,swordx);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getDragons() {
		return (ArrayList<Integer>) dragons.clone();
	}
	
	public Maze getMaze() {
		
		//Converts maze to wall-only
		mazen[heroy][herox]=' ';
		mazen[swordy][swordx]=' ';		
		for(int i=0; i<dragons.size(); i+=2)
			mazen[dragons.get(i)][dragons.get(i+1)]=' ';
		
		//Clones maze array
		deepclone=new char[mazen.length][mazen.length];
		for(int i=0; i<mazen.length; i++)
			deepclone[i]=mazen[i].clone();
		
		return new Maze(deepclone);
	}
}
