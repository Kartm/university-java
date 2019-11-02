package lab4;

import java.util.Scanner;
import java.util.Arrays;

public class ArrayPack {
	public static void main(String[] args) {
	}
	
	public static int[] readArray(Scanner sc) {
		int arrSize = 0;
		
		if(sc.hasNextInt()) {
			arrSize = sc.nextInt();
		}
		
		int[] resultArr = new int[arrSize];
		int inputIndex = 0;
		while(inputIndex < arrSize) {
			int value = sc.nextInt();
			resultArr[inputIndex] = value;
			inputIndex++;
		}
		return resultArr;
	}
	
	public static int[] appendArrays(int[] arr1, int[] arr2) {
		int[] resultArr = new int[arr1.length + arr2.length];
		for(int i = 0; i < arr1.length; i++) {
			resultArr[i] = arr1[i];
		}
		for(int i = 0; i < arr2.length; i++) {
			resultArr[arr1.length + i] = arr2[i];
		}
		return resultArr;
	}
	
	public int getMaximalElement(int[] inArr) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < inArr.length; i++) {
			if(inArr[i] > max) {
				max = inArr[i];
			}
		}
		return max;
	}
	
	public int getMinimalElement(int[] inArr) {
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < inArr.length; i++) {
			if(inArr[i] < min) {
				min = inArr[i];
			}
		}
		return min;
	}
	
	public int[] getGreaterThan(int[] inArr, int limit) {
		
	}
	
public int[] getLessThan(int[] inArr, int limit) {
		
	}

public int[] getRange(int[] inArr, int lowerLimit, int upperLimit) {
	
}

public static boolean isAnElement(int[] inArr, int what2Look4) {
	for(int i = 0; i < inArr.length; i++) {
		if(inArr[i] == what2Look4) {
			return true;
		}
	}
	return false;
}

public static int[] uniqueElements(int[] a1, int[] a2) {
	int uniqueCounter = 0;
	for(int i = 0; i < a1.length; i++) {
		if(isAnElement(a2, a1[i]) == false) {
			uniqueCounter++;
		}
	}
	
	int[] resultArr = new int[uniqueCounter];
	int resultArrIndex = 0;
	
	for(int i = 0; i < a1.length; i++) {
		if(isAnElement(a2, a1[i]) == false) {
			resultArr[resultArrIndex] = a1[i];
			resultArrIndex++;
		}
	}
	return resultArr;
}

public static int[] commonElements(int[] a1, int[] a2) {
	
}
	
	public static void showArr(int[] arr) {
		if(arr != null && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + "\t");
			}
		} else {
			System.out.print("No data to print");
		}
		System.out.print("\n");
	}
}
