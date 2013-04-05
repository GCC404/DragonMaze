package maze.logic;

import maze.cli.CLI;

public class Hero extends Mobile {

	private boolean wield=false;

	public Hero(int x, int y) {
		super(x,y,'H');
	}
	
	public int move(char[][] maze, boolean countDragons) {

		String move=CLI.lerInput();

		switch(move) {
		case "A":

			if(maze[x][y-1]=='x')
				return 0;

			if(maze[x][y-1]=='E')
				this.weild();

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
				this.weild();

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
				this.weild();

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
				this.weild();

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

	public void weild() {
		this.sym='A';
		wield=true;
	}

	public boolean isWield() {
		return wield;
	}
}