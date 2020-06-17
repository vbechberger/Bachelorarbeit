package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.mutation.Mutation;
import tsp.Solution;
import genetic.mutation.Inversion;

public class InversionTest {
	
	private static FitnessFunction fitnessFct;
	private Mutation mutation;
	private static Chromosome kid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		int [] tour = new int[] {0,1,4,5,3,2};
		fitnessFct = new DummyFitnessFct(tour.length);
		kid = new Chromosome(fitnessFct, new Solution(6, tour));

	}

	@Test
	public void testInversion() {
		mutation = new Inversion(fitnessFct, kid, 1, 4);		
		
		int [] expected = new int[] {0,3,5,4,1,2};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenesAsArray());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInversionIndex2SmallerIndex1() {
		mutation = new Inversion(fitnessFct, kid, 1, 0);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInversionIndex2EqualIndex1() {
		mutation = new Inversion(fitnessFct, kid, 1, 1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInversionIndex1Negativ() {
		mutation = new Inversion(fitnessFct, kid, -1, 1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInversionIndex1TooLarge() {
		mutation = new Inversion(fitnessFct, kid, 8, 1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInversionIndex2Negativ() {
		mutation = new Inversion(fitnessFct, kid, 0, -1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInversionIndex2TooLarge() {
		mutation = new Inversion(fitnessFct, kid, 0, 10);		
	}

}
