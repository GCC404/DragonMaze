package maze.logic;

import java.util.Random;

public abstract class Mobile extends GameElement {
	
	protected Random generator=new Random();
	protected boolean dead=false;
	
	protected Mobile(int x, int y, char sym) {
		super(x,y,sym);
	}
	
	public void kill() {
		dead=true;
	}
	
	public boolean isDead() {
		return dead;
	}
}
