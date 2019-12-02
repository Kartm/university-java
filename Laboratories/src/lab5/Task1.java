package lab5;

import java.util.Arrays;
import java.util.Scanner;

import lab4.ArrayPack;
import java.math.BigInteger;

public class Task1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// MIN and MAX values, near 0, compare the methods, use series of testing (n, n+1 etc), 
		
		System.out.print("Enter n for factorials: ");
		BigInteger n = BigInteger.valueOf(sc.nextInt());

		System.out.print("Enter a and b for GCD: ");
		BigInteger a = BigInteger.valueOf(sc.nextInt());
		BigInteger b = BigInteger.valueOf(sc.nextInt());
		System.out.println("Enter the integer array in the following way:");
		System.out.println("<size> <element1> <element2> ...etc");
		BigInteger[] arr = ArrayPack.readBigIntegerArray(sc);

		System.out.println("Iterative factorial of " + n + ": ");
		System.out.println(FactorialIterative(n));

		System.out.println("Recursive factorial of " + n + ": ");
		System.out.println(FactorialRecursive(n));

		System.out.println("Iterative GCD of " + a + " and " + b + ": ");
		System.out.println(GCDIterative(a, b));

		System.out.println("Recursive GCD of " + a + " and " + b + ": ");
		System.out.println(GCDRecursive(a, b));

		System.out.println("Recursive number of positive values: ");
		System.out.println(NumberOfPositiveValuesRecursive(arr));

		System.out.println("Iterative number of positive values: ");
		System.out.println(NumberOfPositiveValuesIterative(arr));

		System.out.println("Recursive total of positive values: ");
		System.out.println(TotalOfPositiveValuesRecursive(arr));

		System.out.println("Iterative total of positive values: ");
		System.out.println(TotalOfPositiveValuesIterative(arr));

		sc.close();
	}

	public static BigInteger FactorialIterative(BigInteger n) {
		if (n.compareTo(BigInteger.valueOf(0)) < 0) {
			System.err.println("N cannot be negative");
			return BigInteger.valueOf(-1);
		} else if (n.compareTo(BigInteger.valueOf(0)) == 0) {
			return BigInteger.valueOf(1);
		}
		BigInteger result = BigInteger.valueOf(1);
		for (int i = 1; n.compareTo(BigInteger.valueOf(i)) >= 0; i++) {
			result = result.multiply(BigInteger.valueOf(i));
		}
		return result;
	}

	public static BigInteger FactorialRecursive(BigInteger n) {
		if (n.compareTo(BigInteger.valueOf(0)) < 0) {
			System.err.println("N cannot be negative");
			return BigInteger.valueOf(-1);
		}
		if (n.compareTo(BigInteger.valueOf(1)) >= 0) {
			return n.multiply(
					FactorialRecursive(n.add(BigInteger.valueOf(-1))));
		} else {
			return BigInteger.valueOf(1);
		}
	}

	public static BigInteger GCDRecursive(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.valueOf(0)) == false) {
			return GCDRecursive(b, a.mod(b));
		}
		return a;
	}

	public static BigInteger GCDIterative(BigInteger a, BigInteger b) {
		a = a.abs();
		b = b.abs();
		BigInteger number = BigInteger.valueOf(1);
		BigInteger GCD = BigInteger.valueOf(1);
		
		while (number.compareTo(a) <= 0 && number.compareTo(b) <= 0) {
			if (a.mod(number).equals(BigInteger.valueOf(0))
					&& b.mod(number).equals(BigInteger.valueOf(0))) {
				GCD = number;
			}
			number = number.add(BigInteger.valueOf(1));
		}
		return GCD;
	}

	public static BigInteger NumberOfPositiveValuesRecursive(BigInteger[] arr) {
		BigInteger counter = BigInteger.valueOf(0);
		if(arr.length > 0) {
			BigInteger[] newArr = Arrays.copyOfRange(arr, 1, arr.length);
			if(arr[0].compareTo(BigInteger.valueOf(0)) > 0) {
				counter = counter.add(BigInteger.valueOf(1));
			}
			counter = counter.add(NumberOfPositiveValuesRecursive(newArr));
		}
		
		return counter;
	}

	public static BigInteger NumberOfPositiveValuesIterative(BigInteger[] arr) {
		
		BigInteger counter = BigInteger.valueOf(0);

				
				for (int i = 0; i < arr.length; i++) {
					if (arr[i].compareTo(BigInteger.valueOf(0)) == 1) { // > 0
						counter = counter.add(BigInteger.valueOf(1));
					}
				}
		return counter;
	}

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
