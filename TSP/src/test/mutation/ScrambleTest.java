package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.mutation.Mutation;
import genetic.mutation.MutationScramble;
import test.util.DummyFitnessFct;

public class ScrambleTest {
	
	private static FitnessFunction fitnessFct;
	private Mutation mutation;
	private static Chromosome kid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		int [] tour = new int[] {1,2,5,6,4,3,8,7};
		fitnessFct = new DummyFitnessFct(tour.length);
		kid = new Chromosome(fitnessFct, tour);

	}

	@Test
	public void testScramble() {
		mutation = new MutationScramble(fitnessFct, kid, 1, 6);		
		mutation.start();
		
		int [] expected = new int[] {1,5,2,3,8,4,6,7};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenes());
	}

}
