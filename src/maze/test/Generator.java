package maze.test;

import java.util.Random;

public class Generator {
	private static int []test;
	private static int i;
	private static Random generator=new Random();
	
	public static void setTest(int [] ttest) {
		test=ttest;
		i=0;
	}
	
	public static int nextInt(int limit) {
		if(test.length==0)
			return generator.nextInt(limit);
		
		i++;
		return test[i-1];
	}
	
	/*
	 * 		int a = generator.nextInt(5);

		switch(a) {
		case 0:
			return "";
		case 1:S
			return "W";
		case 2:
			return "A";
		case 3:
			return "S";
		case 4:
			return "D";
		}
	 */
}
