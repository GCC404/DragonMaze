package maze.logic;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Sword extends GameElement implements Serializable {

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
	 * Set wield to true
	 */
	public void setWield() {
		wield=true;
	}
	
	/**
	 * @return wield
	 */
	public boolean getWield() {
		return wield;
	}
}
