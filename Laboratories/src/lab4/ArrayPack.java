package lab4;

import java.util.Scanner;

public class ArrayPack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr1 = readArray(sc);
		int[] arr2 = readArray(sc);
		sc.close();
		int[] arr = appendArrays(arr1, arr2);
		
		System.out.println("Appended arrays:");
		showArr(arr);
		
		System.out.println("Minimal value:");
		System.out.println(getMinimalElement(arr));
		
		System.out.println("Maximal value:");
		System.out.println(getMaximalElement(arr));
		
		System.out.println("Elements less than 5:");
		showArr(getLessThan(arr, 5));
		
		System.out.println("Elements greater than 5:");
		showArr(getGreaterThan(arr, 5));
		
		System.out.println("Elements limited by 1 and 6:");
		showArr(getRange(arr, 1, 6));
		
		System.out.println("Is 5 an element of the arrays?:");
		System.out.println(isAnElement(arr, 5));
		
		System.out.println("Unique elements of arr1 and arr2:");
		showArr(uniqueElements(arr1, arr2));
		
		System.out.println("Common elements of arr1 and arr2:");
		showArr(commonElements(arr1, arr2));
	}

	public static int[] readArray(Scanner sc) {
		int arrSize = 0;

		if (sc.hasNextInt()) {
			arrSize = sc.nextInt();
		}

		int[] resultArr = new int[arrSize];
		int inputIndex = 0;
		while (inputIndex < arrSize) {
			int value = sc.nextInt();
			resultArr[inputIndex] = value;
			inputIndex++;
		}
		return resultArr;
	}

	public static int[] appendArrays(int[] arr1, int[] arr2) {
		int[] resultArr = new int[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length; i++) {
			resultArr[i] = arr1[i];
		}
		for (int i = 0; i < arr2.length; i++) {
			resultArr[arr1.length + i] = arr2[i];
		}
		return resultArr;
	}

	public enum ArrayElementComparator {
		GREATER, LESS
	}

	private static int getExtrema(int[] inArr, ArrayElementComparator comp) {
		int result = (comp.equals(ArrayElementComparator.GREATER) ? Integer.MIN_VALUE : Integer.MAX_VALUE);
		for (int i = 0; i < inArr.length; i++) {
			switch(comp) {
			case GREATER:
				if (inArr[i] > result) {
					result = inArr[i];
				}
				break;
			case LESS:
				if (inArr[i] < result) {
					result = inArr[i];
				}
				break;
			default:
				break;
			}
		}
		return result;
	}

	public static int getMaximalElement(int[] inArr) {
		return getExtrema(inArr, ArrayElementComparator.GREATER);
	}

	public static int getMinimalElement(int[] inArr) {
		return getExtrema(inArr, ArrayElementComparator.LESS);
	}

	private static int[] getComparedThan(int[] inArr, int limit, ArrayElementComparator comp) {
		int validCounter = 0;
		for (int i = 0; i < inArr.length; i++) {
			switch (comp) {
			case GREATER:
				if (inArr[i] > limit) {
					validCounter++;
				}
				break;
			case LESS:
				if (inArr[i] < limit) {
					validCounter++;
				}
				break;
			default:
				return new int[0];
			}
		}

		int[] resultArr = new int[validCounter];
		int validIndex = 0;
		for (int i = 0; i < inArr.length; i++) {
			switch (comp) {
			case GREATER:
				if (inArr[i] > limit) {
					resultArr[validIndex] = inArr[i];
					validIndex++;
				}
				
				break;
			case LESS:
				if (inArr[i] < limit) {
					resultArr[validIndex] = inArr[i];
					validIndex++;
				}
				
				break;
			default:
				return new int[0];
			}
		}
		return resultArr;
	}

	public static int[] getGreaterThan(int[] inArr, int limit) {
		return getComparedThan(inArr, limit, ArrayElementComparator.GREATER);
	}

	public static int[] getLessThan(int[] inArr, int limit) {
		return getComparedThan(inArr, limit, ArrayElementComparator.LESS);
	}

	public static int[] getRange(int[] inArr, int lowerLimit, int upperLimit) {
		int[] lowerResult = getGreaterThan(inArr, lowerLimit);
		return getLessThan(lowerResult, upperLimit);
	}

	public static boolean isAnElement(int[] inArr, int what2Look4) {
		for (int i = 0; i < inArr.length; i++) {
			if (inArr[i] == what2Look4) {
				return true;
			}
		}
		return false;
	}

	public enum ElementComparator {
		UNIQUE, COMMON
	}

	private static int[] getComparedElements(int[] a1, int[] a2, ElementComparator comp) {
		boolean isUnique = true;
		if (comp == ElementComparator.COMMON) {
			isUnique = false;
		}

		int validCounter = 0;
		for (int i = 0; i < a1.length; i++) {
			if (isAnElement(a2, a1[i]) == isUnique) {
				validCounter++;
			}
		}

		int[] resultArr = new int[validCounter];
		int resultArrIndex = 0;

		for (int i = 0; i < a1.length; i++) {
			if (isAnElement(a2, a1[i]) == isUnique) {
				resultArr[resultArrIndex] = a1[i];
				resultArrIndex++;
			}
		}
		return resultArr;
	}

	public static int[] uniqueElements(int[] a1, int[] a2) {
		return getComparedElements(a1, a2, ElementComparator.UNIQUE);
	}

	public static int[] commonElements(int[] a1, int[] a2) {
		return getComparedElements(a1, a2, ElementComparator.COMMON);
	}

	public static void showArr(int[] arr) {
		if (arr != null && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + "\t");
			}
		} else {
			System.out.print("No data to print");
		}
		System.out.print("\n");
	}
}
