package start;

public class Decimal2BinaryConversion2_splitted {
	public static void main(String[] args) {
		int[] testData= {7, 10, 15, 12345};
		for(int k = 0; k < testData.length; k++) {
			String result = convertToBinary(testData[k]);
			System.out.println(testData[k] + " ==> " + result);
		}
	}
	public static String convertToBinary(int decimal) {
		String result = "";
		while(decimal > 0) {
			int remainder = decimal % 2;
			result = remainder + result;
			decimal = decimal / 2;
		}
		return result;
	}
}
