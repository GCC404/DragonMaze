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

			if(x<0 || x>9 || y-1<0 || y-1>9)
				break;
			
			if(maze[x][y-1]=='x')
				break;

			if(maze[x][y-1]=='E')
				this.weild();

			if(maze[x][y-1]=='S' && !this.wield)
				break;
			
			y--;
			

			break;
		case "W":

			if(x-1<0 || x-1>9 || y<0 || y>9)
				break;
			
			if(maze[x-1][y]=='x')
				break;

			if(maze[x-1][y]=='E')
				this.weild();
			
			if(maze[x-1][y]=='S' && !this.wield)
				break;

			x--;

			break;
		case "D":
			
			if(x<0 || x>9 || y+1<0 || y+1>9)
				break;
			
			if(maze[x][y+1]=='x')
				break;

			if(maze[x][y-1]=='E')
				this.weild();

			if(maze[x][y+1]=='S' && !this.wield)
				break;
			
			y++;

			break;
		case "S":
			
			if(x+1<0 || x+1>9 || y<0 || y>9)
				break;
			
			if(maze[x+1][y]=='x')
				break;

			if(maze[x+1][y]=='E')
				this.weild();

			if(maze[x+1][y]=='S' && !this.wield)
				break;
			
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