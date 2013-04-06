package maze.logic;


public class Eagle extends Mobile {
	
	private boolean move=false;
	private boolean espada=false;
	private boolean first=true;
	private boolean posInicial=false;

	private int posEspadaX;
	private int posEspadaY;
	private int posHeroiX;
	private int posHeroiY;
	
	

	public Eagle(int x, int y) {
		super(x,y,'G');
	}
	
	public void aMover() {
		move=true;
	}
	
	public boolean move() {
		return move;
	}

	public void comEspada() {
		espada=true;
	}
	
	public boolean espada() {
		return espada;
	}


	public void move(char[][] maze) {

		if(this.x==posEspadaX) {
			if(this.y==posEspadaY)
				this.espada=true;
			else if(this.y<posEspadaY)
				this.y++;
			else if(this.y>posEspadaY)
				this.y--;
		}
		else if(this.y==posEspadaY) {
			if(this.x<posEspadaX)
				this.x++;
			else if(this.x>posEspadaX)
				this.x--;
		}
		else {
			if(this.x<posEspadaX)
				this.x++;
			else if(this.x>posEspadaX)
				this.x--;
			if (this.y<posEspadaY)
				this.y++;
			else if (this.y>posEspadaY)
				this.y--;
		}
	}
	

	public void move1(char[][] maze, int x1, int y1) { //movimentar com o heroi (representacao implicita)
		this.x=x1;
		this.y=y1;
	}

	public void move2(char[][] maze, int x1, int y1, int x2, int y2) {// movimentar em direcao a espada

		first=false;

		this.posHeroiX=x1;
		this.posHeroiY=y1;
		this.posEspadaX=x2;
		this.posEspadaY=y2;
		
		if(this.x==posEspadaX) {
			if(this.y==posEspadaY) {
				this.espada=true;
				if(maze[posEspadaX][posEspadaY]=='E' ||	maze[posEspadaX-1][posEspadaY]=='D'
						 || maze[posEspadaX][posEspadaY-1]=='D' || maze[posEspadaX+1][posEspadaY]=='D'
								 || maze[posEspadaX][posEspadaY+1]=='D')
					this.dead=true; //aguia "morre" se espada ja nao estiver la e morrer quando o dragao esta la
			}
			else if(this.y<posEspadaY)
				this.y++;
			else if(this.y>posEspadaY)
				this.y--;
		}
		else if(this.y==posEspadaY) {
			if(this.x<posEspadaX)
				this.x++;
			else if(this.x>posEspadaX)
				this.x--;
		}
		else {
			if(this.x<posEspadaX)
				this.x++;
			else if(this.x>posEspadaX)
				this.x--;
			if (this.y<posEspadaY)
				this.y++;
			else if (this.y>posEspadaY)
				this.y--;
		}
	}


	public boolean first() {
		return first;
	}

	public void retorno(char[][] maze, int x1, int y1) {

		if(!posInicial) {
			if(this.x==posHeroiX) {
				if(this.y==posHeroiY) {
					posInicial=true;
					retorno2(maze, x1, y1);
				}
				else if(this.y<posHeroiY)
					this.y++;
				else if(this.y>posHeroiY)
					this.y--;
			}
			else if(this.y==posHeroiY) {
				if(this.x<posHeroiX)
					this.x++;
				else if(this.x>posHeroiX)
					this.x--;
			}
			else {
				if(this.x<posHeroiX)
					this.x++;
				else if(this.x>posHeroiX)
					this.x--;
				if (this.y<posHeroiY)
					this.y++;
				else if (this.y>posHeroiY)
					this.y--;
			}
		} else 
			retorno2(maze, x1, y1);

	}

	private void retorno2(char[][] maze, int x1, int y1) {
		
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

	public boolean posInicial() {
		return posInicial;
	}
}
