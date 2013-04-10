package maze.logic;

import java.util.Random;

@SuppressWarnings("serial")
public abstract class Mobile extends GameElement {
	
	protected Random generator=new Random();
	protected boolean dead=false;
	
	/**
	 * Mobile constructor
	 * @param x
	 * @param y
	 * @param sym
	 */
	protected Mobile(int x, int y, char sym) {
		super(x,y,sym);
	}
	
	/**
	 * Set kill to true
	 */
	public void kill() {
		dead=true;
	}
	
	/**
	 * @return dead
	 */
	public boolean getDead() {
		return dead;
	}
}
