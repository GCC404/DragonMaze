package maze.generate;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import maze.cli.CLI;
import maze.logic.*;

public class Maze {
	
	private Random generator=new Random();
	private int size=10;
	private int []oldx=new int[4];
	private int []oldy=new int[4];
	private boolean def=true;
	private static int []oldXdragon;
	private static int []oldYdragon;
	private char oldsym='T';
	private char[][] maze={ 
			 {'x','x','x','x','x','x','x','x','x','x'},
			 {'x',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ',' ',' ',' ',' ',' ','x',' ','S'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ',' ',' ',' ',' ','x'},
			 {'x','x','x','x','x','x','x','x','x','x'},};
	
	/**
	 * Maze constructor
	 * @param ssize-size of maze
	 */
	public Maze(int ssize) {
		
		if(ssize==-1)
			return;
		
		def=false;
		size=ssize;
		maze=new char[size][size];
		maze=geraLab();
	}
	
	/**
	 * Maze constructor
	 * @param maze
	 */
	public Maze(char[][] maze) {
		this.maze=maze;
	}
	
	/**
	 * @return def
	 */
	public boolean isDefault() {
		return def;
	}
	
	/**
	 * @return maze
	 */
	public char[][] getMaze() {
		return maze;
	}
	
	/**
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Create new maze
	 * @return maze generated
	 */
	private char[][] geraLab() {
		
		char[][] maze= new char[size][size];
		Random generator=new Random();

		for(int i=0; i<maze.length; i++)
			Arrays.fill(maze[i], 'x');		

		int n=generator.nextInt(size);
		int x3=n,y3;

		if (n == 0) {
			do
				n=generator.nextInt(size);
			while (n==0 || n==size-1);
		} else if (n == size-1) {
			do
				n=generator.nextInt(size);
			while (n==0 || n==size-1);
		} else {
			n=generator.nextInt(2);
			if(n>0)
				n=size-1;
		}

		y3=n;

		maze[x3][y3]='S';

		Stack<Integer> sx = new Stack<Integer>(), sy=new Stack<Integer>();
		sx.push(x3);
		sy.push(y3);
		
		int[] novo;

		int i=0, b=0;

		while(!checkStop(maze)) {

			do {
				novo=pickNew(x3,y3, maze);

				if(b>10) {

					if(sx.size()==0)
						return geraLab();
					
					x3=(Integer)sx.pop();
					y3=(Integer)sy.pop();

					b=0;
				}
				
				novo=pickNew(x3,y3, maze);

				b++;
			}
			while(maze[novo[0]][novo[1]]!='x');

			maze[novo[0]][novo[1]]=' ';

			if(checkValid(maze)) {
				
				if(checkStop(maze))
					break;

				x3=novo[0];
				y3=novo[1];

				sx.push((Integer)x3);
				sy.push((Integer)y3);

				continue;
			}

			maze[novo[0]][novo[1]]='x';

			i++;

			if(i>10) {

				if(sx.size()==0)
					return geraLab();
				
				x3=(Integer)sx.pop();
				y3=(Integer)sy.pop();

				i=0;
			}	
		}
		return maze;
	}

	/**
	 * TODO
	 * @param x
	 * @param y
	 * @param maze
	 * @return
	 */
	private int[] pickNew(int x, int y, char[][]maze) {

		int[] res=new int[2];

		if(x==0) {
			res[0]=1;
			res[1]=y;

			return res;
		}

		if(y==0) {
			res[0]=x;
			res[1]=1;

			return res;
		}

		if(x==size-1) {
			res[0]=x-1;
			res[1]=y;

			return res;
		}

		if(y==size-1) {
			res[0]=x;
			res[1]=y-1;

			return res;
		}

		while(true) {
			int n=generator.nextInt(4);

			switch(n) {
			case 0:
				if(x>1) {
					res[0]=x-1;
					res[1]=y;

					return res;
				}

				break;

			case 1:
				if(y<size-1) {
					res[0]=x;
					res[1]=y+1;

					return res;
				}

				break;

			case 2:

				if(x<size-1) {
					res[0]=x+1;
					res[1]=y;

					return res;
				}

				break;

			case 3:

				if(y>1) {
					res[0]=x;
					res[1]=y-1;

					return res;
				}

				break;
			}
		}

	}

