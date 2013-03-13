package maze.cli;

import java.util.Scanner;

public class CLI {
	
	private static Scanner input=new Scanner(System.in);
	
	public static String lerInput() {
		
		System.out.print("Indique como pretende mover o heroi ou a aguia: (W/A/S/D) (V)");
		return input.next();
	}

	public static int chooseMaze() {

		int size;

		System.out.print("Aleatorio (A) ou Pre-definido (P): ");

		if(input.next().equals("P")) 
			return -1;

		System.out.println();
		System.out.print("Insira a dimensão do labirinto: ");	
		size = input.nextInt();
		return size;
	}

	
	public static int chooseDragon1() {
		
		int numDragons=1;
		
		System.out.println();
		System.out.print("Quantos dragoes pretende: [1-3]");	
		numDragons = input.nextInt();
		
		return numDragons;
	}
	
	public static int chooseDragon2() {
		
		System.out.println();
		System.out.print("Dragao parado (P), c/ mov. aleatoria (A), aleatorio + dormir (D): ");	
		String choice = input.next();
		
		switch(choice) {
		case "P":
			return 1;
		case "A":
			return 2;
		case "D":
			return 3;
		default:
				
		}
		return 3;
	}
	
	public static void setScanner(String buffer) {
		input=new Scanner(buffer);
	}
}
