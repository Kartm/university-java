package lab7;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import lab6.Item;
import lab6.ProductType;

public class ShoppingBagTest {
	ShoppingBag shoppingBag;

	@BeforeEach
	public void setUp() throws Exception {
		shoppingBag = null;
	}

	@Test
	public void testEquals_Premium() throws Exception {
		shoppingBag = new ShoppingBag(false);
		assertFalse(shoppingBag.equals(new ShoppingBag(true)));
		assertTrue(shoppingBag.equals(shoppingBag));
		
		ShoppingBag newShoppingBag = new ShoppingBag(true);
		assertFalse(shoppingBag.equals(newShoppingBag));
	}
	
	@Test
	public void testEquals_Compare() throws Exception {
		shoppingBag = new ShoppingBag(false);
		ShoppingBag newShoppingBag = new ShoppingBag(false);
		assertTrue(shoppingBag.equals(newShoppingBag));
		
		shoppingBag.putIn(new Item(ProductType.MILK, 2));
		newShoppingBag.putIn(new Item(ProductType.MILK, 2));
		
		assertTrue(shoppingBag.equals(newShoppingBag));
		
		newShoppingBag.putIn(new Item(ProductType.MILK, 2));
		assertFalse(shoppingBag.equals(newShoppingBag));
	}

	@Test
	public void testGetIsRobust() throws Exception {
		shoppingBag = new ShoppingBag(true);
		assertTrue(shoppingBag.getIsRobust());
		
		shoppingBag = new ShoppingBag(false);
		assertFalse(shoppingBag.getIsRobust());
	}

	@Test
	public void testGetTotalWeight() throws Exception {
		shoppingBag = new ShoppingBag(true);
		assertEquals(shoppingBag.getTotalWeight(), 0.0);
		
		shoppingBag.putIn(new Item(ProductType.MILK, 2));
		assertEquals(shoppingBag.getTotalWeight(), 4.4);
		
		shoppingBag.remove(ProductType.MILK, 1);
		assertEquals(shoppingBag.getTotalWeight(), 2.2);
		
		shoppingBag.remove(ProductType.MILK);
		assertEquals(shoppingBag.getTotalWeight(), 0.0);
		
		shoppingBag.putIn(new Item(ProductType.BUTTER, 100));
		// only 30 of butter fit = 30*7.5
		assertEquals(shoppingBag.getTotalWeight(), 225.0);
	}
}
