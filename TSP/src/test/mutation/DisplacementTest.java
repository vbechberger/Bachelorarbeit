package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.mutation.Mutation;
import genetic.mutation.MutationDisplacement;
import test.util.DummyFitnessFct;

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
		mutation = new MutationDisplacement(fitnessFct, kid, 1, 4, 6);		
		mutation.start();
		
		int [] expected = new int[] {0,2,7,1,4,5,3,6};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenesAsArray());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexSmallerFirstIndex() {
		mutation = new MutationDisplacement(fitnessFct, kid, 1, 3, 0);		
		mutation.start();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexEqualFirstIndex() {
		mutation = new MutationDisplacement(fitnessFct, kid, 1, 1, 1);		
		mutation.start();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testFirstIndexNegativ() {
		mutation = new MutationDisplacement(fitnessFct, kid, -1, 1, 5);		
		mutation.start();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testFirstIndexTooLarge() {
		mutation = new MutationDisplacement(fitnessFct, kid, 10, 1, 5);		
		mutation.start();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testLastIndexNegativ() {
		mutation = new MutationDisplacement(fitnessFct, kid, 0, 5, -1);		
		mutation.start();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testLastIndexTooLarge() {
		mutation = new MutationDisplacement(fitnessFct, kid, 0, 5, 10);		
		mutation.start();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testMiddleIndexNegativ() {
		mutation = new MutationDisplacement(fitnessFct, kid, 0, -1, 5);		
		mutation.start();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testMiddleIndexTooLarge() {
		mutation = new MutationDisplacement(fitnessFct, kid, 0, 10, 20);		
		mutation.start();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMiddleIndexSmallerFirstIndex() {
		mutation = new MutationDisplacement(fitnessFct, kid, 1, 0, 5);		
		mutation.start();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMiddleIndexEqualFirstIndex() {
		mutation = new MutationDisplacement(fitnessFct, kid, 0, 0, 5);		
		mutation.start();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexSmallerMiddleIndex() {
		mutation = new MutationDisplacement(fitnessFct, kid, 1, 5, 0);		
		mutation.start();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testLastIndexEqualMiddleIndex() {
		mutation = new MutationDisplacement(fitnessFct, kid, 1, 5, 5);		
		mutation.start();
	}
	

}
