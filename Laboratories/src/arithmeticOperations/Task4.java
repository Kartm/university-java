package arithmeticOperations;

import java.util.Scanner;

public class Task4 {
	public static void main(String[] args) {
		// TODO auto-generated method stub
		int sum = 0;
		int elemNo = 0;
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			String token = sc.next();
			if(token.equals("^Z")) {
				break;
			}
			try {
				int currentNumber = Integer.parseInt(token);
//				System.out.print("number: " + currentNumber + "\n");
				sum = sum + currentNumber;
				elemNo=elemNo+ 1;
			}
			catch (Exception e) {
				System.out.println("This is not a number: " + token);
			}
			
		}
		if(elemNo == 0) {
			System.err.print("No data available\n");
			System.exit(1);
		}
		sc.close();
		System.out.print("the sum of all integers");
		System.out.print(" is " + sum + "\n");
		System.exit(0);
	}
}
