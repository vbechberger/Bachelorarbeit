package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.mutation.Mutation;
import tsp.Solution;
import genetic.mutation.Displacement;

public class DisplacementTest {
	
	private static FitnessFunction fitnessFct;
	private Mutation mutation;
	private static Chromosome kid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		int [] tour = new int[] {0,1,4,5,3,2,7,6};
		fitnessFct = new DummyFitnessFct(tour.length);
		kid = new Chromosome(fitnessFct, new Solution(8, tour));

	}

	@Test
	public void testDisplacement() {
		mutation = new Displacement(fitnessFct, kid, 1, 4, 6);				
		int [] expected = new int[] {0,2,7,1,4,5,3,6};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenesAsArray());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexSmallerFirstIndex() {
		mutation = new Displacement(fitnessFct, kid, 1, 3, 0);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexEqualFirstIndex() {
		mutation = new Displacement(fitnessFct, kid, 1, 1, 1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testFirstIndexNegativ() {
		mutation = new Displacement(fitnessFct, kid, -1, 1, 5);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testFirstIndexTooLarge() {
		mutation = new Displacement(fitnessFct, kid, 10, 1, 5);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testLastIndexNegativ() {
		mutation = new Displacement(fitnessFct, kid, 0, 5, -1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testLastIndexTooLarge() {
		mutation = new Displacement(fitnessFct, kid, 0, 5, 10);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testMiddleIndexNegativ() {
		mutation = new Displacement(fitnessFct, kid, 0, -1, 5);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testMiddleIndexTooLarge() {
		mutation = new Displacement(fitnessFct, kid, 0, 10, 20);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMiddleIndexSmallerFirstIndex() {
		mutation = new Displacement(fitnessFct, kid, 1, 0, 5);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMiddleIndexEqualFirstIndex() {
		mutation = new Displacement(fitnessFct, kid, 0, 0, 5);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexSmallerMiddleIndex() {
		mutation = new Displacement(fitnessFct, kid, 1, 5, 0);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexEqualMiddleIndex() {
		mutation = new Displacement(fitnessFct, kid, 1, 5, 5);		
	}
	
}
