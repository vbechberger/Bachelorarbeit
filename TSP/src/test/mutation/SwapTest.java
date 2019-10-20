package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.mutation.Mutation;
import genetic.mutation.MutationSwap;
import test.util.DummyFitnessFct;

public class SwapTest {
	
	private static FitnessFunction fitnessFct;
	private Mutation mutation;;
	private static Chromosome kid;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		int [] tour = new int[] {1,2,5,6,4,3,8,7};
		fitnessFct = new DummyFitnessFct(tour.length);
		kid = new Chromosome(fitnessFct, tour);

	}
	
	

	
	@Test
	public void testSwap() {
		
		mutation = new MutationSwap(fitnessFct, kid, 2, 4);		
		mutation.start();
		
		int [] expected = new int[] {1,2,4,6,5,3,8,7};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenes());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex1NotSet() {
		
		mutation = new MutationSwap(fitnessFct, kid, -1, 4);		
		mutation.start();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex2NotSet() {
		
		mutation = new MutationSwap(fitnessFct, kid, 2, -1);		
		mutation.start();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex1OutOfBoundsLarge() {
		
		mutation = new MutationSwap(fitnessFct, kid, 10, 4);		
		mutation.start();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex2OutOfBoundsLarge() {
		
		mutation = new MutationSwap(fitnessFct, kid, 2, 10);		
		mutation.start();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex1OutOfBoundsSmall() {
		
		mutation = new MutationSwap(fitnessFct, kid, -100, 4);		
		mutation.start();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSwapIndex2OutOfBoundsSmall() {
		
		mutation = new MutationSwap(fitnessFct, kid, 2, -100);		
		mutation.start();
		
	}
}	