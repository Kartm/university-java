package lab5;

import lab5.SimpleIntList;

import java.util.Scanner;

import lab4.ArrayPack;

public class Task2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the integer array in the following way:");
		System.out.println("<size> <element1> <element2> ...etc");
		int[] arr = ArrayPack.readArray(sc);
		
		if(arr.length > 0) {
			SimpleIntList list = new SimpleIntList(arr[0]);
			for (int i = 1; i < arr.length; i++) {
				SimpleIntList.appendElem(list, arr[i]);
			}

			System.out.println("Size of the list: " + list.getSize());
			System.out.println(SimpleIntList.toString(list));
			System.out.println(SimpleIntList.reverseToString(list));
			
			System.out.println("Recursive total of positive values: " + TotalValueOfPositiveValuesRecursive(list));
			System.out.println("Iterative total of positive values: " + TotalValueOfPositiveValuesIterative(list));
		}
		sc.close();
	}

	public static int TotalValueOfPositiveValuesRecursive(SimpleIntList start) {
		int sum = 0;
		if(start != null) {
			sum += start.value + TotalValueOfPositiveValuesRecursive(start.next);
		}
		return sum;
	}

	public static int TotalValueOfPositiveValuesIterative(SimpleIntList currElement) {
		int sum = 0;
		while(currElement != null) {
			int value = currElement.value;
			if (value > 0) {
				sum += value;
			}

			currElement = currElement.next;
		}
		return sum;
	}
}
