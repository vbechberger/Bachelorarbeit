package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.mutation.Mutation;
import genetic.mutation.Scramble;
import tsp.Solution;

public class ScrambleTest {
	
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
	public void testScramble() {
		mutation = new Scramble(fitnessFct, kid, 1, 6);		
		
		int [] expected = new int[] {0,4,1,2,7,3,5,6};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenesAsArray());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIndex2SmallerIndex1() {
		mutation = new Scramble(fitnessFct, kid, 1, 0);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIndex2EqualIndex1() {
		mutation = new Scramble(fitnessFct, kid, 1, 1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndex1Negativ() {
		mutation = new Scramble(fitnessFct, kid, -1, 1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndex1TooLarge() {
		mutation = new Scramble(fitnessFct, kid, 8, 1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndex2Negativ() {
		mutation = new Scramble(fitnessFct, kid, 0, -1);		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndex2TooLarge() {
		mutation = new Scramble(fitnessFct, kid, 0, 10);		
	}

}
