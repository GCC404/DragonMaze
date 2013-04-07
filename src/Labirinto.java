
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import maze.logic.*;

public class Labirinto {

	static char[][]	labirinto;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectInputStream is = null;
		is = new ObjectInputStream(new FileInputStream("file.dat"));
		Logic game2 = (Logic) is.readObject();
		game2.play();
		
	}

}
