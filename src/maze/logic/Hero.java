package maze.logic;

import maze.cli.CLI;

public class Hero extends Mobile {

	private boolean wield=false;

	/**
	 * Heor constructor
	 * @param x
	 * @param y
	 */
	public Hero(int x, int y) {
		super(x,y,'H');
	}
	
	/**
	 * Set weild to true
	 */
	public void setWeild() {
		this.sym='A';
		wield=true;
	}

	/**
	 * @return weild
	 */
	public boolean isWield() {
		return wield;
	}
	
	/**
	 * Move hero
	 * @param maze
	 * @param countDragons-true if hero has already killed all dragons
	 * @return 0:keep playing; 1:move eagle; 2:win
	 */
	public int move(char[][] maze, boolean countDragons) {

		String move=CLI.readInput();

		switch(move) {
		case "A":

			if(maze[x][y-1]=='x')
				return 0;

			if(maze[x][y-1]=='E')
				this.setWeild();

			if(maze[x][y-1]=='S' && countDragons) {
				y--;
				return 2;
			}
			else if(maze[x][y-1]=='S' && !countDragons)
				return 0;
				
			y--;
			
			return 0;
		case "W":
			
			if(maze[x-1][y]=='x')
				return 0;

			if(maze[x-1][y]=='E')
				this.setWeild();

			if(maze[x-1][y]=='S' && countDragons) {
				x--;
				return 2;
			}
			else if(maze[x-1][y]=='S' && !countDragons)
				return 0;

			x--;

			return 0;
		case "D":
			
			if(maze[x][y+1]=='x')
				return 0;

			if(maze[x][y-1]=='E')
				this.setWeild();

			if(maze[x][y+1]=='S' && countDragons) {
				y++;
				return 2;
			}
			else if(maze[x][y+1]=='S' && !countDragons)
				return 0;
			
			y++;

			return 0;
		case "S":

			if(maze[x+1][y]=='x')
				return 0;

			if(maze[x+1][y]=='E')
				this.setWeild();

			if(maze[x+1][y]=='S' && countDragons) {
				x++;
				return 2;
			}
			else if(maze[x+1][y]=='S' && !countDragons)
				return 0;

			x++;

			return 0;

		case "V":
			return 1;
			
		default:
			return 0;
		}
	}

	
}