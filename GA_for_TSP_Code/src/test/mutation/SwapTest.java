package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.mutation.Mutation;
import genetic.mutation.Swap;
import tsp.Solution;

public class SwapTest {
	
	private static FitnessFunction fitnessFct;
	private Mutation mutation;;
	private static Chromosome kid;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		int [] tour = new int[] {0,1,4,5,3,2,7,6};
		fitnessFct = new DummyFitnessFct(tour.length);
		kid = new Chromosome(fitnessFct, new Solution(8, tour));

	}
	
	

	
	@Test
	public void testSwap() {
		
		mutation = new Swap(fitnessFct, kid, 2, 4);		
		
		int [] expected = new int[] {0,1,3,5,4,2,7,6};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenesAsArray());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex1NotSet() {
		
		mutation = new Swap(fitnessFct, kid, -1, 4);				
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex2NotSet() {
		
		mutation = new Swap(fitnessFct, kid, 2, -1);				
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex1OutOfBoundsLarge() {
		
		mutation = new Swap(fitnessFct, kid, 10, 4);				
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex2OutOfBoundsLarge() {
		
		mutation = new Swap(fitnessFct, kid, 2, 10);				
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex1OutOfBoundsSmall() {
		
		mutation = new Swap(fitnessFct, kid, -100, 4);				
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex2OutOfBoundsSmall() {
		
		mutation = new Swap(fitnessFct, kid, 2, -100);				
	}
}	