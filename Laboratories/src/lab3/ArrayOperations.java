package lab3;

public class ArrayOperations {
	public static void main(String[] args) {
		int[] testArray = { 7, 12, 1, 0, 5, 8 };
		int[] emptyArray = {};
		int[] anotherArray;
		printArray(testArray, 3);
		printArray(emptyArray, 3);
		printArray(null, 3);
		anotherArray = createArray(8);
		printArray(anotherArray, 8);
		System.out.println("Test average: " + findAverage(testArray));
		System.out.println("Empty average: " + findAverage(emptyArray));
		anotherArray = selectGreaterThan(testArray, 7);
		System.out.print(">7: ");
		printArray(anotherArray, 5);
		anotherArray = selectGreaterThan(testArray, 77);
		System.out.print(">77: ");
		printArray(anotherArray, 5);
	}
	
	public static void printArray(int[] arr, int no) {
		if(arr != null && arr.length > 0) {
			String seperator = "\t";
			for (int i = arr.length - 1; i >= 0; i--) {
				if((i + 0) % no != 0) {
					seperator = "  -  ";
				} else {
					seperator = "\n";
				}
				
				System.out.print(arr[i] + seperator);
			}
			if(seperator.equals("\t")) {
				System.out.print("\n");
			}
		} else {
			System.out.print("No data to print\n");
		}
	}

	public static int[] fillArray(int[] arrayToFill) {
		for (int i = 0; i < arrayToFill.length; i++) {
			arrayToFill[i] = 11 + i * 2;
		}
		return arrayToFill;
	}

	public static int[] createArray(int size) {
		int[] newArr = new int[size];
		newArr = fillArray(newArr);
		return newArr;
	}

	public static double findAverage(int[] arr) {
		double sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		if(arr.length > 0) {
			return (double) (sum / arr.length);
		} else {
			return 0;
		}
	}

	public static int[] selectGreaterThan(int[] arr, int limit) {
		int greaterCounter = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > limit) {
				greaterCounter++;
			}
		}
		
		int[] newArr = new int [greaterCounter];
		int newIndex = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > limit) {
				newArr[newIndex] = arr[i];
				newIndex++;
			}
		}
		return newArr;
	}

}
