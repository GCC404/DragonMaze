package maze.logic;

public class Eagle extends Mobile {
	
	private boolean move=false;

	public Eagle(int x, int y) {
		super(x,y,'G');
	}

	public void move(char[][] maze) { }

	public void move2(char[][] maze, int x, int y) {

		if(!move) {
			this.x=x;
			this.y=y;
		} else { // movimentar em direcao a espada
			
		}
	}

}
