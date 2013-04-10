package maze.cli;

import java.util.Scanner;

public class CLI {

	private static Scanner input=new Scanner(System.in);
	private static boolean display=true;

	/**
	 * Read input from keyboard
	 * @return string 
	 */
	public static String readInput() {

		if(display)
			System.out.print("Indique como pretende mover o heroi ou a aguia: (W/A/S/D) (V)");
		return input.next();
	}

	/**
	 * Choose maze type
	 * @return maze size
	 */
	public static int chooseMaze() {

		int size;

		if(display)
			System.out.print("Aleatorio (A) ou Pre-definido (P): ");

		if(input.next().equals("P"))
			return -1;

		if(display) {
			System.out.println();
			System.out.print("Insira a dimensão do labirinto: ");	
		}
		
		size = input.nextInt();

		return size;
	}

	/**
	 * Choose number of dragons
	 * @return number of dragons
	 */
	public static int chooseNumDragons() {
		int numDragons=1;

		if(display) {
			System.out.println();
			System.out.print("Quantos dragoes pretende: [1-3]");			
		}
		numDragons = input.nextInt();

		return numDragons;
	}

	/**
	 * Choose dragon's mode
	 * @return dragon's mode
	 */
	public static int chooseModeDragons() {

		if(display) {
			System.out.println();
			System.out.print("Dragao parado (P), c/ mov. aleatoria (A), aleatorio + dormir (D): ");	
		}

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
	
	/**
	 * Set scanner
	 * @param buffer
	 */
	public static void setScanner(String buffer) {
		input=new Scanner(buffer);
		display=false;
	}

	/**
	 * Print maze
	 * @param maze
	 */
	public static void printMaze(char[][] maze) {
		for(char[] line: maze) {			
			for(char cell: line)
				System.out.print( cell + " ");

			System.out.println();
		}
	}
}
