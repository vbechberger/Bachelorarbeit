package test.mutation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.mutation.Mutation;
import genetic.mutation.MutationScramble;

public class ScrambleTest {
	
	private Mutation mutation;
	private static Chromosome kid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int [] tour = new int[] {1,2,5,6,4,3,8,7};
		kid = new Chromosome(tour);

	}

	@Test
	public void testScramble() {
		mutation = new MutationScramble(kid, 1, 6);		
		mutation.start();
		
		int [] expected = new int[] {1,5,2,3,8,4,6,7};
		
		Assert.assertArrayEquals(expected, mutation.getMutant().getGenes());
	}

}
