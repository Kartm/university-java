package lab8;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryNumber {
	private char[] binary = new char[32];

	public BinaryNumber(int val) {
		this.setBinary(BinaryNumber.fromInt(val).getBinary());
	}

	public BinaryNumber(BinaryNumber bn) {
		this.binary = bn.getBinary();
	}

	public BinaryNumber(char[] bn) {
		this.setBinary(bn);
	}

	public BinaryNumber(String token) {
		// todo verify correctness
		char[] result = new char[32];
		for (int i = result.length - 1; i >= 0; i--) {
			int invertedIndex = result.length - 1 - i; // to operate on token
			if (invertedIndex < token.length()) {
				int invertedTokenIndex = token.length() - 1 - invertedIndex;
				result[i] = token.charAt(invertedTokenIndex);
			} else {
				result[i] = Character.forDigit(0, 10);
			}
		}
		this.binary = result;
	}

	public char[] getBinary() {
		return this.binary;
	}

	public void setBinary(char[] binaryChar) {
		this.binary = binaryChar;
	}

	public String toString() {
		String res = "";
		for (char character : this.getBinary()) {
			res += String.valueOf(character);
		}
		return res;
	}

	public int toInt() {
		int sum = 0;
		char[] binary = this.getBinary();
		if (binary[0] == '0') {
			// positive number
			return binaryCharToInt(binary);
		} else {
			// binary number
			BinaryNumber bn = new BinaryNumber(binary);
			bn = BinaryNumber.getTwosComplement(bn);
			return -bn.toInt();
		}
	}

	private int binaryCharToInt(char[] bn) {
		int sum = 0;
		for (int i = binary.length - 1; i >= 0; i--) {
			int digitFromChar = (int) binary[i] - 48;
			sum += digitFromChar * (int) Math.pow(2, (binary.length - 1 - i));
		}
		return sum;
	}

	public static String toString(char[] sequence) {
		String res = "";
		for (char character : sequence) {
			res += String.valueOf(character);
		}
		return res;
	}

	public BinaryNumber calcAND(BinaryNumber other) {
		BinaryNumber newBin = new BinaryNumber(0); // empty number
		char[] result = new char[32];

		char[] thisBinary = this.getBinary();
		char[] otherBinary = other.getBinary();
		for (int i = thisBinary.length - 1; i >= 0; i--) {
			// they're both 1's
			if (thisBinary[i] - otherBinary[i] == 0 && thisBinary[i] == '1') {
				result[i] = Character.forDigit(1, 10);
			} else {
				result[i] = Character.forDigit(0, 10);
			}
		}

		newBin.setBinary(result);
		return newBin;
	}

	public BinaryNumber calcAND(int other) {
		BinaryNumber otherBinary = BinaryNumber.fromInt(other);
		return this.calcAND(otherBinary);
	}

	public static BinaryNumber calcAND(int first, int second) {
		BinaryNumber firstBinary = BinaryNumber.fromInt(first);
		BinaryNumber secondBinary = BinaryNumber.fromInt(second);
		return firstBinary.calcAND(secondBinary);
	}

	public BinaryNumber calcOR(BinaryNumber other) {
		BinaryNumber newBin = new BinaryNumber(0); // empty number
		char[] result = new char[32];

		char[] thisBinary = this.getBinary();
		char[] otherBinary = other.getBinary();
		for (int i = thisBinary.length - 1; i >= 0; i--) {
			if (thisBinary[i] - otherBinary[i] != 0) { // they're different
				result[i] = Character.forDigit(1, 10);
			} else if (thisBinary[i] == '1') { // both are 1's
				result[i] = Character.forDigit(1, 10);
			} else {
				result[i] = Character.forDigit(0, 10);
			}
		}

		newBin.setBinary(result);
		return newBin;
	}

	public BinaryNumber calcOR(int other) {
		BinaryNumber otherBinary = BinaryNumber.fromInt(other);
		return this.calcOR(otherBinary);
	}

	public static BinaryNumber calcOR(int first, int second) {
		BinaryNumber firstBinary = BinaryNumber.fromInt(first);
		BinaryNumber secondBinary = BinaryNumber.fromInt(second);
		return firstBinary.calcOR(secondBinary);
	}

	public BinaryNumber calcXOR(BinaryNumber other) {
		BinaryNumber newBin = new BinaryNumber(0); // empty number
		char[] result = new char[32];

		char[] thisBinary = this.getBinary();
		char[] otherBinary = other.getBinary();
		for (int i = thisBinary.length - 1; i >= 0; i--) {
			if (thisBinary[i] != otherBinary[i]) {
				result[i] = Character.forDigit(1, 10);
			} else {
				result[i] = Character.forDigit(0, 10);
			}
		}

		newBin.setBinary(result);
		return newBin;
	}

	public BinaryNumber calcXOR(int other) {
		BinaryNumber otherBinary = BinaryNumber.fromInt(other);
		return this.calcXOR(otherBinary);
	}

	public static BinaryNumber calcXOR(int first, int second) {
		BinaryNumber firstBinary = BinaryNumber.fromInt(first);
		BinaryNumber secondBinary = BinaryNumber.fromInt(second);
		return firstBinary.calcXOR(secondBinary);
	}

	public static BinaryNumber getTwosComplement(BinaryNumber bn) {
		char[] bitmask = new char[32];

		// calculate 1's complement
		Arrays.fill(bitmask, '1');
		BinaryNumber mask = new BinaryNumber(bitmask);
		BinaryNumber oneComplement = bn.calcXOR(mask);

		// calculate 2's complement
		int carry = 1; // because we add 1 to the end
		char[] oneComplementArr = oneComplement.getBinary();
		for (int i = oneComplementArr.length - 1; i >= 0 && carry == 1; i--) {
			carry--;
			if (oneComplementArr[i] == '1') {
				carry++;
				oneComplementArr[i] = '0';
			} else if (oneComplementArr[i] == '0') {
				oneComplementArr[i] = '1';
			}
		}
		return new BinaryNumber(oneComplementArr);

	}

	public static BinaryNumber fromInt(int val) {
		if (val >= 0) {
			char[] result = new char[32];
			int binIndex = result.length - 1;
			do {
				result[binIndex] = Character.forDigit(val % 2, 10);
				val /= 2;
				binIndex--;
			} while (val > 0);

			for (; binIndex >= 0; binIndex--) {
				result[binIndex] = Character.forDigit(0, 10);
			}

			return new BinaryNumber(result);
		} else {
			return getTwosComplement(fromInt(-val));
		}
	}

	private static void printArr(char[] arr) {
		for (char el : arr) {
			System.out.print(el);
		}
		System.out.print("\n");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
//		Examples of expression to handle:
//			5 && 8 // for AND
//			7 || "1" // for OR
//			"001" ^ "11" //for XOR
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] exp = line.trim().split("\\s++");
			if(exp.length == 3) {
				BinaryNumber a = new BinaryNumber(exp[0]);
				BinaryNumber b = new BinaryNumber(exp[2]);
				System.out.println(a.toString() + " " + b.toString());
			} else {
				System.out.println("Invalid expression parameters. Exit.");
				break;
			}
		}
		sc.close();
	}
}
