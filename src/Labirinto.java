import maze.logic.*;

// Gerar labirinto
// Quadrado canto superior esquerdo
// Aparecer A quando apanhas a espada


public class Labirinto {

	static char[][]	labirinto;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Logic game1 = new Logic();
		int response=0;
		
		game1.printConsole();
		
		while(response==0) {
			
			response=game1.play();
			game1.printConsole();
			
		}
		
		if(response==1)
			System.out.println("GANHASTE MÁNINHO!");
		else System.out.println("Já foste.");
		
	}

}
