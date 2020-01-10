package lab6;

public enum ProductType {
	BREAD(3.5), MILK(2.2), TEA(15.0), BUTTER(7.5), OTHER(0.0);
	private double weight;

	public static ProductType fromString(String s) { 
		s = s.toLowerCase().trim();
		switch(s) {
		case "bread": return BREAD;
		case "milk": return MILK;
		case "tea": return TEA;
		case "butter": return BUTTER;
		default: return OTHER;
		}
	}
	private ProductType(double weight) {
		this.weight = weight;
	}
	public double getWeight() {
		return this.weight;
	}
	
	public String show() {
		String s = this + " [" + this.weight + "]";
		return s.toLowerCase();
	}
	
	public static void testMe() {
		ProductType p1 = ProductType.BREAD;
		System.out.println(p1);
		p1 = ProductType.fromString("tea");
		System.out.println(p1);
		for(ProductType p: ProductType.values()) {
			System.out.println(p.show());
		}
	}
	
	public static void main(String[] args) {
		testMe();
	}
}