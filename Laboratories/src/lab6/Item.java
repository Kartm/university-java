package lab6;

import lab6.ProductType;

public class Item {
	protected ProductType type;
	protected int quantity = 1;
	protected String comment = "";
	
	public Item(ProductType product, int quantity, String comment) {
		this.type = product;
		this.quantity = (quantity > 0) ? quantity: this.quantity;
		this.comment = (comment != null) ? comment: this.comment;
	}
	
	public Item(ProductType product) {
		this.type = product;
	}
	
	public Item(String productType) {
		this.type = ProductType.fromString(productType);
	}
	
	public void addItem() {
		this.quantity++;
	}
	
	public void addItems(int quantity) {
		if(quantity > 0) {
			this.quantity += quantity;
		}
	}
	
	public boolean remove(int number) {
		if(number > this.quantity) {
			return false;
		}
		this.quantity -= number;
		return true;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getTotalWeight() {
		return this.quantity * this.type.getWeight();
	}

	public String toString() {
		return this.quantity + " items of type " + this.type.toString() + ", " + this.comment;
	}
	
	public boolean equals(Item other) {
		if(
				other instanceof Item == false
				|| other.type != this.type
				|| other.quantity != this.quantity
				) {
			return false;
		}
		return true;
	}
	
	private static void printItemArr(Item[] itemArr) {
		for(Item item: itemArr) {
			String hashCode = Integer.toHexString(System.identityHashCode((item)));
			System.out.println(item + ", hash code: " + hashCode);
		}
	}
	
	public static void main(String[] args) {
		Item itemObj1 = new Item("bread");
		Item itemObj2 = new Item(ProductType.BREAD);
		Item itemObj3 = new Item(ProductType.BREAD, 5, "comment");
		
		Item[] itemArr = {itemObj1, itemObj2, itemObj3};
		printItemArr(itemArr);
		
		Item[] itemArrToFill = new Item[3];
		for(int i = 0; i < itemArr.length; i++) {
			itemArrToFill[i] = itemArr[i];
		}
		
		printItemArr(itemArrToFill);
	}
}
