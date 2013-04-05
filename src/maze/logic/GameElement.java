package maze.logic;

public class GameElement {
	protected int x, y;
	protected char sym;
	
	public GameElement(int x, int y, char sym) {
		this.x=x;
		this.y=y;
		this.sym=sym;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public char getSym() {
		return sym;
	}
}
