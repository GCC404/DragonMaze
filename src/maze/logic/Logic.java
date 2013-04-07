package maze.logic;


import java.io.Serializable;
import java.util.Random;

import maze.generate.Maze;
import maze.cli.*;

public class Logic implements Serializable {

	private Random gerador= new Random();
	private Hero hero;
	private Sword sword;
	private Maze maze;
	private Dragon[] dragons;
	private Eagle eagle;
	private int numDragons=1;
	

	public char[][] getMaze() {
		maze.update(hero, dragons, sword, eagle);
		return maze.getMaze();
	}
	
	public Logic() {
		maze=new Maze(CLI.chooseMaze());
		int num=CLI.chooseDragon2();
		
		int []x=new int[2];	
		
		if(!maze.isDefault()) {
				
			numDragons=CLI.chooseDragon1();
			dragons=new Dragon[numDragons+1];
			
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
			dragons=new Dragon[numDragons+1];
			dragons[0]=new Dragon(3,1,num);
			sword=new Sword(8,1);
			hero=new Hero(1,1);
		}

		eagle=new Eagle(hero.getX(), hero.getY());

		Maze.atualizaDragoes(numDragons);
	}

	
	private void pickEmptyPos(int x[], int i) {
		int max=maze.getSize();
		char[][] auxmaze=maze.getMaze();

		do {
			x[0]=gerador.nextInt(max);
			x[1]=gerador.nextInt(max);
		} while (auxmaze[x[0]][x[1]]!=' ');	

		for(int j=1; j<i; j++) {
			if(dragons[j].getX()==x[0] && dragons[j].getY()==x[1])
				pickEmptyPos(x, i);
		}
	}

	//Ganhou 1
	//Perdeu -1
	//Continua a jogar 0
	public int makePlay() {

		checkCollision();
		int ret;
		
		if(!hero.isDead())
			ret=hero.move(maze.getMaze(), countDragons());
		else return -1;		
		if(hero.isDead())
			return -1;
		
		if(ret==1 && !eagle.isDead())
			eagle.aMover();
		else if(ret==2)
			return 1;
				
		if(!eagle.isDead()) {
			if(!eagle.move() && !eagle.espada())
				eagle.move1(maze.getMaze(), hero.getX(), hero.getY());
			else if(eagle.move() && !eagle.espada()) {
				if(eagle.first())
					eagle.move2(maze.getMaze(), hero.getX(), hero.getY(), sword.getX(), sword.getY());
				else
					eagle.move(maze.getMaze());
			}
			else if(eagle.espada()) {
				sword.wield();
				eagle.retorno(maze.getMaze(), hero.getX(), hero.getY());
			}
		}
		
		if(eagle.espada() && eagle.getX()==hero.getX() && eagle.getY()==hero.getY()) {
			hero.weild();
			eagle.kill();
		}

		for(int i=0; i<numDragons; i++) {
			if(!dragons[i].isDead())
				dragons[i].move(maze.getMaze());
		}

		return 0;
	}

	private boolean countDragons() {

		int count=0;
		for(int i=0; i<numDragons; i++)
			if(dragons[i].isDead())
				count++;

		return (count==numDragons);
	}

	public void printConsole() {
		maze.printConsole(hero, dragons, sword, eagle);
	}

	private void checkCollision() {		

		for(int i=0; i<numDragons; i++) {
			if(!dragons[i].isDead()) {
				if(hero.getX()==dragons[i].getX())
					if(hero.getY()-dragons[i].getY()<2 && hero.getY()-dragons[i].getY()>-2)
						if(hero.isWield())
							dragons[i].kill();
						else if(!dragons[i].isAsleep())
							hero.kill();

				if(hero.getY()==dragons[i].getY())		
					if(hero.getX()-dragons[i].getX()<2 && hero.getX()-dragons[i].getX()>-2)
						if(hero.isWield())
							dragons[i].kill();
						else if(!dragons[i].isAsleep())
							hero.kill();

				if(!eagle.isDead()) {
					if(eagle.getX()==dragons[i].getX()) {
						if(eagle.getY()-dragons[i].getY()<2 && eagle.getY()-dragons[i].getY()>-2
								&& !dragons[i].isAsleep() && eagle.posInicial())
							eagle.kill();
					}
					else if(eagle.getY()==dragons[i].getY()) {	
						if(eagle.getX()-dragons[i].getX()<2 && eagle.getX()-dragons[i].getX()>-2
								&& !dragons[i].isAsleep() && eagle.posInicial())
							eagle.kill();
					}
				}
			}
		}
		
		if(hero.getX()==sword.getX() && hero.getY()==sword.getY()) {
			hero.weild();
			sword.wield();
			eagle.kill();
		}
	}

	public void play() {  

		int response=0;

		printConsole();

		while(response==0) {

			response=makePlay();
			printConsole();
		}

		if(response==1)
			System.out.println("GANHASTE MÁNINHO!");
		else System.out.println("Já foste.");
	}
}
