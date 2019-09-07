package test.general;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Instance;

public class InstanceTest {
	private Instance instance;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	

	@Before
	public void setUp() throws Exception {
		instance = new Instance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValid() {
		
		assertTrue(instance.isValid());
	}
	
	/*@Test(expected = IllegalStateException.class)
	public void testMissedValue() {
		instance.getTour().remove(0);
	}*/
	/*
	@Test
	
	public void testWrongSize() {
		
		ArrayList<Integer> tour = new ArrayList<Integer>();
		instance = new Instance(tour);
		assertFalse(instance.isValid());
	}
	*/
		//Test 2: if the size <= 0
		//t.size = -10;
		//t.isValid();

		//Test 3: if a city is missed
		//t.tour.set(0, 10);
		//t.isValid();
		//t.print();
		//
	

}
