package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.mutation.Mutation;
import genetic.mutation.MutationScramble;
import test.util.DummyFitnessFct;

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
		mutation = new MutationScramble(fitnessFct, kid, 1, 6);		
		mutation.start();
		
		int [] expected = new int[] {0,4,1,2,7,3,5,6};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenesInPath());
	}

}
