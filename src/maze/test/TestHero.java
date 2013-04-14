package maze.test;
import static org.junit.Assert.*;
import maze.logic.*;

/**
 * Test class for hero
 * @author Ana Sousa e Gabriel Candal
 *
 */
public class TestHero {
	
	/**
	 * Test hero's movement to free cells
	 */
	@org.junit.Test
	public void moveHero() {
		
		Logic game1 = new Logic(-1, 1, 1);

		assertEquals(0,game1.makePlay("D"));
		char[][] maze=game1.getMaze();
		assertSame(maze[1][2],'H');
		assertSame(maze[1][1],' ');

		assertEquals(0,game1.makePlay("D"));
		maze=game1.getMaze();
		assertSame(maze[1][3],'H');
		assertSame(maze[1][2],' ');

		assertEquals(0,game1.makePlay("D"));
		maze=game1.getMaze();
		assertSame(maze[1][4],'H');
		assertSame(maze[1][3],' ');

		assertEquals(0,game1.makePlay("D"));
		maze=game1.getMaze();
		assertSame(maze[1][5],'H');
		assertSame(maze[1][4],' ');

		assertEquals(0,game1.makePlay("A"));
		maze=game1.getMaze();
		assertSame(maze[1][4],'H');
		assertSame(maze[1][5],' ');

		assertEquals(0,game1.makePlay("S"));
		maze=game1.getMaze();
		assertSame(maze[1][4],' ');
		assertSame(maze[2][4],'H');

		assertEquals(0,game1.makePlay("W"));
		maze=game1.getMaze();
		assertSame(maze[1][4],'H');
		assertSame(maze[2][4],' ');
	}

	/**
	 * Test hero's movement against walls
	 */
	@org.junit.Test
	public void moveWall() {

		Logic game1 = new Logic(-1, 1, 1);

		assertEquals(0,game1.makePlay("A"));
		char[][] maze=game1.getMaze();
		assertSame(maze[1][1],'H');
		assertSame(maze[1][0],'x');

		assertEquals(0,game1.makePlay("W"));
		maze=game1.getMaze();
		assertSame(maze[1][1],'H');
		assertSame(maze[0][1],'x');
	}

	@org.junit.Test
	public void sword() {

		Logic game1 = new Logic(-1, 1, 1);
		char[][] maze;

		String moves="DDDSSSSAAASSSW";

		for(int i=0; i<14; i++)
			assertEquals(0,game1.makePlay(moves.substring(i, i+1)));

		maze=game1.getMaze();		
		assertSame(maze[7][1],'A');
		assertSame(maze[8][1],' ');
	}

	@org.junit.Test
	public void die() {

		Logic game1 = new Logic(-1, 1, 1);
		char[][] maze;

		assertEquals(0,game1.makePlay("S"));	
		assertEquals(-1,game1.makePlay(""));
		maze=game1.getMaze();

		assertSame(maze[3][1],'D');
		assertSame(maze[2][1],' ');
	}

	@org.junit.Test
	public void win() { 

		Logic game1 = new Logic(-1, 1, 1);
		char[][] maze;

		String moves="DDDSSSSAAASSSWWWWSDDDSSSDDDDWWW";
		for(int i=0; i<31; i++)
			assertEquals(0,game1.makePlay(moves.substring(i, i+1)));

		assertEquals(1,game1.makePlay("D"));
		maze=game1.getMaze();
		assertSame(maze[5][9],'A');
	}

	@org.junit.Test
	public void failToWin() { 

		Logic game1 = new Logic(-1, 1, 1);
		char[][] maze;

		String moves="DDDSSSSAAASSSWWWDDDSSSDDDDWWW";
		for(int i=0; i<29; i++)
			assertEquals(0,game1.makePlay(moves.substring(i, i+1)));

		assertEquals(0,game1.makePlay("D"));
		maze=game1.getMaze();
		assertSame(maze[5][8],'A');
		assertNotSame(maze[5][9], 'A');
	}

	@org.junit.Test
	public void lose() { 

		Logic game1 = new Logic(-1, 1, 1);
		char[][] maze;

		assertEquals(0,game1.makePlay("S"));
		assertEquals(-1,game1.makePlay("S"));
		maze=game1.getMaze();
		assertSame(maze[3][1],'D');
		assertNotSame(maze[2][1], 'H');
	}
}

