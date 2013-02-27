package maze.logic;

import maze.cli.CLI;

public class Hero extends Mobile {

	private boolean wield=false;

	public Hero(int x, int y) {
		super(x,y,'H');
	}

	public void move(char[][] maze) {

		String move=CLI.lerInput();

		switch(move) {
		case "A":

			if(maze[x][y-1]=='x')
				break;

			if(maze[x][y-1]=='E')
				this.weild();

			y--;

			break;
		case "W":

			if(maze[x-1][y]=='x')
				break;

			if(maze[x-1][y]=='E')
				this.weild();


			x--;

			break;
		case "D":

			if(maze[x][y+1]=='x')
				break;

			if(maze[x][y-1]=='E')
				this.weild();

			y++;

			break;
		case "S":

			if(maze[x+1][y]=='x')
				break;

			if(maze[x+1][y]=='E')
				this.weild();

			x++;

			break;

		default:
			break;
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