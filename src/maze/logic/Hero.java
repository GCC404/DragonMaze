package maze.logic;

import maze.cli.CLI;

public class Hero extends Mobile {

	private boolean wield=false;

	public Hero(int x, int y) {
		super(x,y,'H');
	}

	public void move(char[][] maze) { }
	
	public int move1(char[][] maze) {

		String move=CLI.lerInput();
		
		//System.out.println("Leu: "+move);

		switch(move) {
		case "A":

			if(x<0 || x>9 || y-1<0 || y-1>9)
				return 0;
			
			if(maze[x][y-1]=='x')
				return 0;

			if(maze[x][y-1]=='E')
				this.weild();

			if(maze[x][y-1]=='S' && this.wield)
				return 0;
			
			y--;
			
			return 0;
		case "W":

			if(x-1<0 || x-1>9 || y<0 || y>9)
				return 0;
			
			if(maze[x-1][y]=='x')
				return 0;

			if(maze[x-1][y]=='E')
				this.weild();
			
			if(maze[x-1][y]=='S' && !this.wield)
				return 0;

			x--;

			return 0;
		case "D":
			
			if(x<0 || x>9 || y+1<0 || y+1>9)
				return 0;
			
			if(maze[x][y+1]=='x')
				return 0;

			if(maze[x][y-1]=='E')
				this.weild();

			if(maze[x][y+1]=='S' && !this.wield)
				return 0;
			
			y++;

			return 0;
		case "S":
			
			if(x+1<0 || x+1>9 || y<0 || y>9)
				return 0;
			
			if(maze[x+1][y]=='x')
				return 0;

			if(maze[x+1][y]=='E')
				this.weild();

			if(maze[x+1][y]=='S' && !this.wield)
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