package maze.test;
import static org.junit.Assert.*;
import maze.logic.Logic;
import maze.generator.Generator;

/**
 * Test class to dragon
 * @author Ana Sousa e Gabriel Candal
 *
 */
public class TestDragon {
	
	@org.junit.Test
	public void dragonStopped() {

		Logic game1 = new Logic(-1,1,1);
		char[][] maze;

		String moves="DDDSSSSAAASSSWWWDDDSSSDDDDW";

		for(int i=0; i<27; i++) {
			game1.makePlay(moves.substring(i, i+1));
			maze=game1.getMaze();
			assertSame(maze[3][1],'D');
		}
	}
	
	
	@org.junit.Test
	public void dragonSleep() {

		Logic game1 = new Logic(-1,1,1);
		char[][] maze;

		String moves="DSAW";

		for(int i=0; i<4; i++) {
			game1.makePlay(moves.substring(i, i+1));
			game1.setSleep();
			maze=game1.getMaze();
			assertSame(maze[3][1],'d');
		}
	}

	@org.junit.Test
	public void dragonRandomMove() {

		Logic game1 = new Logic(-1,1,2);
		char[][] maze;
		int[] dragonMove={3,3,4};
		Generator.setTest(dragonMove);

		game1.makePlay("D");
		maze=game1.getMaze();
		assertSame(maze[4][1],'D');

		game1.makePlay("D");
		maze=game1.getMaze();
		assertSame(maze[5][1],'D');

		game1.makePlay("D");
		maze=game1.getMaze();
		assertSame(maze[5][2],'D');
	}
	
	@org.junit.Test
	public void multipleDragons() {

		Logic game1 = new Logic(10, 1, 1);
		char[][] maze=game1.getMaze();
		int count=0;
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
			if(maze[i][j]=='D')
				count++;
			}
		}

		assertSame(count, 1);
		

		Logic game2 = new Logic(40, 2, 1);
		maze=game2.getMaze();
		count=0;
		for(int i=0; i<40; i++) {
			for(int j=0; j<40; j++) {
			if(maze[i][j]=='D')
				count++;
			}
		}
		assertSame(count, 2);
		
		Logic game3 = new Logic(40, 3, 1);
		maze=game3.getMaze();
		count=0;
		for(int i=0; i<40; i++) {
			for(int j=0; j<40; j++) {
			if(maze[i][j]=='D')
				count++;
			}
		}
		assertSame(count, 3);
	}
}
