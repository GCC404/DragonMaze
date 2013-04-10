package maze.logic;

import java.io.Serializable;

import maze.generate.Generator;

@SuppressWarnings("serial")
public class Dragon extends Mobile implements Serializable {
	
	private boolean sleep=false;
	private int mode=3;
	
	/**
	 * Dragon constructor
	 * @param x 
	 * @param y
	 * @param mode-1:stop, 2:random, 3:random+sleep
	 */
	public Dragon(int x, int y, int mode) {
		super(x,y,'D');
		this.mode=mode;
	}
	
	/**
	 * @return sleep
	 */
	public boolean getSleep() {
		return sleep;
	}
	
	/**
	 * Set sleep
	 */
	public void setSleep() {
		sleep=true;
		this.sym='d';
	}

	/**
	 * Move dragon
	 * @param maze
	 */
	public void move(char[][] maze) {

		if(this.mode==3) {
			if(generator.nextInt(6)==0)
				sleep=!sleep;

			if(sleep) {
				this.sym='d';
				return;
			}

			this.sym='D';
		}

		if(this.mode==2 || this.mode==3) {

			switch(genMovem()) {

			case "W":
				if(maze[x-1][y]=='x' || maze[x-1][y]=='S')
					break;
				x--;
				break;

			case "A":
				if(maze[x][y-1]=='x' || maze[x][y-1]=='S')
					break;
				y--;
				break;

			case "S":
				if(maze[x+1][y]=='x' || maze[x+1][y]=='S')
					break;
				x++;
				break;

			case "D":
				if(maze[x][y+1]=='x' || maze[x][y+1]=='S')
					break;
				y++;
				break;

			case "":
				break;
			}
		}
	}

	/**
	 * Generate random move
	 * @return move
	 */
	private String genMovem() {

		int a = Generator.nextInt(5);

		switch(a) {
		case 0:
			return "";
		case 1:
			return "W";
		case 2:
			return "A";
		case 3:
			return "S";
		case 4:
			return "D";
		}
		return "";
	}

}