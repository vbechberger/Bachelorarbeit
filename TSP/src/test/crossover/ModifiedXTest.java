package test.crossover;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.crossover.*;
import test.util.DummyFitnessFct;
import util.Printer;

public class ModifiedXTest {
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
	public void testModifiedXMiddleKid1() {
		
		crossover = new CrossoverModifiedX(fitnessFct, c1, c2, 2);
		crossover.start();
		
		int [] expected = new int[] {1,2,4,3,6,5,7,8};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
	}
	
	@Test
	public void testModifiedXMiddleKid2() {
		
		crossover = new CrossoverModifiedX(fitnessFct, c1, c2, 2);
		crossover.start();
		
		int [] expected = new int[] {1,4,2,5,6,3,8,7};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
	}
	
	@Test
	public void testModifiedXStartKid1() {
		
		crossover = new CrossoverModifiedX(fitnessFct, c1, c2, 0);
		crossover.start();
		
		int [] expected = new int[] {1,4,2,3,6,5,7,8};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
	}

	@Test
	public void testModifiedXStartKid2() {
		
		crossover = new CrossoverModifiedX(fitnessFct, c1, c2, 0);
		crossover.start();
		
		int [] expected = new int[] {1,2,5,6,4,3,8,7};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
	}
	
	@Test
	public void testModifiedXEndKid1() {
		
		crossover = new CrossoverModifiedX(fitnessFct, c1, c2, 7);
		crossover.start();
		
		int [] expected = new int[] {1,2,5,6,4,3,8,7};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
	}
	
	@Test
	public void testModifiedXEndKid2() {
		
		crossover = new CrossoverModifiedX(fitnessFct, c1, c2, 7);
		crossover.start();
		
		int [] expected = new int[] {1,4,2,3,6,5,7,8};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
	}
	
}
