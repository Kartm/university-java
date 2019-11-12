package lab5;

import lab5.SimpleIntList;

public class Task2 {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		SimpleIntList list = new SimpleIntList(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			SimpleIntList.appendElem(list, arr[i]);
		}

		System.out.println(list.getSize());
		System.out.println(SimpleIntList.toString(list));
		System.out.println(SimpleIntList.reverseToString(list));
		
		System.out.println(TotalValueOfPositiveValuesRecursive(list));
		System.out.println(TotalValueOfPositiveValuesIterative(list));
		
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
