package maze.logic;


public class Eagle extends Mobile {
	
	private boolean move=false;
	private boolean sword=false;
	private boolean first=true;
	private boolean initialPos=false;

	private int swordX;
	private int swordY;
	private int heroX;
	private int heroY;
	
	/**
	 * Eagle constructor
	 * @param x
	 * @param y
	 */
	public Eagle(int x, int y) {
		super(x,y,'G');
	}
	
	/**
	 * @return move
	 */
	public boolean getMove() {
		return move;
	}

	/**
	 * Set move to true
	 */
	public void setMove() {
		move=true;
	}
	
	/**
	 * @return sword
	 */
	public boolean getSword() {
		return sword;
	}
	
	/**
	 * Set sword to true
	 */
	public void setSword() {
		sword=true;
	}

	/**
	 * @return first
	 */
	public boolean getFirst() {
		return first;
	}
	
	/**
	 * @return initialPos
	 */
	public boolean getInitialPos() {
		return initialPos;
	}

	/**
	 * Move eagle with hero
	 * @param maze
	 * @param x1 - hero x
	 * @param y1 - hero y
	 */
	public void moveHero(char[][] maze, int x1, int y1) { 
		this.x=x1;
		this.y=y1;
	}
	
	/**
	 * Move eagle to sword
	 * @param maze
	 */
	public void moveSword(char[][] maze) {

		if(this.x==swordX) {
			if(this.y==swordY)
				this.sword=true;
			else if(this.y<swordY)
				this.y++;
			else if(this.y>swordY)
				this.y--;
		}
		else if(this.y==swordY) {
			if(this.x<swordX)
				this.x++;
			else if(this.x>swordX)
				this.x--;
		}
		else {
			if(this.x<swordX)
				this.x++;
			else if(this.x>swordX)
				this.x--;
			if (this.y<swordY)
				this.y++;
			else if (this.y>swordY)
				this.y--;
		}
	}

	/**
	 * Move eagle to sword (first move)
	 * @param maze
	 * @param x1 - initial x hero
	 * @param y1 - initial y hero
	 * @param x2 - sword x
	 * @param y2 - sword y
	 */
	public void moveSwordFirst(char[][] maze, int x1, int y1, int x2, int y2) {

		first=false;

		this.heroX=x1;
		this.heroY=y1;
		this.swordX=x2;
		this.swordY=y2;
		
		if(this.x==swordX) {
			if(this.y==swordY) {
				this.sword=true;
				if(maze[swordX][swordY]=='E' ||	maze[swordX-1][swordY]=='D'
						 || maze[swordX][swordY-1]=='D' || maze[swordX+1][swordY]=='D'
								 || maze[swordX][swordY+1]=='D')
					this.dead=true; 
				}
			else if(this.y<swordY)
				this.y++;
			else if(this.y>swordY)
				this.y--;
		}
		else if(this.y==swordY) {
			if(this.x<swordX)
				this.x++;
			else if(this.x>swordX)
				this.x--;
		}
		else {
			if(this.x<swordX)
				this.x++;
			else if(this.x>swordX)
				this.x--;
			if (this.y<swordY)
				this.y++;
			else if (this.y>swordY)
				this.y--;
		}
	}

	/**
	 * Return to hero
	 * @param maze
	 * @param x1 - current x hero
	 * @param y1 - current y hero
	 */
	public void return1(char[][] maze, int x1, int y1) {

		if(!initialPos) {
			if(this.x==heroX) {
				if(this.y==heroY) {
					initialPos=true;
					return2(maze, x1, y1);
				}
				else if(this.y<heroY)
					this.y++;
				else if(this.y>heroY)
					this.y--;
			}
			else if(this.y==heroY) {
				if(this.x<heroX)
					this.x++;
				else if(this.x>heroX)
					this.x--;
			}
			else {
				if(this.x<heroX)
					this.x++;
				else if(this.x>heroX)
					this.x--;
				if (this.y<heroY)
					this.y++;
				else if (this.y>heroY)
					this.y--;
			}
		} else 
			return2(maze, x1, y1);

	}

	/**
	 * Return to hero from initial hero position
	 * @param maze
	 * @param x1 - current x hero
	 * @param y1 - current y hero
	 */
	private void return2(char[][] maze, int x1, int y1) {
		
		if(this.x==x1) {
			if(this.y==y1) {
				this.move=false;
				this.dead=true;
			}
			else if(this.y<y1)
				this.y++;
			else if(this.y>y1)
				this.y--;
		}
		else if(this.y==y1) {
			if(this.x<x1)
				this.x++;
			else if(this.x>x1)
				this.x--;
		}
		else {
			if(this.x<x1)
				this.x++;
			else if(this.x>x1)
				this.x--;
			if (this.y<y1)
				this.y++;
			else if (this.y>y1)
				this.y--;
		}		
	}
}
