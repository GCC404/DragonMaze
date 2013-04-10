package maze.test;
import static org.junit.Assert.*;
import maze.logic.*;
import maze.cli.*;
import maze.generate.Generator;

/**
 * Test class to dragon
 * @author Sousa
 *
 */
public class TestDragon {
	
	/**
	 * Test 
	 */
	@org.junit.Test
	public void dragonStopped() {

		CLI.setScanner("P P");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("D D D S S S S A A A S S S W W W D D D S S S D D D D W");

		for(int i=0; i<27; i++) {
			game1.makePlay();
			maze=game1.getMaze();
			assertSame(maze[3][1],'D');
		}
	}
	
	/**
	 * 
	 */
	@org.junit.Test
	public void dragonSleep() {

		CLI.setScanner("P P");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("D S A W");

		for(int i=0; i<4; i++) {
			game1.makePlay();
			game1.setSleep();
			maze=game1.getMaze();
			assertSame(maze[3][1],'d');
		}
	}

	/**
	 * 
	 */
	@org.junit.Test
	public void dragonRandomMove() {

		CLI.setScanner("P A 1");
		Logic game1 = new Logic();
		char[][] maze;
		int[] dragonMove={3,3,4};
		Generator.setTest(dragonMove);
		CLI.setScanner("D D D");

		game1.makePlay();
		maze=game1.getMaze();
		assertSame(maze[4][1],'D');

		game1.makePlay();
		maze=game1.getMaze();
		assertSame(maze[5][1],'D');

		game1.makePlay();
		maze=game1.getMaze();
		assertSame(maze[5][2],'D');
	}
	
	/**
	 * 
	 */
	@org.junit.Test
	public void multipleDragons() {
		
		CLI.setScanner("A 10 P 1");
		Logic game1 = new Logic();
		char[][] maze=game1.getMaze();
		int count=0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
			if(maze[i][j]=='D')
				count++;
			}
		}
		assertSame(count, 1);
		
		CLI.setScanner("A 10 P 2");
		Logic game2 = new Logic();
		maze=game2.getMaze();
		count=0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
			if(maze[i][j]=='D')
				count++;
			}
		}
		assertSame(count, 2);
		
		CLI.setScanner("A 10 P 3");
		Logic game3 = new Logic();
		maze=game3.getMaze();
		count=0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
			if(maze[i][j]=='D')
				count++;
			}
		}
		assertSame(count, 3);
	}
}
