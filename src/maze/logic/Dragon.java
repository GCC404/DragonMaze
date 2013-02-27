package maze.logic;

public class Dragon extends Mobile {
	
	private boolean sleep=false;
	
	public Dragon(int x, int y) {
		super(x,y,'D');
	}
	
	public void move(char[][] maze) {
		
		if(generator.nextInt(6)==0)
			sleep=!sleep;
		
		if(sleep) {
			this.sym='d';
			return;
		}
			
		this.sym='D';
		
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
		}
		
	}
	
	private String genMovem() {

		int a = generator.nextInt(5);

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
	
	public boolean isAsleep() {
		return sleep;
	}
}