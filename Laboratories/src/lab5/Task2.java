package lab5;

import lab4.ArrayPack;
import lab5.SimpleBigIntegerList;

import java.math.BigInteger;
import java.util.Scanner;

public class Task2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the integer array in the following way:");
		System.out.println("<size> <element1> <element2> ...etc");
		BigInteger[] arr = ArrayPack.readBigIntegerArray(sc);

		if (arr.length > 0) {
			SimpleBigIntegerList list = new SimpleBigIntegerList(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				SimpleBigIntegerList.appendElem(list, arr[i]);
			}

			System.out.println("Size of the list: " + list.getSize());
			System.out.println(SimpleBigIntegerList.toString(list));
			System.out.println(SimpleBigIntegerList.reverseToString(list));

			System.out.println("Recursive total of positive values: "
					+ TotalValueOfPositiveValuesRecursive(list));
			System.out.println("Iterative total of positive values: "
					+ TotalValueOfPositiveValuesIterative(list));
		}
		sc.close();
	}

	public static BigInteger TotalValueOfPositiveValuesRecursive(
			SimpleBigIntegerList start) {
		BigInteger sum = BigInteger.valueOf(0);
		if (start != null) {
			sum = sum.add((start.value.compareTo(BigInteger.valueOf(0)) > 0) ? start.value : BigInteger.valueOf(0));
			sum = sum.add(TotalValueOfPositiveValuesRecursive(start.next));
		}
		return sum;
	}

	public static BigInteger TotalValueOfPositiveValuesIterative(
			SimpleBigIntegerList currElement) {
		BigInteger sum = BigInteger.valueOf(0);
		while (currElement != null) {
			BigInteger value = currElement.value;
			if (value.compareTo(BigInteger.valueOf(0)) > 0) {
				sum = sum.add(value);
			}

			currElement = currElement.next;
		}
		return sum;
	}
}
