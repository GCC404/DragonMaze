package maze.test;
import static org.junit.Assert.*;
import maze.logic.*;
import maze.cli.*;

/**
 * Test class for eagle
 * @author Sousa
 *
 */
public class TestEagle {

	
	@org.junit.Test
	public void eagle() {
		
		CLI.setScanner("P P 1");
		Logic game1 = new Logic();

		CLI.setScanner("V");
		assertEquals(0,game1.makePlay());
		char[][] maze=game1.getMaze();
		assertSame(maze[1][1],'H');
		assertSame(maze[2][1],'G');
		

		CLI.setScanner("W W W W W W W W W W W W W W"); //manter o heroi na mesma posicao a espera da aguia
		for(int i=0; i<14; i++)
			assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][1],'A');

		CLI.setScanner("V"); //verificar se a aguia se move duas vezes
		assertEquals(0,game1.makePlay());
		maze=game1.getMaze();
		assertSame(maze[1][1],'A');
		assertSame(maze[2][1],' ');
		assertSame(maze[8][1],' ');
	}
	
	
}
