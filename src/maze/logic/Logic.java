package maze.logic;


import java.util.Random;

import maze.generate.Maze;
import maze.cli.*;

public class Logic {

	private Random gerador= new Random();
	private Hero hero;
	private Sword sword;
	private Maze maze;
	private Dragon[] dragons;
	private Eagle eagle;
	private int numDragons;
	

	public char[][] getMaze() {
		maze.update(hero, dragons, sword, eagle);
		return maze.getMaze();
	}
	
	public Logic() {
		maze=new Maze(CLI.chooseMaze());
		int num=CLI.chooseDragon2();
		numDragons=CLI.chooseDragon1();
		dragons=new Dragon[numDragons+1];
		
		int []x=new int[2];	
		
		if(!maze.isDefault()) {
				
			pickEmptyPos(x);
			dragons[0]=new Dragon(x[0],x[1],num);

			for(int i=1; i<dragons.length-1; i++) {
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
			dragons[0]=new Dragon(3,1,num);
			for(int i=1; i<numDragons-1; i++) {
				if(i==1)
					dragons[i]=new Dragon(2,8,num);
				else if(i==2) {
					System.out.println("aqui");
					dragons[i]=new Dragon(8,5,num);
				}
			}
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
	
	private void pickEmptyPos(int x[]) {
		int max=maze.getSize();
		char[][] auxmaze=maze.getMaze();

		do {
			x[0]=gerador.nextInt(max);
			x[1]=gerador.nextInt(max);
		} while (auxmaze[x[0]][x[1]]!=' ');		

	}

	//Ganhou 1
	//Perdeu -1
	//Continua a jogar 0
	public int makePlay() {

		checkCollision();
		int ret;
		
		if(!hero.isDead())
			ret=hero.move1(maze.getMaze());
		else return -1;		
		
		if(ret==1 && !eagle.isDead()) {
			eagle.aMover();
		}
		
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

		if(eagle.espada() && eagle.noHeroi())
			hero.weild();

		int count=0;
		for(int i=0; i<numDragons; i++) {
			if(maze.getMaze()[hero.getX()][hero.getY()]=='S' && dragons[i].isDead() && hero.isWield())
				count++;
		}
		if(count==numDragons) {
			return 1;
		}
		

		//checkCollision();

		for(int i=0; i<numDragons-1; i++) {
			if(!dragons[i].isDead())
				dragons[i].move(maze.getMaze());
		}

		//System.out.println();

		return 0;
	}

	public void printConsole() {
		maze.printConsole(hero, dragons, sword, eagle);
	}

	private void checkCollision() {		

		for(int i=0; i<numDragons-1; i++) {
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


			if(hero.getX()==sword.getX() && hero.getY()==sword.getY()) {
				hero.weild();
				sword.wield();
			}

			if(eagle.getX()==dragons[i].getX()) {
				if(eagle.getY()-dragons[i].getY()<2 && eagle.getY()-dragons[i].getY()>-2 && !dragons[i].isAsleep() && !eagle.espada())
					eagle.kill();
			}
			else if(eagle.getY()==dragons[i].getY()) {		
				if(eagle.getX()-dragons[i].getX()<2 && eagle.getX()-dragons[i].getX()>-2 && !dragons[i].isAsleep() && !eagle.espada())
					eagle.kill();
			}
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
