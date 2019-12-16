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
		
		binary = BinaryNumber.fromInt((int)Math.pow(2, 31));
		assertEquals(binary.toString(), "01111111111111111111111111111111");
		
		binary = BinaryNumber.fromInt((int)Math.pow(2, 31) - 1);
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
		
		binary = BinaryNumber.fromInt(-(int)Math.pow(2, 31));
		assertEquals(binary.toString(), "10000000000000000000000000000001");
		
		binary = BinaryNumber.fromInt(-((int)Math.pow(2, 31) - 1));
		assertEquals(binary.toString(), "10000000000000000000000000000010");
	}

	@Test
	public void testToInt() throws Exception {
		int[] numbers = new int[] {0, 1, 2, 3, 10000, (int)Math.pow(2, 31) - 1};
		for(int num: numbers) {
			assertEquals(new BinaryNumber(num).toInt(), num);
		}
	}
	
	@Test
	public void testToIntNegative() throws Exception {
		int[] numbers = new int[] {-0, -1, -1000, -((int)Math.pow(2, 31) - 1)};
		for(int num: numbers) {
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
		assertEquals(BinaryNumber.getTwosComplement(bn).toString(), "11111111111111111111111111111010");
	
		bn = new BinaryNumber(0);
		assertEquals(BinaryNumber.getTwosComplement(bn).toString(), "00000000000000000000000000000000");
	
		bn = new BinaryNumber((int)Math.pow(2, 31) - 1);
		assertEquals(BinaryNumber.getTwosComplement(bn).toString(), "10000000000000000000000000000010");
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
		
		bn = new BinaryNumber((int)Math.pow(2, 31) - 1);
		assertEquals(bn.calcAND(new BinaryNumber("111")).toInt(), 6);
		
		bn = new BinaryNumber((int)Math.pow(2, 31) - 1);
		assertEquals(bn.calcAND(new BinaryNumber((int)Math.pow(2, 31) - 1)).toInt(), (int)Math.pow(2, 31) - 1);
	}

	@Test
	public void testCalcANDInt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCalcANDIntInt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCalcORBinaryNumber() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCalcORInt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCalcORIntInt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCalcXORBinaryNumber() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCalcXORInt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCalcXORIntInt() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
}