	/**
	 * TODO
	 * @param maze
	 * @return
	 */
	private static boolean checkValid(char[][] maze) {

		for(int y=0; y<maze[0].length; y++)
			if( (maze[0][y]!='x' && maze[0][y]!='S') || (maze[maze[0].length-1][y]!='S' && maze[maze[0].length-1][y]!='x') )
				return false;				

		for(int x=0; x<maze[0].length; x++)
			if( (maze[x][0]!='x' && maze[x][0]!='S') || (maze[x][maze[0].length-1]!='S' && maze[x][maze[0].length-1]!='x') )
				return false;

		for(int x=1; x<maze.length-1; x++)
			for(int y=1; y<maze[0].length-1; y++)
				if(maze[x][y]==' '&&maze[x+1][y]==' '&&maze[x][y+1]==' '
				&&maze[x+1][y+1]==' ')
					return false;

		return true;
	}

	/**
	 * TODO
	 * @param maze
	 * @return
	 */
	private static boolean checkStop(char[][] maze) {

		for(int x=0; x<maze.length-2; x++)
			for(int y=0; y<maze[0].length-2; y++)
				if(maze[x][y]=='x'&&maze[x][y+1]=='x'&&maze[x][y+2]=='x'&&
				maze[x+1][y]=='x'&&maze[x+1][y+1]=='x'&&maze[x+1][y+2]=='x'&&
				maze[x+2][y]=='x'&&maze[x+2][y+1]=='x'&&maze[x+2][y+2]=='x')
					return false;

		return true;
	}
	
	/**
	 * Update maze
	 * @param hero
	 * @param dragons
	 * @param sword
	 * @param eagle
	 */
	public void update(Hero hero, Dragon[] dragons, Sword sword, Eagle eagle) {
		
		for(int i=0; i<oldx.length-1; i++)
			maze[oldx[i]][oldy[i]]=' ';
		
		for(int j=0; j<oldXdragon.length-1; j++)
			maze[oldXdragon[j]][oldYdragon[j]]=' ';
		
		if(oldsym!='T')
			maze[oldx[2]][oldy[2]]=oldsym;
		
		if(!eagle.getDead()) {
			oldx[2]=eagle.getX();
			oldy[2]=eagle.getY();
			oldsym=maze[eagle.getX()][eagle.getY()];
			
			if(eagle.getMove())
				maze[eagle.getX()][eagle.getY()]=eagle.getSym();
		}

		for(int i=0; i<(dragons.length-1); i++) {
			
			if(!dragons[i].getDead()) {
				if(sword.getX()==dragons[i].getX() && sword.getX()==dragons[i].getX())
					maze[sword.getX()][sword.getY()]='F';
				else maze[dragons[i].getX()][dragons[i].getY()]=dragons[i].getSym();

				oldXdragon[i]=dragons[i].getX();
				oldYdragon[i]=dragons[i].getY();
			}
		}

		if(!sword.getWield()) {
			oldx[1]=sword.getX();
			oldy[1]=sword.getY();
			maze[sword.getX()][sword.getY()]=sword.getSym();
		}

		if(!hero.getDead()) {
			oldx[0]=hero.getX();
			oldy[0]=hero.getY();
			
			maze[hero.getX()][hero.getY()]=hero.getSym();
		}
		maze[0][0]='x';
	}

	/**
	 * Print maze
	 * @param hero
	 * @param dragons
	 * @param sword
	 * @param eagle
	 */
	public void printConsole(Hero hero, Dragon[] dragons, Sword sword, Eagle eagle) {
		
		update(hero,dragons,sword, eagle);
		
		CLI.printMaze(maze);
	}

	/**
	 * Update oldXdragon and oldYdragon
	 * @param numDragons
	 */
	public static void updateDragons(int numDragons) {
		oldXdragon=new int[numDragons+1];
		oldYdragon=new int[numDragons+1];
	}
}
