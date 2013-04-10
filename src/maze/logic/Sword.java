package maze.logic;


public class Sword extends GameElement {

	private boolean wield=false;
	
	/**
	 * Sword constructor
	 * @param x
	 * @param y
	 */
	public Sword(int x, int y) {
		super(x,y,'E');
	}
	
	/**
	 * Set weild to true
	 */
	public void setWield() {
		wield=true;
	}
	
	/**
	 * @return weild
	 */
	public boolean getWield() {
		return wield;
	}
}
