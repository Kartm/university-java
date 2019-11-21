package lab5;

import java.util.Arrays;
import java.util.Scanner;

import lab4.ArrayPack;
import java.math.BigInteger;

public class Task1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//
//		System.out.print("Enter n for factorials: ");
//		BigInteger n = BigInteger.valueOf(sc.nextInt());
//
//		System.out.print("Enter a and b for GCD: ");
//		BigInteger a = BigInteger.valueOf(sc.nextInt());
//		BigInteger b = BigInteger.valueOf(sc.nextInt());
		System.out.println("Enter the integer array in the following way:");
		System.out.println("<size> <element1> <element2> ...etc");
		BigInteger[] arr = ArrayPack.readBigIntegerArray(sc);

//		System.out.println("Iterative factorial of " + n + ": ");
//		System.out.println(FactorialIterative(n));
//
//		System.out.println("Recursive factorial of " + n + ": ");
//		System.out.println(FactorialRecursive(n));
//
//		System.out.println("Iterative GCD of " + a + " and " + b + ": ");
//		System.out.println(GCDIterative(a, b));
//
//		System.out.println("Recursive GCD of " + a + " and " + b + ": ");
//		System.out.println(GCDRecursive(a, b));
//
//		System.out.println("Recursive number of positive values: ");
//		System.out.println(NumberOfPositiveValuesRecursive(arr));
//
//		System.out.println("Iterative number of positive values: ");
//		System.out.println(NumberOfPositiveValuesIterative(arr));

		System.out.println("Recursive total of positive values: ");
		System.out.println(TotalOfPositiveValuesRecursive(arr));

		System.out.println("Iterative total of positive values: ");
		System.out.println(TotalOfPositiveValuesIterative(arr));

		sc.close();
	}

//	public static BigInteger FactorialIterative(BigInteger n) {
//		if (n <= 0) {
//			return -1;
//		}
//		int result = 1;
//		for (int i = 1; i <= n; i++) {
//			result *= i;
//		}
//		return result;
//	}
//
//	public static BigInteger FactorialRecursive(BigInteger n) {
//		if (n >= 1) {
//			return n * FactorialRecursive(n - 1);
//		} else {
//			return 1;
//		}
//	}
//
//	public static BigInteger GCDRecursive(BigInteger a, BigInteger b) {
//		if (b != 0) {
//			return GCDRecursive(b, a % b);
//		}
//		return a;
//	}
//
//	public static BigInteger GCDIterative(BigInteger a, BigInteger b) {
//		int number = 1;
//		int GCD = 1;
//		while (number <= a && number <= b) {
//			if (a % number == 0 && b % number == 0) {
//				GCD = number;
//			}
//			number++;
//		}
//		return GCD;
//	}
//
//	public static BigInteger NumberOfPositiveValuesRecursive(BigInteger[] arr) {
//		int counter = 0;
//		if (arr.length > 0) {
//			int[] newArr = Arrays.copyOfRange(arr, 1, arr.length);
//			if (arr[0] > 0) {
//				counter++;
//			}
//			counter += NumberOfPositiveValuesRecursive(newArr);
//		}
//		return counter;
//	}
//
//	public static BigInteger NumberOfPositiveValuesIterative(BigInteger[] arr) {
//		int counter = 0;
//		for (int i = 0; i < arr.length; i++) {
//			if (arr[i] > 0) {
//				counter++;
//			}
//		}
//		return counter;
//	}

	public static BigInteger TotalOfPositiveValuesRecursive(BigInteger[] arr) {
		BigInteger total = BigInteger.valueOf(0);
		if (arr.length > 0) {
			BigInteger[] newArr = Arrays.copyOfRange(arr, 1, arr.length);
			if (arr[0].compareTo(BigInteger.valueOf(0)) == 1) { // > 0
				total = total.add(arr[0]);
			}
			total = total.add(TotalOfPositiveValuesRecursive(newArr));
		}
		return total;
	}

	public static BigInteger TotalOfPositiveValuesIterative(BigInteger[] arr) {
		BigInteger total = BigInteger.valueOf(0);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].compareTo(BigInteger.valueOf(0)) == 1) { // > 0
				total = total.add(arr[i]); 
			}
		}
		return total;
	}
}
