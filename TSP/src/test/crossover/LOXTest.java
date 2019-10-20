package test.crossover;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.crossover.Crossover;
import genetic.crossover.CrossoverLOX;
import test.util.DummyFitnessFct;
import util.Printer;

public class LOXTest {
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		fitnessFct = new DummyFitnessFct(8);
		
		int [] tour1 = new int[] {1,2,5,6,4,3,8,7};
		c1 = new Chromosome(fitnessFct, tour1);
		
		int [] tour2 = new int[] {1,4,2,3,6,5,7,8};
		c2 = new Chromosome(fitnessFct, tour2);
		
	}
	
	@Test
	public void testLOXMiddleKid1() {
		
		crossover = new CrossoverLOX(fitnessFct, c1, c2, 2, 4);
		crossover.start();
		
		int [] expected = new int[] {1,2,5,6,4,3,7,8};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
	}
	
	@Test
	public void testLOXMiddleKid2() {
		
		crossover = new CrossoverLOX(fitnessFct, c1, c2, 2, 4);
		crossover.start();
		
		int [] expected = new int[] {1,5,2,3,6,4,8,7};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
	}
	
	@Test
	public void testLOXEndKid1() {
		
		crossover = new CrossoverLOX(fitnessFct, c1, c2, 5, 7);
		crossover.start();
		
		int [] expected = new int[] {1,4,2,6,5,3,8,7};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
	}
	
	@Test
	public void testLOXEndKid2() {
		
		crossover = new CrossoverLOX(fitnessFct, c1, c2, 5, 7);
		crossover.start();
		
		int [] expected = new int[] {1,2,6,4,3,5,7,8};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
	}
	
	@Test
	public void testLOXStartKid1() {
		
		crossover = new CrossoverLOX(fitnessFct, c1, c2, 0, 2);
		crossover.start();
		
		int [] expected = new int[] {1,2,5,4,3,6,7,8};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
	}
	
	@Test
	public void testLOXStartKid2() {
		
		crossover = new CrossoverLOX(fitnessFct, c1, c2, 0, 2);
		crossover.start();
		
		int [] expected = new int[] {1,4,2,5,6,3,8,7};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
	}

}
