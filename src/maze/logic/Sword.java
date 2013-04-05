package maze.logic;


public class Sword extends GameElement {

	private boolean wield=false;
	
	public Sword(int x, int y) {
		super(x,y,'E');
	}
	
	public void wield() {
		wield=true;
	}
	
	public boolean isWield() {
		return wield;
	}
}
