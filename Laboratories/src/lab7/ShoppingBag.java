package lab7;

import lab6.Item;

public class ShoppingBag extends Bag {
protected boolean isRobust;
	
	public ShoppingBag(boolean isRobust) {
		super(30);
		this.isRobust = isRobust;
	}
	
	public boolean getIsRobust() {
		return this.isRobust;
	}
	
	public String toString() {
		return String.format("Is robust: %s\n%s",
				this.getIsRobust(), super.toString());
	}

	public double getTotalWeight() {
		double total = 0;
		for(Object item: this.getItems().toArray()) {
			if(item instanceof Item) {
				Item castedItem = (Item)item;
				total += castedItem.getTotalWeight();
			}
		}
		return total;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof ShoppingBag) {
			ShoppingBag castedOther = (ShoppingBag) other;
			if (castedOther.getIsRobust() != this.getIsRobust()
					|| castedOther.getItems().size() != this.getItems().size()) {
				return false;
			} else {
				// we choose this instance since the sizes are equal
				for (int i = 0; i < this.getItems().size(); i++) {
					Item thisItem = this.getItems().get(i);
					Item otherItem = castedOther.getItems().get(i);
					if (thisItem.getProductType() != otherItem.getProductType()
							|| thisItem.getQuantity() != otherItem
									.getQuantity()) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

}
