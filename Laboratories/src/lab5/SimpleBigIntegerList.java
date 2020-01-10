package lab5;

import java.math.BigInteger;

public class SimpleBigIntegerList {
	protected SimpleBigIntegerList next;
	protected BigInteger value;
	protected int size = 0;
	
	public void incrementSize() {
		size++;
	}
	
	public int getSize() {
		return size;
	}
	
	public SimpleBigIntegerList getNext() {
		return next;
	}
	
	public BigInteger getValue() {
		return value;
	}
	
	public SimpleBigIntegerList(BigInteger val) {
		value = val;
		next = null;
		incrementSize();
	}
	
	public static SimpleBigIntegerList addElem(SimpleBigIntegerList start, BigInteger val) {
		SimpleBigIntegerList elem = new SimpleBigIntegerList(val);
		elem.next = start;
		elem.incrementSize();
		return elem;
	}
	public static SimpleBigIntegerList appendElem(SimpleBigIntegerList elem, BigInteger val) {
		if(elem == null) {
			SimpleBigIntegerList nElem = new SimpleBigIntegerList(val);
			return nElem;
		} else {
			elem.next = appendElem(elem.next, val);
			elem.incrementSize();
			return elem;
		}
	}
	public static String toString(SimpleBigIntegerList start) {
		if(start == null) {
			return "[end]";
		}
		return "[" + start.value + "]\t" + toString(start.next);
	}
	public static String reverseToString(SimpleBigIntegerList start) {
		if(start == null) {
			return "[end]\t";
		}
		return reverseToString(start.next) + "[" + start.value + "]\t";
	}
}
