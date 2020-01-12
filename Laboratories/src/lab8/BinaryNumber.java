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

	public static String calcFromToken(String token) {
		String[] exp = token.trim().split("\\s++");
		if (exp.length == 3) {
			BinaryNumber aBinary = null;
			BinaryNumber bBinary = null;

			String a = exp[0];
			String b = exp[2];

			if (a.matches("\".*\"")) {
				aBinary = new BinaryNumber(a.replace("\"", ""));
			} else if (a.matches("^[0-9]*$")) {
				aBinary = new BinaryNumber(Integer.parseInt(a));
			} else {
				System.out.println("Invalid first number.");
			}

			if (b.matches("\".*\"")) {
				bBinary = new BinaryNumber(b.replace("\"", ""));
			} else if (a.matches("^[0-9]*$")) {
				bBinary = new BinaryNumber(Integer.parseInt(b));
			} else {
				System.out.println("Invalid first number.");
			}

			if (aBinary != null && bBinary != null) {
				String operation = exp[1];
				if (operation.matches("&&")) {
					return aBinary.calcAND(bBinary).toString();
				} else if (operation.matches("||")) {
					return aBinary.calcOR(bBinary).toString();
				} else if (operation.matches("\\^")) {
					return aBinary.calcXOR(bBinary).toString();
				} else {
					return "Unknown operation.";
				}
			}
		} else {
			return "Invalid expression parameters. Exit.";
		}
		return null;

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			System.out.println(calcFromToken(line));
		}
		sc.close();
	}
}
