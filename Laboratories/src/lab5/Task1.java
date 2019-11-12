package lab5;

import java.util.Arrays;

public class Task1 {

	public static void main(String[] args) {
		System.out.println("Iterative factorial of 5: ");
		System.out.println(FactorialIterative(5));

		System.out.println("Recursive factorial of 5: ");
		System.out.println(FactorialRecursive(5));

		System.out.println("Iterative GCD of 55 and 10: ");
		System.out.println(GCDIterative(55, 10));
		
		System.out.println("Recursive GCD of 55 and 10: ");
		System.out.println(GCDRecursive(55, 10));
		
		int[] arr = {-10, 100, -110, 10, -5, 1};

		System.out.println("Recursive number of positive values: ");
		System.out.println(NumberOfPositiveValuesRecursive(arr));
		
		System.out.println("Iterative number of positive values: ");
		System.out.println(NumberOfPositiveValuesIterative(arr));
		
		System.out.println("Recursive total of positive values: ");
		System.out.println(TotalOfPositiveValuesRecursive(arr));
		
		System.out.println("Iterative total of positive values: ");
		System.out.println(TotalOfPositiveValuesIterative(arr));
		
	}

	public static int FactorialIterative(int n) {
		if(n <= 0) {
			return -1;
		}
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static int FactorialRecursive(int n) {
		if (n >= 1) {
			return n * FactorialRecursive(n - 1);
		} else {
			return 1;
		}
	}

	public static int GCDRecursive(int a, int b) {
		if (b != 0) {
			return GCDRecursive(b, a % b);
		}
		return a;
	}

	public static int GCDIterative(int a, int b) {
		int number = 1;
		int GCD = 1;
		while (number <= a && number <= b) {
			if (a % number == 0 && b % number == 0) {
				GCD = number;
			}
			number++;
		}
		return GCD;
	}

	public static int NumberOfPositiveValuesRecursive(int[] arr) {
		int counter = 0;
		if (arr.length > 0) {
			int[] newArr = Arrays.copyOfRange(arr, 1, arr.length);
			if(arr[0] > 0) {
				counter++;
			}
			counter += NumberOfPositiveValuesRecursive(newArr);
		}
		return counter;
	}

	public static int NumberOfPositiveValuesIterative(int[] arr) {
		int counter = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > 0) {
				counter++;
			}
		}
		return counter;
	}

	public static int TotalOfPositiveValuesRecursive(int[] arr) {
		int total = 0;
		if (arr.length > 0) {
			int[] newArr = Arrays.copyOfRange(arr, 1, arr.length);
			if(arr[0] > 0) {
				total += arr[0];
			}
			total += TotalOfPositiveValuesRecursive(newArr);
		}
		return total;
	}

	public static int TotalOfPositiveValuesIterative(int[] arr) {
		int total = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > 0) {
				total += arr[i];
			}
		}
		return total;
	}
}
