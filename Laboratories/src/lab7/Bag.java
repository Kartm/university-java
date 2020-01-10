package lab7;

import java.util.ArrayList;

import lab6.Item;
import lab6.ProductType;

public class Bag {
	private ArrayList<Item> items = new ArrayList<Item>();
	private int initialCapacity;

	public Bag(int capacity) {
		initialCapacity = capacity;
	}

	public void removeAllItems() {
		items.clear();
	}
	
	public int getCapacity() {
		return this.initialCapacity;
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}

	public int getTakenSize() {
		// a hacky hack
		int[] totalSize = { 0 };
		this.items.forEach((Item item) -> {
			totalSize[0] += item.getQuantity();
		});
		return totalSize[0];
	}

	protected enum Fitting {
		ALL, SOME, NONE
	}

	protected Fitting getQuantityFitting(int additionalQuantity) {
		int newCapacity = this.getTakenSize() + additionalQuantity;
		if (newCapacity <= this.initialCapacity) {
			return Fitting.ALL;
			// initial = 10
			// current = 9
			// we try to add 3 - only 1 gets added
		} else if (newCapacity - this.initialCapacity < additionalQuantity) {
			return Fitting.SOME;
		} else {
			return Fitting.NONE;
		}
	}
	
	protected void addWithoutDuplicates(Item item) {
		for(int i = 0; i < this.items.size(); i++) {
			if(this.items.get(i).getProductType() == item.getProductType()) {
				Item existingItem = this.items.get(i);
				int newQuantity = existingItem.getQuantity() + item.getQuantity();
				try {
					existingItem.setQuantity(newQuantity);
					this.items.set(i, existingItem);
					return;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		this.items.add(item);
		return;
	}

	public boolean putIn(Item item) {
		switch (this.getQuantityFitting(item.getQuantity())) {
		case ALL: {
			this.addWithoutDuplicates(item);
			return true;
		}
		case SOME: {
			Item reduced = item;
			int newSize = this.initialCapacity - this.getTakenSize();
			try {
				reduced.setQuantity(newSize);
				this.addWithoutDuplicates(reduced);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		}
		default: {
			return false;
		}
		}
	}

	public boolean remove(ProductType productType) {
		return this.items.removeIf((Item item) -> {
			return item.getProductType() == productType;
		});
	}

	public boolean remove(ProductType product, int quantityToRemove) {
		ArrayList<Item> newList = new ArrayList<>();
		boolean isRemovalCorrect = false;
		for (Object item : this.items.toArray()) {
			Item castedItem = (Item) item;
			if(castedItem.getProductType() == product) {
				int itemQuantity = castedItem.getQuantity();
				if(itemQuantity == quantityToRemove) {
					// skip, remove whole item
					isRemovalCorrect = true;
				} else if (itemQuantity > quantityToRemove) {
					// just reduce the quantity
					isRemovalCorrect = true;
					try {
						castedItem.setQuantity(itemQuantity - quantityToRemove);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
					newList.add(castedItem);
				} else {
					// we want to remove more than possible
					isRemovalCorrect = false;
				}
			} else {
				newList.add(castedItem);
			}
		}
		this.items = newList;
		return isRemovalCorrect;
	}

	public String toString() {
		String[] resultStr = { "" };
		Object[] args = { this.getClass().getSimpleName(),
				String.valueOf(this.getTakenSize()),
				String.valueOf(this.initialCapacity) };

		resultStr[0] += String.format("%s. Current capacity: [%s/%s]\n", args);
		this.items.forEach((Item item) -> {
			resultStr[0] += String.format("%s of %s\n", item.getQuantity(),
					item.getProductType());
		});
		return resultStr[0];
	}

	public boolean equals(Object other) {
		if (other instanceof Bag) {
			Bag castedOther = (Bag) other;
			if (castedOther.initialCapacity != this.initialCapacity
					|| castedOther.items.size() != castedOther.items.size()) {
				return false;
			} else {
				// we choose this instance since the sizes are equal
				for (int i = 0; i < this.items.size(); i++) {
					Item thisItem = this.items.get(i);
					Item otherItem = castedOther.items.get(i);
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
