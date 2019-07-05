package test.mutation;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.mutation.Mutation;
import genetic.mutation.MutationShift;

public class ShiftTest {
	
	private Mutation mutation;
	private static Chromosome kid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int [] tour = new int[] {3,7,1,9,6,4,5,2,8};
		kid = new Chromosome(tour);
	}

	@Test
	public void testShift() {
		
		//randomly select an existing city number, i.e.
		//in the range from 1 to the length of the tour
		//TODO:not random!
		//Random randomGenerator = new Random(42);
		//int randomCityNr = randomGenerator.nextInt(9) + 1;

		//ramdomly select the number of positions the city to be replaced at
		//int randomStepsNr = randomGenerator.nextInt(8) + 1;
		
		mutation = new MutationShift(kid, 6, 5);		
		mutation.start();

		int [] expected = new int[] {6,3,7,1,9,4,5,2,8};

		Assert.assertArrayEquals(expected, mutation.getMutant().getGenes());
	}

}
