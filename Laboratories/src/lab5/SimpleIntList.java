package lab5;

public class SimpleIntList {
	protected SimpleIntList next;
	protected int value;
	protected int size = 0;
	
	public void incrementSize() {
		size++;
	}
	
	public int getSize() {
		return size;
	}
	
	public SimpleIntList getNext() {
		return next;
	}
	
	public int getValue() {
		return value;
	}
	
	public SimpleIntList(int val) {
		value = val;
		next = null;
		incrementSize();
	}
	
	public static SimpleIntList addElem(SimpleIntList start, int val) {
		SimpleIntList elem = new SimpleIntList(val);
		elem.next = start;
		elem.incrementSize();
		return elem;
	}
	public static SimpleIntList appendElem(SimpleIntList elem, int val) {
		if(elem == null) {
			SimpleIntList nElem = new SimpleIntList(val);
			return nElem;
		} else {
			elem.next = appendElem(elem.next, val);
			elem.incrementSize();
			return elem;
		}
	}
	public static String toString(SimpleIntList start) {
		if(start == null) {
			return "[end]";
		}
		return "[" + start.value + "]\t" + toString(start.next);
	}
	public static String reverseToString(SimpleIntList start) {
		if(start == null) {
			return "[end]\t";
		}
		return reverseToString(start.next) + "[" + start.value + "]\t";
	}
}
