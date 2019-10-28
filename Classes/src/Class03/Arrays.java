package Class03;

public class Arrays {
	public static void main(String[] args) {
		int[][] testArr = {{1, 4, 17}, {7, 12}, {3, 15, 19, 22}};
		System.out.println(isAscending(testArr[0]));
		System.out.println(isAscending(testArr[1]));
		System.out.println(isAscending(testArr[2]));
		System.out.println("");
		showMax(testArr);
		System.out.println(getAverageValue(testArr));
		showAboveAverage(testArr);
		System.out.println(sumNaturalsUpToLimit(0));
		System.out.println(sumNaturalsUpToLimit(2));
		System.out.println(sumNaturalsUpToLimit(4));
	}
	
	public static boolean isAscending(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			if(arr[i] > arr[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	public static void showMax(int[][] arr) {
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		System.out.println(max);
	}
	
	public static double getAverageValue(int[][] arr) {
		int counter = 0;
		double sum = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				counter++;
				sum += arr[i][j];
			}
		}
		return((double)(sum / counter));
	}
	
	public static void showAboveAverage(int[][] arr) {
		double average = getAverageValue(arr);
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if((double)average < arr[i][j]) {
					System.out.println(arr[i][j] + "\t");
				}
			}
		}
		System.out.println("");
	}
	
	 /* 7 - description unclear
	  * 
	  * Write a pseudo-code that computes how many the numbers we have to read from input so that
their sum is >= then N, where N is an argument of the method).
	 */
	
	public static int sumNaturalsUpToLimit(int limit) {
		int sum = 0;
		if(limit > 1) {
			for(int i = 1; i < limit; i++) {
				sum += i;
			}
		} else {
			return 0;
		}
		return sum;
	}
	
}
