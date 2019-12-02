package lab7;

import lab6.Item;
import lab7.Bag;

public class HandBag extends Bag {
	protected boolean isPremiumQuality;
	
	public HandBag(boolean isPremiumQuality) {
		super(5);
		this.isPremiumQuality = isPremiumQuality;
	}
	
	public boolean getIsPremiumQuality() {
		return this.isPremiumQuality;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof HandBag) {
			HandBag castedOther = (HandBag) other;
			if (castedOther.getIsPremiumQuality() != this.getIsPremiumQuality()
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
