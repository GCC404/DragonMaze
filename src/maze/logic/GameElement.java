package maze.logic;

import java.io.Serializable;

import maze.generator.Generator;

@SuppressWarnings("serial")
public class GameElement implements Serializable {
	protected Generator random=new Generator();
	protected int x, y;
	protected char sym;
	
	/**
	 * GameElement constructor
	 * @param x
	 * @param y
	 * @param sym
	 */
	public GameElement(int x, int y, char sym) {
		this.x=x;
		this.y=y;
		this.sym=sym;
	}
	
	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @return sym
	 */
	public char getSym() {
		return sym;
	}
}
