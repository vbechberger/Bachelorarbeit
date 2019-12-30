package test.crossover;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.crossover.Crossover;
import genetic.crossover.CrossoverCycleX;
import test.util.DummyFitnessFct;
import util.Printer;

public class CycleXTest {
	
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fitnessFct = new DummyFitnessFct(8);
		
		int [] tour1 = new int[] {0,2,4,5,3,1,7,6};		
		c1 = new Chromosome(fitnessFct, new Solution(8, tour1));
		
		int [] tour2 = new int[] {0,3,1,2,5,4,6,7};
		c2 = new Chromosome(fitnessFct, new Solution(8, tour2));
		
		
	}

	@Test
	public void testCycleXKid1() {
		crossover = new CrossoverCycleX(fitnessFct, c1, c2);
		
		int [] expected = new int[] {0,2,1,5,3,4,6,7};
		Printer.printArray(crossover.getKid1().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenesInPath());
	}
	
	@Test
	public void testCycleXKid2() {
		crossover = new CrossoverCycleX(fitnessFct, c1, c2);
		
		int [] expected = new int[] {0,3,4,2,5,1,7,6};
		Printer.printArray(crossover.getKid2().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenesInPath());
	}
	
	@Test
	public void testEqualParents() {
		
		int [] tour2 = new int[] {0,2,4,5,3,1,7,6};
		Chromosome c2 = new Chromosome(fitnessFct, new Solution(8, tour2));
		
		crossover = new CrossoverCycleX(fitnessFct, c1, c2);
		
		int [] expected = new int[] {0,2,4,5,3,1,7,6};
		Printer.printArray(crossover.getKid1().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenesInPath());
	}

}
