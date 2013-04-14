package maze.cli;

import java.util.Scanner;

import maze.logic.Logic;

public class CLI {

	private static Scanner input=new Scanner(System.in);
	private static boolean display=true;

	public static void main(String[] args) {		

		int size;
		do {
			size=chooseMaze();
		} while(size==-2);
		
		Logic game;
		if(size!=-1)
			game = new Logic(size,chooseNumDragons(), chooseModeDragons());
		else game=new Logic(size,0,chooseModeDragons());
		
		play(game);
	}
	
	/**
	 * General gameplay cycle.
	 * @param logic
	 */
	public static void play(Logic logic) {  

		int response=0;

		printMaze(logic);

		while(response==0) {

			response=logic.makePlay(readInput());
			printMaze(logic);
		}

		if(response==1)
			System.out.println("You won!");
		else System.out.println("You lose..");
	}
	
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
	 * @return -1(if default), maze size(if random), -2(if input isn't valid)
	 */
	public static int chooseMaze() {

		String in;
		
		if(display)
			System.out.print("Aleatorio (A) ou Pre-definido (P): ");

		in=input.next();
		
		if(in.equals("P"))
			return -1;
		else if(in.equals("A"))
			return chooseMazeSize();
		else 
			return -2;
	}


	/**
	 * Choose maze size for random labyrinth
	 * @return maze size
	 */
	private static int chooseMazeSize() {
		
		int size=0;
		do {   
			if(display) {
				System.out.println();
				System.out.print("Insira a dimensão do labirinto: ");	
			}
			
			try {
				size= Integer.parseInt(input.next());
			}
			catch(NumberFormatException e) {
				size = 0;
			}
		} while (size<5 || size>50);
		
		return size;
	}

	/**
	 * Choose number of dragons
	 * @return number of dragons
	 */
	public static int chooseNumDragons() {
		int numDragons=0;

		do {   
			if(display) {
				System.out.println();
				System.out.print("Quantos dragoes pretende: [1-3]");
			}
			
			try {
				numDragons= Integer.parseInt(input.next());
			}
			catch(NumberFormatException e) {
				numDragons = 0;
			}
			
		} while (numDragons<1 || numDragons>3);
		
		return numDragons;
	}
	
	/**
	 * Choose dragon's mode
	 * @return dragon's mode
	 */
	public static int chooseModeDragons() {

		boolean valid=false;

		while (!valid) {
			
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
		}
		return 1;
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
	public static void printMaze(Logic logic) {
		char[][] mazec=logic.getMaze();
		for(char[] line: mazec) {			
			for(char cell: line)
				System.out.print( cell + " ");

			System.out.println();
		}
	}
}
