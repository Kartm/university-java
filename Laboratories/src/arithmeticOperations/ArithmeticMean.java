package arithmeticOperations;

import java.util.Scanner;

public class ArithmeticMean {
	public static void main(String[] args) {
		// TODO auto-generated method stub
		int sum = 0;
		int elemNo = 0;
		int currElem;
		double result;
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			currElem = sc.nextInt();
			sum = sum + currElem;
			elemNo=elemNo+ 1;
		}
		if(elemNo == 0) {
			System.err.print("No data available\n");
			System.exit(1);
		}
		sc.close();
		result = (double) sum / elemNo;
		System.out.print("the sum of " + elemNo);
		System.out.print(" is " + result + "\n");
		System.exit(0);
	}
}
