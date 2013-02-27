package maze.generate;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import maze.logic.*;

public class Maze {
	
	private Random generator=new Random();
	private int size=10;
	private int []oldx=new int[3];
	private int []oldy=new int[3];
	
	
	private char[][] maze={ {'x','x','x','x','x','x','x','x','x','x'},
			 {'x',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ',' ',' ',' ',' ',' ','x',' ','S'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ','x',' ','x',' ','x'},
			 {'x',' ','x','x',' ',' ',' ',' ',' ','x'},
			 {'x','x','x','x','x','x','x','x','x','x'},};
	
	public Maze(int ssize) {
		size=ssize;
		
		if(ssize==-1)
			return;
		
		maze=new char[size][size];
		maze = geraLab();
	}
	
	public char[][] getMaze() {
		return maze;
	}
	
	private char[][] geraLab() {
		
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

		Stack sx = new Stack(), sy=new Stack();
		sx.push((Integer)x3);
		sy.push((Integer)y3);
		
		int[] novo;
				
		int i=0, b=0;
		
		while(true) {
						
//			System.out.println("Antigo: "+x3+" "+y3);			
//			labirinto=maze;
//			imprime();
			
			do
				novo=pickNew(x3,y3);
			while(maze[novo[0]][novo[1]]!='x');
			
//			System.out.println("Tentativa: "+novo[0]+" "+novo[1]);
//			maze[novo[0]][novo[1]]=' ';
//			
//			labirinto=maze;
//			imprime();
//			System.out.println();
			
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
			b++;
			
			if(i>10) {
				
//				System.out.println("Backtrack!");
				
				//maze[x3][y3]='x';
				
				x3=(Integer)sx.pop();
				x3=(Integer)sx.pop();
				y3=(Integer)sy.pop();
				y3=(Integer)sy.pop();
				
				i=0;
			}
		}
				
		
		
		do {
			x3=generator.nextInt(size-1);
			y3=generator.nextInt(size-1);
		} while(maze[x3][y3]!=' ');
		
		maze[x3][y3]='E';
		
		do {
			x3=generator.nextInt(size-1);
			y3=generator.nextInt(size-1);
		} while(maze[x3][y3]!=' ');
		maze[x3][y3]='H';
		
		do {
			x3=generator.nextInt(size-1);
			y3=generator.nextInt(size-1);
		} while(maze[x3][y3]!=' ');
			maze[x3][y3]='D';
		
		return maze;
	}
	
	private int[] pickNew(int x, int y) {
		
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
			
			System.out.println("Random: "+n+" / "+x+","+y);
			
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
	
	private boolean checkValid(char[][] maze) {
		
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
	
	private static boolean checkStop(char[][] maze) {
		
		for(int x=0; x<maze.length-2; x++)
			for(int y=0; y<maze[0].length-2; y++)
				if(maze[x][y]=='x'&&maze[x][y+1]=='x'&&maze[x][y+2]=='x'&&
				maze[x+1][y]=='x'&&maze[x][y+1]=='x'&&maze[x][y+2]=='x'&&
				maze[x+2][y]=='x'&&maze[x][y+1]=='x'&&maze[x][y+2]=='x')
					return false;
		
		return true;
	}
	
	public void printConsole(Hero hero, Dragon dragon, Sword sword) {
		
		for(int i=0; i<oldx.length; i++)
			maze[oldx[i]][oldy[i]]=' ';
		
		if(!dragon.isDead()) {
			if(sword.getX()==dragon.getX() && sword.getX()==dragon.getX())
				maze[sword.getX()][sword.getY()]='F';
			else maze[dragon.getX()][dragon.getY()]=dragon.getSym();
			
			oldx[2]=dragon.getX();
			oldy[2]=dragon.getY();
		}
			
		
		if(!hero.isDead()) {
			oldx[0]=hero.getX();
			oldy[0]=hero.getY();
			
			maze[hero.getX()][hero.getY()]=hero.getSym();
		}
			
		
		if(!sword.isWield()) {
			oldx[1]=sword.getX();
			oldy[1]=sword.getY();
			maze[sword.getX()][sword.getY()]=sword.getSym();
		}
			
		
		for(char[] line: maze) {			
			for(char cell: line)
				System.out.print(" " + cell);
			
			System.out.println();
		}	
		
		
	}
}
