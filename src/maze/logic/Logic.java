package maze.logic;

import java.util.Random;
import maze.generate.Maze;
import maze.cli.*;

public class Logic {
	
	private Random gerador= new Random();
	private Hero hero=new Hero(1,1);
	private Sword sword;
	private Maze maze;
	private Dragon dragon;
	
	public Logic() {
		sword=new Sword(8,1);
		maze=new Maze(CLI.chooseMaze());
		dragon=new Dragon(3,1);
	}
	
	//Ganhou 1
	//Perdeu -1
	//Continua a jogar 0
	public int play() {
			
		checkCollision();
		
		if(!hero.isDead())
			hero.move(maze.getMaze());
		else return -1;		

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
			sword.wield();
			hero.weild();
		}
			
		
	}

}
