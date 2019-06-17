package test.mutation;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.Simulation;
import genetic.mutation.MutationType;

public class SwapTest {
	
	private Simulation simulation;
	private static Chromosome kid;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int [] tour = new int[] {1,2,5,6,4,3,8,7};
		kid = new Chromosome(tour);

	}

	
	@Test
	public void testSwap() {
		
		simulation = new Simulation(MutationType.SWAP, kid, 2, 4);		
		simulation.start();
		
		int [] expected = new int[] {1,2,4,6,5,3,8,7};
		
		Assert.assertArrayEquals(expected, simulation.getMutant().getGenes());
		
	}
	
}	