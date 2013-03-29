package maze.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ShowMaze extends JPanel {
	private BufferedImage wall, dragon, sword, dragonasleep, hero, herosword, exit;
	private char[][] maze={ 
			 {'x','x','x','x','x','x','x','x','x','x'},
			 {'x',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ',' ',' ',' ',' ',' ','x',' ','S'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ',' ',' ',' ',' ','x'},
			 {'x','x','x','x','x','x','x','x','x','x'},};

	public ShowMaze(char[][] maze) {
		try {                
			wall = ImageIO.read(new File("C:\\Users\\Gabriel\\workspace\\ProjetoGuiado\\wall.jpg"));
			dragon = ImageIO.read(new File("C:\\Users\\Gabriel\\workspace\\ProjetoGuiado\\dragao.png"));
			sword = ImageIO.read(new File("C:\\Users\\Gabriel\\workspace\\ProjetoGuiado\\sword.jpg"));
			dragonasleep = ImageIO.read(new File("C:\\Users\\Gabriel\\workspace\\ProjetoGuiado\\dragaosleep.png"));
			hero = ImageIO.read(new File("C:\\Users\\Gabriel\\workspace\\ProjetoGuiado\\hero.jpg"));
			herosword = ImageIO.read(new File("C:\\Users\\Gabriel\\workspace\\ProjetoGuiado\\herosword.gif"));
			exit = ImageIO.read(new File("C:\\Users\\Gabriel\\workspace\\ProjetoGuiado\\exit.jpg"));
		} catch (IOException ex) {
			// handle exception...
		}

		if(maze!=null)
			this.maze=maze;
		
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		int size=500/maze.length;
		for(int y=0; y<maze.length; y++)
			for(int x=0; x<maze[0].length; x++) {
				switch(maze[y][x]) {
				case ' ':
					g.fillRect(x*size, y*size, size, size);
					break;
				case 'x':
					g.drawImage(wall, x*size, y*size, size, size, null);
					break;
				case 'D':
					g.drawImage(dragon, x*size, y*size, size, size, null);
					break;
				case 'd':
					g.drawImage(dragonasleep, x*size, y*size, size, size, null);
					break;
				case 'S':
					g.drawImage(exit, x*size, y*size, size, size, null);
					break;
				case 'H':
					g.drawImage(hero, x*size, y*size, size, size, null);
					break;
				case 'E':
					g.drawImage(sword, x*size, y*size, size, size, null);
					break;
				case 'A':
					g.drawImage(herosword, x*size, y*size, size, size, null);
					break;
				}
			}
		
	}
	
	public void updateStatus(char[][] maze) {
		this.maze=maze;
		repaint();
	}
}