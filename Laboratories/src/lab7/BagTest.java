package lab7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import lab6.Item;
import lab6.ProductType;
import lab7.Bag.Fitting;

public class BagTest {
	Bag bag;

	@BeforeEach
	public void setUp() throws Exception {
		bag = null;
	}

	@Test
	public void testRemoveAllItems() throws Exception {
		bag = new Bag(5);
		Item item1 = new Item(ProductType.BREAD, 4);
		Item item2 = new Item(ProductType.BUTTER, 1);
		bag.putIn(item1);
		bag.putIn(item2);
		bag.removeAllItems();

		assertEquals(bag.getTakenSize(), 0);

		bag.removeAllItems();
		assertEquals(bag.getTakenSize(), 0);
	}

	@Test
	public void testGetCapacity() throws Exception {
		bag = new Bag(5);
		assertEquals(bag.getCapacity(), 5);
		assertNotEquals(bag.getCapacity(), -1);
	}

	@Test
	public void testGetCapacity_Empty() throws Exception {
		bag = new Bag(0);
		assertEquals(bag.getCapacity(), 0);
		assertNotEquals(bag.getCapacity(), -1);
	}
	
	@Test
	public void testGetTakenSize() throws Exception {
		bag = new Bag(5);
		assertEquals(bag.getTakenSize(), 0);

		Item item1 = new Item(ProductType.BREAD, 4);
		bag.putIn(item1);
		assertEquals(bag.getTakenSize(), 4);
	}

	@Test
	public void testPutIn() throws Exception {
		bag = new Bag(5);
		Item item1 = new Item(ProductType.BREAD, 4);

		assertTrue(bag.putIn(item1));

		Item item2 = new Item(ProductType.BUTTER, 10);
		assertTrue(bag.putIn(item2));
		
		Item item3 = new Item(ProductType.OTHER, 1);
		assertFalse(bag.putIn(item3));
	}

	@Test
	public void testRemoveProductType() throws Exception {
		bag = new Bag(5);
		Item item1 = new Item(ProductType.BREAD, 4);
		bag.putIn(item1);
		assertTrue(bag.remove(ProductType.BREAD));
		assertEquals(bag.getTakenSize(), 0);
		assertFalse(bag.remove(ProductType.MILK));
	}

	@Test
	public void testRemoveProductTypeInt_Some() throws Exception {
		bag = new Bag(5);
		Item item1 = new Item(ProductType.BREAD, 4);
		bag.putIn(item1);
		assertTrue(bag.remove(ProductType.BREAD, 2));
		assertEquals(bag.getTakenSize(), 2);
	}
	
	@Test
	public void testRemoveProductTypeInt_More() throws Exception {
		bag = new Bag(5);
		Item item1 = new Item(ProductType.BREAD, 4);
		bag.putIn(item1);
		assertTrue(bag.remove(ProductType.BREAD, -10));
		assertFalse(bag.remove(ProductType.MILK));
	}
	
	@Test
	public void testRemoveProductTypeInt_All() throws Exception {
		bag = new Bag(5);
		Item item1 = new Item(ProductType.BREAD, 4);
		bag.putIn(item1);
		assertTrue(bag.remove(ProductType.BREAD, 4));
		assertFalse(bag.remove(ProductType.MILK));
	}

	@Test
	public void testToString() throws Exception {
		bag = new Bag(5);
		Item item1 = new Item(ProductType.BREAD, 4);
		bag.putIn(item1);
		assertEquals(bag.toString(), "Bag. Current capacity: [4/5]\n" + 
				"4 of BREAD\n");
	}
	
	@Test
	public void testToString_Empty() throws Exception {
		bag = new Bag(5);
		assertEquals(bag.toString(), "Bag. Current capacity: [0/5]\n");
	}
	
	@Test
	public void testGetQuantityFitting_All() throws Exception {
		bag = new Bag(5);
		assertEquals(bag.getQuantityFitting(5), Fitting.ALL);
	}
	
	@Test
	public void testGetQuantityFitting_Some() throws Exception {
		bag = new Bag(5);
		assertEquals(bag.getQuantityFitting(10), Fitting.SOME);
	}
	
	@Test
	public void testGetQuantityFitting_SomeVaried() throws Exception {
		bag = new Bag(5);
		bag.putIn(new Item(ProductType.MILK, 2));
		assertEquals(bag.getQuantityFitting(10), Fitting.SOME);
	}
	
	@Test
	public void testGetQuantityFitting_None() throws Exception {
		bag = new Bag(2);
		bag.putIn(new Item(ProductType.MILK, 2));
		assertEquals(bag.getQuantityFitting(10), Fitting.NONE);
	}
	
	@Test
	public void fillingBag() {
		bag = new Bag(5);
		assertTrue(bag.putIn(new Item(ProductType.MILK, 1)));
		assertTrue(bag.putIn(new Item(ProductType.BREAD, 1)));
		assertTrue(bag.putIn(new Item(ProductType.BUTTER, 1)));
		assertTrue(bag.putIn(new Item(ProductType.TEA, 1)));
		assertTrue(bag.putIn(new Item(ProductType.OTHER, 1)));
		assertEquals(bag.getTakenSize(), 5);
		assertFalse(bag.putIn(new Item(ProductType.OTHER, 1)));
	}
	
	@Test
	public void overfillingBag() {
		bag = new Bag(5);
		assertTrue(bag.putIn(new Item(ProductType.MILK, 1)));
		assertTrue(bag.putIn(new Item(ProductType.BREAD, 1)));
		assertTrue(bag.putIn(new Item(ProductType.BUTTER, 1)));
		assertTrue(bag.putIn(new Item(ProductType.TEA, 1)));
		assertTrue(bag.putIn(new Item(ProductType.OTHER, 1)));
		assertFalse(bag.putIn(new Item(ProductType.BREAD, 1000)));
		assertEquals(bag.getTakenSize(), 5);
	}

	@Test
	public void testEquals() throws Exception {
		bag = new Bag(5);
		assertFalse(bag.equals(new Bag(0)));
		assertTrue(bag.equals(bag));
		
		Bag newBag = new Bag(5);
		assertTrue(bag.equals(newBag));
		
		bag.putIn(new Item(ProductType.MILK, 2));
		newBag.putIn(new Item(ProductType.MILK, 2));
		assertTrue(bag.equals(newBag));
		
		newBag.putIn(new Item(ProductType.MILK, 2));
		assertFalse(bag.equals(newBag));
	}
}
