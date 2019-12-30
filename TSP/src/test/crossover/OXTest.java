package test.crossover;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.crossover.PathReprCrossover;
import genetic.crossover.Crossover;
import genetic.crossover.CrossoverOX;
import test.util.DummyFitnessFct;
import util.Printer;

public class OXTest {
	
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		fitnessFct = new DummyFitnessFct(8);
		
		int [] tour1 = new int[] {0,1,4,5,3,2,7,6};
		c1 = new Chromosome(fitnessFct, new Solution(8, tour1));
		
		int [] tour2 = new int[] {0,3,1,2,5,4,6,7};
		c2 = new Chromosome(fitnessFct, new Solution(8, tour2));
		
	}
	
	@Test
	public void testOXMiddleKid1() {
		
		crossover = new CrossoverOX(fitnessFct, c1, c2, 2, 4);
		
		int [] expected = new int[] {1,2,4,5,3,6,7,0};
		Printer.printArray(crossover.getKid1().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenesInPath());
	}
	
	@Test
	public void testOXMiddleKid2() {
		
		crossover = new CrossoverOX(fitnessFct, c1, c2, 2, 4);
		
		int [] expected = new int[] {4,3,1,2,5,7,6,0};
		Printer.printArray(crossover.getKid2().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenesInPath());
	}
	
	@Test
	public void testOXEndKid1() {
		
		crossover = new CrossoverOX(fitnessFct, c1, c2, 5, 7);
		
		int [] expected = new int[] {0,3,1,5,4,2,7,6};
		Printer.printArray(crossover.getKid1().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenesInPath());
	}
	
	@Test
	public void testOXEndKid2() {
		
		crossover = new CrossoverOX(fitnessFct, c1, c2, 5, 7);
		
		int [] expected = new int[] {0,1,5,3,2,4,6,7};
		Printer.printArray(crossover.getKid2().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenesInPath());
	}
	
	@Test
	public void testOXStartKid1() {
		
		crossover = new CrossoverOX(fitnessFct, c1, c2, 0, 2);
		
		int [] expected = new int[] {0,1,4,2,5,6,7,3};
		Printer.printArray(crossover.getKid1().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenesInPath());
	}
	
	@Test
	public void testOXStartKid2() {
		
		crossover = new CrossoverOX(fitnessFct, c1, c2, 0, 2);
		
		int [] expected = new int[] {0,3,1,5,2,7,6,4};
		Printer.printArray(crossover.getKid2().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenesInPath());
	}
	
	//TODO:I do everything locally here, perhaps to optimize
	@Test
	public void testOXIfEndPar2NotInCutKid1() {
		
		int [] tour1 = new int[] {0,1,4,5,3,2,7,6};
		Chromosome c1 = new Chromosome(fitnessFct, new Solution(8, tour1));
		
		int [] tour2 = new int[] {0,3,7,2,5,4,6,1};
		Chromosome c2 = new Chromosome(fitnessFct, new Solution(8, tour2));
		
		PathReprCrossover crossover = new CrossoverOX(fitnessFct, c1, c2, 2, 6);
		
		int [] expected = new int[] {0,6,4,5,3,2,7,1};
		Printer.printArray(crossover.getKid1().getGenesInPath());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenesInPath());
	}

}
