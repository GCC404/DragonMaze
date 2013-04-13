package maze.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import maze.logic.Maze;
import maze.cli.*;

@SuppressWarnings("serial")
public class Logic implements Serializable {

	private Random gerador= new Random();
	private Hero hero;
	private Sword sword;
	private Maze maze;
	private Dragon[] dragons;
	private Eagle eagle;
	private int numDragons=1;

	/**
	 * Get maze
	 * @return maze updated
	 */
	public char[][] getMaze() {
		maze.update(hero, dragons, sword, eagle);
		return maze.getMaze();
	}
	
	/**
	 * Logic constructor
	 * @param maze
	 * @param hero
	 * @param sword
	 * @param posDragons
	 * @param mode-dragon mode
	 */
    public Logic(Maze maze, Hero hero, Sword sword, ArrayList<Integer> posDragons, int mode) {
		this.hero=hero;
        
		dragons=new Dragon[posDragons.size()/2];
		
		for(int i=0; i<posDragons.size(); i+=2)
			dragons[i/2]=new Dragon(posDragons.get(i), posDragons.get(i+1), mode);

		Maze.updateDragons(posDragons.size()/2+1);
        
		this.sword=sword;
		this.maze=maze;
		this.eagle=new Eagle(hero.getX(), hero.getY());
		numDragons=posDragons.size()/2;
	}
	
	
	/**
	 * Logic constructor
	 */
	public Logic() {
		maze=new Maze(CLI.chooseMaze());
		int num=CLI.chooseModeDragons();
		
		int []x=new int[2];	
		
		if(!maze.isDefault()) {
				
			numDragons=CLI.chooseNumDragons();
			dragons=new Dragon[numDragons];
			
			pickEmptyPos(x, 0);
			dragons[0]=new Dragon(x[0],x[1],num);

			for(int i=1; i<dragons.length; i++) {
				pickEmptyPos(x, i);
				dragons[i]=new Dragon(x[0],x[1],num);
			}

			pickEmptyPos(x, numDragons);
			sword=new Sword(x[0],x[1]);

			do
				pickEmptyPos(x, numDragons);
			while((x[0]==sword.getX() && x[1]==sword.getY()));
			hero=new Hero(x[0],x[1]);
			
		} else {
			dragons=new Dragon[numDragons];
			dragons[0]=new Dragon(3,1,num);
			sword=new Sword(8,1);
			hero=new Hero(1,1);
		}

		eagle=new Eagle(hero.getX(), hero.getY());

		Maze.updateDragons(numDragons);
	}

	/**
	 * Choose empty position
	 * @param x-new random positions
	 * @param i-current number of dragons
	 */
	private void pickEmptyPos(int x[], int i) { 
		
		int max=maze.getSize();
		char[][] auxmaze=maze.getMaze();

		do {
			x[0]=gerador.nextInt(max);
			x[1]=gerador.nextInt(max);
		} while (auxmaze[x[0]][x[1]]!=' ');	

		for(int j=0; j<i; j++) {
			if(dragons[j].getX()==x[0] && dragons[j].getY()==x[1])
				pickEmptyPos(x, i);
		}
	}

	/**
	 * Make play
	 * @return 1:won, -1:lost, 0:keep playing
	 */
	public int makePlay() {

		checkCollision();
		int ret;
		
		if(!hero.getDead())
			ret=hero.move(maze.getMaze(), countDragons());
		else return -1;		
		
		if(ret==1 && !eagle.getDead())
			eagle.setMove();
		else if(ret==2)
			return 1;
				
		if(!eagle.getDead()) {
			if(!eagle.getMove() && !eagle.getSword())
				eagle.moveHero(maze.getMaze(), hero.getX(), hero.getY());
			else if(eagle.getMove() && !eagle.getSword()) {
				if(eagle.getFirst())
					eagle.moveSwordFirst(maze.getMaze(), hero.getX(), hero.getY(), sword.getX(), sword.getY());
				else
					eagle.moveSword(maze.getMaze());
			}
			else if(eagle.getSword()) {
				sword.setWield();
				eagle.return1(maze.getMaze(), hero.getX(), hero.getY());
			}
		}
		
		if(eagle.getSword() && eagle.getX()==hero.getX() && eagle.getY()==hero.getY()) {
			hero.setWeild();
			eagle.kill();
		}

		for(int i=0; i<numDragons; i++) {
			if(!dragons[i].getDead())
				dragons[i].move(maze.getMaze());
		}

		return 0;
	}

	/**
	 * Count number of dragons already killed
	 * @return true if hero has already killed all dragons
	 */
	private boolean countDragons() {

		int count=0;
		for(int i=0; i<numDragons; i++)
			if(dragons[i].getDead())
				count++;

		return (count==numDragons);
	}

	/**
	 * Print maze
	 */
	public void printConsole() {
		maze.printConsole(hero, dragons, sword, eagle);
	}

	/**
	 * Check collisions between all game elements
	 */
	private void checkCollision() {		

		for(int i=0; i<numDragons; i++) {
			if(!dragons[i].getDead()) {
				if(hero.getX()==dragons[i].getX())
					if(hero.getY()-dragons[i].getY()<2 && hero.getY()-dragons[i].getY()>-2)
						if(hero.isWield())
							dragons[i].kill();
						else if(!dragons[i].getSleep())
							hero.kill();

				if(hero.getY()==dragons[i].getY())		
					if(hero.getX()-dragons[i].getX()<2 && hero.getX()-dragons[i].getX()>-2)
						if(hero.isWield())
							dragons[i].kill();
						else if(!dragons[i].getSleep())
							hero.kill();

				if(!eagle.getDead()) {
					if(eagle.getX()==dragons[i].getX()) {
						if(eagle.getY()-dragons[i].getY()<2 && eagle.getY()-dragons[i].getY()>-2
								&& !dragons[i].getSleep() && eagle.getInitialPos())
							eagle.kill();
					}
					else if(eagle.getY()==dragons[i].getY()) {	
						if(eagle.getX()-dragons[i].getX()<2 && eagle.getX()-dragons[i].getX()>-2
								&& !dragons[i].getSleep() && eagle.getInitialPos())
							eagle.kill();
					}
				}
			}
		}
		
		if(hero.getX()==sword.getX() && hero.getY()==sword.getY()) {
			hero.setWeild();
			sword.setWield();
			eagle.kill();
		}
	}


	/**
	 * General gameplay cycle.
	 */
	public void play() {  

		int response=0;

		printConsole();

		while(response==0) {

			response=makePlay();
			printConsole();
		}

		if(response==1)
			System.out.println("You won!");
		else System.out.println("You lose..");
	}

	/**
	 * Set sleep (only for dragon tests)
	 */
	public void setSleep() { 
		dragons[0].setSleep();
	}
}
