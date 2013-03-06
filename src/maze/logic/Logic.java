package maze.logic;

import java.util.Random;
import maze.generate.Maze;
import maze.cli.*;

public class Logic {

	private Random gerador= new Random();
	private Hero hero;
	private Sword sword;
	private Maze maze;
	private Dragon dragon;
	private Eagle eagle;


	public Logic() {
		maze=new Maze(CLI.chooseMaze());
		int num=CLI.chooseDragon2();

		if(!maze.isDefault()) {

			int []x=new int[2];		
			pickEmptyPos(x);

			dragon=new Dragon(x[0],x[1],num);

			do
				pickEmptyPos(x);
			while(x[0]==dragon.getX() && x[1]==dragon.getY());

			sword=new Sword(x[0],x[1]);

			do
				pickEmptyPos(x);
			while( (x[0]==dragon.getX() && x[1]==dragon.getY()) || 
					(x[0]==sword.getX() && x[1]==sword.getY()));

			hero=new Hero(x[0],x[1]);
		} else {
			dragon=new Dragon(3,1,num);
			sword=new Sword(8,1);
			hero=new Hero(1,1);
		}

		eagle=new Eagle(hero.getX(), hero.getY());
		//CLI.chooseDragon1();

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
	public int play() {

		checkCollision();

		if(!hero.isDead())
			hero.move(maze.getMaze());
		else return -1;		

		eagle.move2(maze.getMaze(), hero.getX(), hero.getY());

		if(maze.getMaze()[hero.getX()][hero.getY()]=='S' && dragon.isDead())
			return 1;

		//checkCollision();

		if(!dragon.isDead())
			dragon.move(maze.getMaze());


		System.out.println();

		return 0;
	}

	public void printConsole() {
		maze.printConsole(hero, dragon, sword);
	}

	private void checkCollision() {		

		if(hero.getX()==dragon.getX())
			if(hero.getY()-dragon.getY()<2 && hero.getY()-dragon.getY()>-2)
				if(hero.isWield())
					dragon.kill();
				else if(!dragon.isAsleep())
					hero.kill();

		if(hero.getY()==dragon.getY())		
			if(hero.getX()-dragon.getX()<2 && hero.getX()-dragon.getX()>-2)
				if(hero.isWield())
					dragon.kill();
				else if(!dragon.isAsleep())
					hero.kill();


		if(hero.getX()==sword.getX() && hero.getY()==sword.getY()) {
			hero.weild();
			sword.wield();

		}


	}

}
