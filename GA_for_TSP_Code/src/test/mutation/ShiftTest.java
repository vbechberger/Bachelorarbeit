package test.mutation;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.mutation.Mutation;
import genetic.mutation.Shift;
import tsp.Solution;

public class ShiftTest {
	
	private static FitnessFunction fitnessFct;
	private Mutation mutation;
	private static Chromosome kid;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		int [] tour = new int[] {2,6,0,8,5,3,4,1,7};
		fitnessFct = new DummyFitnessFct(tour.length);
		kid = new Chromosome(fitnessFct, new Solution(9, tour));
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
		
		mutation = new Shift(fitnessFct, kid, 5, 5);		

		int [] expected = new int[] {5,2,6,0,8,3,4,1,7};

		Assert.assertArrayEquals(expected, mutation.getMutant().getGenesAsArray());
	}

}
