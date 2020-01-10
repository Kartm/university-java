package lab8;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class BinaryNumberTest {
	BinaryNumber bn = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testToString() throws Exception {
		int val = 0;
		bn = new BinaryNumber(val);
		assertEquals(bn.toString(), "00000000000000000000000000000000");
	}

	@Test
	public void testFromInt() throws Exception {
		BinaryNumber binary = BinaryNumber.fromInt(0);
		assertEquals(binary.toString(), "00000000000000000000000000000000");

		binary = BinaryNumber.fromInt(1);
		assertEquals(binary.toString(), "00000000000000000000000000000001");

		binary = BinaryNumber.fromInt(6);
		assertEquals(binary.toString(), "00000000000000000000000000000110");

		binary = BinaryNumber.fromInt((int) Math.pow(2, 31));
		assertEquals(binary.toString(), "01111111111111111111111111111111");

		binary = BinaryNumber.fromInt((int) Math.pow(2, 31) - 1);
		assertEquals(binary.toString(), "01111111111111111111111111111110");
	}

	@Test
	public void testFromIntNegative() throws Exception {
		BinaryNumber binary = BinaryNumber.fromInt(-0);
		assertEquals(binary.toString(), "00000000000000000000000000000000");

		binary = BinaryNumber.fromInt(-1);
		assertEquals(binary.toString(), "11111111111111111111111111111111");

		binary = BinaryNumber.fromInt(-6);
		assertEquals(binary.toString(), "11111111111111111111111111111010");

		binary = BinaryNumber.fromInt(-(int) Math.pow(2, 31));
		assertEquals(binary.toString(), "10000000000000000000000000000001");

		binary = BinaryNumber.fromInt(-((int) Math.pow(2, 31) - 1));
		assertEquals(binary.toString(), "10000000000000000000000000000010");
	}

	@Test
	public void testToInt() throws Exception {
		int[] numbers = new int[] { 0, 1, 2, 3, 10000,
				(int) Math.pow(2, 31) - 1 };
		for (int num : numbers) {
			assertEquals(new BinaryNumber(num).toInt(), num);
		}
	}

	@Test
	public void testToIntNegative() throws Exception {
		int[] numbers = new int[] { -0, -1, -1000,
				-((int) Math.pow(2, 31) - 1) };
		for (int num : numbers) {
			assertEquals(new BinaryNumber(num).toInt(), num);
		}
	}

	@Test
	public void testBinaryNumberStringFromString() throws Exception {
		bn = new BinaryNumber("0");
		assertEquals(bn.toString(), "00000000000000000000000000000000");

		bn = new BinaryNumber("1");
		assertEquals(bn.toString(), "00000000000000000000000000000001");

		bn = new BinaryNumber("11111111111111111111111111111111");
		assertEquals(bn.toString(), "11111111111111111111111111111111");
	}

	@Test
	public void testGetTwosComplement() throws Exception {
		bn = new BinaryNumber(6);
		assertEquals(BinaryNumber.getTwosComplement(bn).toString(),
				"11111111111111111111111111111010");

		bn = new BinaryNumber(0);
		assertEquals(BinaryNumber.getTwosComplement(bn).toString(),
				"00000000000000000000000000000000");

		bn = new BinaryNumber((int) Math.pow(2, 31) - 1);
		assertEquals(BinaryNumber.getTwosComplement(bn).toString(),
				"10000000000000000000000000000010");
	}

	@Test
	public void testCalcANDBinaryNumber() throws Exception {
		bn = new BinaryNumber(6);
		assertEquals(bn.calcAND(bn).toInt(), bn.toInt());

		bn = new BinaryNumber(6);
		assertEquals(bn.calcAND(new BinaryNumber(0)).toInt(), 0);

		bn = new BinaryNumber(6);
		assertEquals(bn.calcAND(new BinaryNumber("111")).toInt(), 6);

		bn = new BinaryNumber(0);
		assertEquals(bn.calcAND(new BinaryNumber("111")).toInt(), 0);

		bn = new BinaryNumber((int) Math.pow(2, 31) - 1);
		assertEquals(bn.calcAND(new BinaryNumber("111")).toInt(), 6);

		bn = new BinaryNumber((int) Math.pow(2, 31) - 1);
		assertEquals(
				bn.calcAND(new BinaryNumber((int) Math.pow(2, 31) - 1)).toInt(),
				(int) Math.pow(2, 31) - 1);
	}

	@Test
	public void testCalcANDInt() throws Exception {
		bn = new BinaryNumber(0);
		assertEquals(bn.calcAND(0).toInt(), 0);
	}

	@Test
	public void testCalcANDIntInt() throws Exception {
		assertEquals(BinaryNumber.calcAND(0, 0).toInt(), 0);
		assertEquals(BinaryNumber.calcAND(0, 1).toInt(), 0);
		assertEquals(BinaryNumber.calcAND(1000, 1000).toInt(), 1000);
		assertEquals(BinaryNumber.calcAND(123, 321).toInt(), 65);
	}

	@Test
	public void testCalcORBinaryNumber() throws Exception {
		BinaryNumber bnFst = new BinaryNumber(0);
		BinaryNumber bnSec = new BinaryNumber(0);
		assertEquals(bnFst.calcOR(bnSec).toInt(), 0);
	}

	@Test
	public void testCalcORInt() throws Exception {
		BinaryNumber bnFst = new BinaryNumber(0);

		assertEquals(bnFst.calcOR(0).toInt(), 0);
		assertEquals(bnFst.calcOR(1).toInt(), 1);
		assertEquals(bnFst.calcOR(1000).toInt(), 1000);

		bnFst = new BinaryNumber(123);
		assertEquals(bnFst.calcOR(321).toInt(), 379);
	}

	@Test
	public void testCalcORIntInt() throws Exception {
		assertEquals(BinaryNumber.calcOR(0, 0).toInt(), 0);
		assertEquals(BinaryNumber.calcOR(1, 0).toInt(), 1);
		assertEquals(BinaryNumber.calcOR(1000, 1000).toInt(), 1000);
		assertEquals(BinaryNumber.calcOR(123, 321).toInt(), 379);
	}

	@Test
	public void testCalcXORBinaryNumber() throws Exception {
		BinaryNumber bnFst = new BinaryNumber(0);
		BinaryNumber bnSec = new BinaryNumber(1);
		assertEquals(bnFst.calcXOR(bnSec).toInt(), 1);
		bnSec = new BinaryNumber(0);
		assertEquals(bnFst.calcXOR(bnSec).toInt(), 0);
	}

	@Test
	public void testCalcXORInt() throws Exception {
		BinaryNumber bnFst = new BinaryNumber(0);

		assertEquals(bnFst.calcXOR(0).toInt(), 0);
		assertEquals(bnFst.calcXOR(1).toInt(), 1);
		assertEquals(bnFst.calcXOR(1000).toInt(), 1000);

		bnFst = new BinaryNumber(123);
		assertEquals(bnFst.calcXOR(321).toInt(), 314);
	}

	@Test
	public void testCalcXORIntInt() throws Exception {
		assertEquals(BinaryNumber.calcXOR(0, 0).toInt(), 0);
		assertEquals(BinaryNumber.calcXOR(1, 0).toInt(), 1);
		assertEquals(BinaryNumber.calcXOR(1000, 1000).toInt(), 0);
		assertEquals(BinaryNumber.calcXOR(123, 321).toInt(), 314);	
	}
}
