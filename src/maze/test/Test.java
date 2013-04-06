package maze.test;
import static org.junit.Assert.*;
import maze.logic.*;
import maze.cli.*;

public class Test {

	@org.junit.Before
	public void prepare() {


	}

	@org.junit.Test
	public void moveHero() {
		CLI.setScanner("P P 1");
		Logic game1 = new Logic();

		CLI.setScanner("D");
		assertEquals(0,game1.makePlay());
		char[][] maze=game1.getMaze();
		assertSame(maze[1][2],'H');
		assertSame(maze[1][1],' ');

		CLI.setScanner("D");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][3],'H');
		assertSame(maze[1][2],' ');

		CLI.setScanner("D");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][4],'H');
		assertSame(maze[1][3],' ');

		CLI.setScanner("D");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][5],'H');
		assertSame(maze[1][4],' ');

		CLI.setScanner("A");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][4],'H');
		assertSame(maze[1][5],' ');

		CLI.setScanner("S");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][4],' ');
		assertSame(maze[2][4],'H');

		CLI.setScanner("W");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][4],'H');
		assertSame(maze[2][4],' ');
	}

	@org.junit.Test
	public void moveWall() {

		CLI.setScanner("P P 1");
		Logic game1 = new Logic();

		CLI.setScanner("A");
		assertEquals(0,game1.makePlay());
		char[][] maze=game1.getMaze();
		assertSame(maze[1][1],'H');
		assertSame(maze[1][0],'x');

		CLI.setScanner("W");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][1],'H');
		assertSame(maze[0][1],'x');
	}

	@org.junit.Test
	public void sword() {

		CLI.setScanner("P P 1");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("D D D S S S S A A A S S S W");

		for(int i=0; i<14; i++)
			assertEquals(0,game1.makePlay());
		
		maze=game1.getMaze();		
		assertSame(maze[7][1],'A');
		assertSame(maze[8][1],' ');
	}

	@org.junit.Test
	public void die() {

		CLI.setScanner("P P 1");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("S");	
		assertEquals(0,game1.makePlay());	
		assertEquals(-1,game1.makePlay());
		maze=game1.getMaze();

		assertSame(maze[3][1],'D');
		assertSame(maze[2][1],' ');
	}

	@org.junit.Test
	public void win() { 

		CLI.setScanner("P P 1");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("D D D S S S S A A A S S S W W W W S D D D S S S D D D D W W W");
		for(int i=0; i<31; i++)
			assertEquals(0,game1.makePlay());

		CLI.setScanner("D");
		assertEquals(1,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[5][9],'A');
	}

	@org.junit.Test
	public void failToWin() { 

		CLI.setScanner("P P 1");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("D D D S S S S A A A S S S W W W D D D S S S D D D D W W W");
		for(int i=0; i<29; i++)
			assertEquals(0,game1.makePlay());

		CLI.setScanner("D");
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[5][8],'A');
		assertNotSame(maze[5][9], 'A');
	}

	@org.junit.Test
	public void lose() { 

		CLI.setScanner("P P 1");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("S S");
		assertEquals(0,game1.makePlay());
		assertEquals(-1,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[3][1],'D');
		assertNotSame(maze[2][1], 'H');
	}

	@org.junit.Test
	public void dragonSleep() {

		CLI.setScanner("P P 1");
		Logic game1 = new Logic();
		char[][] maze;

		CLI.setScanner("D D D S S S S A A A S S S W W W D D D S S S D D D D W");

		for(int i=0; i<27; i++) {
			game1.makePlay();
			maze=game1.getMaze();
			assertSame(maze[3][1],'D');
		}
	}

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
}
