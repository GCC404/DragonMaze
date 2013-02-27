package maze.cli;

import java.util.Scanner;

public class CLI {
	
	private static Scanner input=new Scanner(System.in);
	
	public static String lerInput() {
		
		System.out.print("Indique como pretende mover o heroi: W/A/S/D ");

		return input.next();
	}
	
	public static int chooseMaze() {
		int size;
		
		System.out.print("Aleatorio (A) ou Pre-definido (P): ");
		
		if(input.next().equals("P")) {
			//input.close();
			
			return -1;
		}
			
		
		System.out.println();
		System.out.print("Insira a dimensão do labirinto: ");	
		
		size = input.nextInt();
		
		//input.close();
		
		return size;
	}
}
