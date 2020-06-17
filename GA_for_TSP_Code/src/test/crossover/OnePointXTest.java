package test.crossover;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.crossover.*;
import tsp.Solution;;

public class OnePointXTest {

	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		fitnessFct = new DummyFitnessFct(5);
				
		int [] tour1 = new int[] {0,3,1,2,4};
		c1 = new Chromosome(fitnessFct, new Solution(5, tour1));
		
		int [] tour2 = new int[] {0,1,4,3,2};
		c2 = new Chromosome(fitnessFct, new Solution(5, tour2));
		
	}

	@Test
	public void testTransformPathToOrd1() {
		
		crossover = new OPX(fitnessFct, c1, c2, 2);
		
		int [] expected = new int[] {0,0,2,1,0};
		Assert.assertArrayEquals(expected, ((OrdReprX)crossover).transformPathToOrd(new int[] {0,1,4,3,2}));
	}
	@Test
	public void testTransformOrdToPath1() {
		
		crossover = new OPX(fitnessFct, c1, c2, 2);
		
		int [] expected = new int[] {0,1,4,3,2};
		
		Assert.assertArrayEquals(expected, ((OrdReprX)crossover).transformOrdToPath(new int[] {0,0,2,1,0}));
	}
	
	@Test
	public void testTransformPathToOrd2() {
		
		crossover = new OPX(fitnessFct, c1, c2, 2);
		
		int [] expected = new int[] {0,2,0,0,0};
		
		Assert.assertArrayEquals(expected, ((OrdReprX)crossover).transformPathToOrd(new int[] {0,3,1,2,4}));
	}
	
	@Test
	public void testOnePointX() {
		
		crossover = new OPX(fitnessFct, c1, c2, 2);
		
		int [] expected = new int[] {0,3,4,2,1};

		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
	}
	
}
