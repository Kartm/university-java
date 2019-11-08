package lab4;

import java.util.Scanner;

public class ArrayPack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr1 = readArray(sc);
		int[] arr2 = readArray(sc);
		sc.close();

		System.out.println("First array: ");
		showArr(arr1);

		System.out.println("Second array: ");
		showArr(arr2);

		System.out.println("Unique elements: ");
		showArr(uniqueElements(arr1, arr2));

		System.out.println("Common elements: ");
		showArr(commonElements(arr1, arr2));

		System.out.println("Appended arrays: ");
		int[] appendedArrays = appendArrays(arr1, arr2);
		showArr(appendedArrays);

		System.out.println("Minimal element in arrays: ");
		System.out.println(getMinimalElement(appendedArrays));

		System.out.println("Maximal element in arrays: ");
		System.out.println(getMaximalElement(appendedArrays));

		System.out.println("Elements less than 5 in arrays: ");
		showArr(getLessThan(appendedArrays, 5));

		System.out.println("Elements greater than 5 in arrays: ");
		showArr(getGreaterThan(appendedArrays, 5));

		System.out.println("Elements in range between 5 and 10 in arrays: ");
		showArr(getRange(appendedArrays, 5, 10));
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

	public enum ElementCompare {
		GREATER, LESS
	}

	public static int GetExtremumElement(int[] inArr, ElementCompare compare) {
		int extremum = (compare == ElementCompare.GREATER) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		for (int i = 0; i < inArr.length; i++) {
			if (compare == ElementCompare.GREATER) {
				if (inArr[i] > extremum) {
					extremum = inArr[i];
				}
			} else if (compare == ElementCompare.LESS) {
				if (inArr[i] < extremum) {
					extremum = inArr[i];
				}
			}
		}
		return extremum;
	}

	public static int getMinimalElement(int[] inArr) {
		return GetExtremumElement(inArr, ElementCompare.LESS);
	}

	public static int getMaximalElement(int[] inArr) {
		return GetExtremumElement(inArr, ElementCompare.GREATER);
	}

	private static int[] limitArray(int[] inArr, int limit, ElementCompare compare) {
		int validElementCounter = 0;
		boolean isLookingForGreater = compare == ElementCompare.GREATER;
		for (int i = 0; i < inArr.length; i++) {
			if (isLookingForGreater) {
				if (inArr[i] > limit) {
					validElementCounter++;
				}
			} else {
				if (inArr[i] < limit) {
					validElementCounter++;
				}
			}
		}

		int[] resultArr = new int[validElementCounter];
		int resultArrIndex = 0;

		for (int i = 0; i < inArr.length; i++) {
			if (isLookingForGreater) {
				if (inArr[i] > limit) {
					resultArr[resultArrIndex] = inArr[i];
					resultArrIndex++;
				}
			} else {
				if (inArr[i] < limit) {
					resultArr[resultArrIndex] = inArr[i];
					resultArrIndex++;
				}
			}
		}
		return resultArr;

	}

	public static int[] getGreaterThan(int[] inArr, int limit) {
		return limitArray(inArr, limit, ElementCompare.GREATER);
	}

	public static int[] getLessThan(int[] inArr, int limit) {
		return limitArray(inArr, limit, ElementCompare.LESS);
	}

	public static int[] getRange(int[] inArr, int lowerLimit, int upperLimit) {
		int[] inUpperRange = getLessThan(inArr, upperLimit);
		return getGreaterThan(inUpperRange, lowerLimit);
	}

	public static boolean isAnElement(int[] inArr, int what2Look4) {
		for (int i = 0; i < inArr.length; i++) {
			if (inArr[i] == what2Look4) {
				return true;
			}
		}
		return false;
	}

	public enum ArrayOperation {
		COMMON, UNIQUE
	}

	public static int[] CompareArrays(int[] a1, int[] a2, ArrayOperation operation) {
		int resultCounter = 0;
		boolean isLookingForCommon = (operation == ArrayOperation.COMMON);
		for (int i = 0; i < a1.length; i++) {
			if (isAnElement(a2, a1[i]) == isLookingForCommon) {
				resultCounter++;
			}
		}

		int[] resultArr = new int[resultCounter];
		int resultArrIndex = 0;

		for (int i = 0; i < a1.length; i++) {
			if (isAnElement(a2, a1[i]) == isLookingForCommon) {
				resultArr[resultArrIndex] = a1[i];
				resultArrIndex++;
			}
		}
		return resultArr;
	}

	public static int[] uniqueElements(int[] a1, int[] a2) {
		int[] onlyInArr1 = CompareArrays(a1, a2, ArrayOperation.UNIQUE);
		int[] onlyInArr2 = CompareArrays(a2, a1, ArrayOperation.UNIQUE);
		return appendArrays(onlyInArr1, onlyInArr2);
	}

	public static int[] commonElements(int[] a1, int[] a2) {
		return CompareArrays(a1, a2, ArrayOperation.COMMON);
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
