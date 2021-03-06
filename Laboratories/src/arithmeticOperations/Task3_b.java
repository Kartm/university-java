package arithmeticOperations;

import java.util.Scanner;

public class Task3_b {
	public static void main(String[] args) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int elemCount = 0;
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int currElem = sc.nextInt();
			if(currElem < min) {
				min = currElem;
			} 
			if (currElem > max) {
				max = currElem;
			}
			elemCount++;
		}
		if(elemCount == 0) {
			System.err.print("No data available\n");
			System.exit(1);
		}
		sc.close();
		System.out.print("the mid-range value");
		System.out.print(" is " + (double)(min+max)/2 + "\n");
		System.exit(0);
	}
}
