package maze.test;
import static org.junit.Assert.*;
import maze.logic.Logic;

/**
 * Test class for eagle
 * @author Sousa
 *
 */
public class TestEagle {

	
	@org.junit.Test
	public void eagle() {
		
		Logic game1 = new Logic(-1, 1, 1);

		assertEquals(0,game1.makePlay("V"));
		char[][] maze=game1.getMaze();
		assertSame(maze[1][1],'H');
		assertSame(maze[2][1],'G');
		
		String moves="WWWWWWWWWWWWWW"; //manter o heroi na mesma posicao a espera da aguia
		
		for(int i=0; i<14; i++)
			assertEquals(0,game1.makePlay(moves.substring(i, i+1)));
		maze=game1.getMaze();
		assertSame(maze[1][1],'A');

		//verificar se a aguia se move duas vezes
		assertEquals(0,game1.makePlay("V"));
		maze=game1.getMaze();
		assertSame(maze[1][1],'A');
		assertSame(maze[2][1],' ');
		assertSame(maze[8][1],' ');
	}
	
	
}
