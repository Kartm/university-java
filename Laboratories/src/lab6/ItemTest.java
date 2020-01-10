package lab6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {
	
	private Item item;
	
	@BeforeEach
    public void setUp() {
		this.item = new Item(ProductType.MILK, 5, "Comment");
    }

    @AfterEach
    public void tearDown() {
        item = null;
    }
	
	@Test
	void testItemProductTypeIntString() {
		this.item = new Item(ProductType.MILK, 5, "Comment");
		
		assertEquals(item.getQuantity(), 5);
		assertEquals(item.toString(), "5 items of type MILK, Comment");
		assertNotEquals(item.toString(), "2 items of type BREAD, No comment");
		assertNotEquals(item.getQuantity(), 10);
	}

	@Test
	void testItemProductType() {
		this.item = new Item(ProductType.BUTTER);
		assertEquals(item.toString(), "1 items of type BUTTER, ");
		assertNotEquals(item.toString(), "");
	}

	@Test
	void testItemString() {
		this.item = new Item("bread");
		assertEquals(item.toString(), "1 items of type BREAD, ");
		assertNotEquals(item.toString(), "");
		
		this.item = new Item("blabla");
		assertEquals(item.toString(), "1 items of type OTHER, ");
		assertNotEquals(item.toString(), "");
	}

	@Test
	void testAddItem() {
		item.addItem();
		assertTrue(item.getQuantity() == 6);
	}

	@Test
	void testAddItems() {
		item.addItems(5);
		assertTrue(item.getQuantity() == 10);
	}

	@Test
	void testRemove() {
		assertTrue(item.remove(2));
		assertFalse(item.remove(100000));
	}

	@Test
	void testGetQuantity() {
		assertTrue(item.getQuantity() == 5);
		assertTrue(item.getQuantity() != 500000000);
	}

	@Test
	void testGetTotalWeight() {
		assertEquals(item.getTotalWeight(), 11.0);
		assertNotEquals(item.getTotalWeight(), 0.0);
		assertNotEquals(item.getTotalWeight(), 0.1);
		assertNotEquals(item.getTotalWeight(), 1.0);
	}

	@Test
	void testToString() {
		assertEquals(item.toString(), "5 items of type MILK, Comment");
		assertNotEquals(item.toString(), "2 items of type BREAD, No comment");
		assertNotEquals(item.toString(), "");
	}

	@Test
	void testEqualsItem() {
		Item otherItem = new Item("bread");
		assertTrue(item.equals(item));
		assertFalse(item.equals(otherItem));
	}

}
