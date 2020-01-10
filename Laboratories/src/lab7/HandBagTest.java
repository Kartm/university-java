package lab7;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import lab6.Item;
import lab6.ProductType;

public class HandBagTest {
	HandBag handBag;

	@BeforeEach
	public void setUp() throws Exception {
		handBag = null;
	}

	@Test
	public void testEquals_Premium() throws Exception {
		handBag = new HandBag(false);
		assertFalse(handBag.equals(new HandBag(true)));
		assertTrue(handBag.equals(handBag));
		
		HandBag newHandBag = new HandBag(true);
		assertFalse(handBag.equals(newHandBag));
	}
	
	@Test
	public void testEquals_Compare() throws Exception {
		handBag = new HandBag(false);
		HandBag newHandBag = new HandBag(false);
		assertTrue(handBag.equals(newHandBag));
		
		handBag.putIn(new Item(ProductType.MILK, 2));
		newHandBag.putIn(new Item(ProductType.MILK, 2));
		
		assertTrue(handBag.equals(newHandBag));
		
		newHandBag.putIn(new Item(ProductType.MILK, 2));
		assertFalse(handBag.equals(newHandBag));
	}

	@Test
	public void testGetIsPremiumQuality() throws Exception {
		handBag = new HandBag(true);
		assertTrue(handBag.getIsPremiumQuality());
		
		handBag = new HandBag(false);
		assertFalse(handBag.getIsPremiumQuality());
	}

	@Test
	public void testToString() throws Exception {
		handBag = new HandBag(true);
		assertEquals(handBag.toString(), "Is premium quality: true\n" + 
				"HandBag. Current capacity: [0/5]\n");
		handBag = new HandBag(false);
		assertEquals(handBag.toString(), "Is premium quality: false\n" + 
				"HandBag. Current capacity: [0/5]\n");
	}
}
