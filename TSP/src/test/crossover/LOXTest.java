package test.crossover;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.crossover.Crossover;
import genetic.crossover.LOX;
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
		
		int [] tour1 = new int[] {0,1,4,5,3,2,7,6};
		c1 = new Chromosome(fitnessFct, new Solution(8, tour1));
		
		int [] tour2 = new int[] {0,3,1,2,5,4,6,7};
		c2 = new Chromosome(fitnessFct, new Solution(8, tour2));
		
	}
	
	@Test
	public void testLOXMiddleKid1() {
		
		crossover = new LOX(fitnessFct, c1, c2, 2, 4);
		
		int [] expected = new int[] {0,1,4,5,3,2,6,7};
		Printer.printArray(crossover.getKid().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getKid().getGenesAsArray());
	}
	
	@Test
	public void testLOXMiddleKid2() {
		
		crossover = new LOX(fitnessFct, c2, c1, 2, 4);
		
		int [] expected = new int[] {0,4,1,2,5,3,7,6};
		Printer.printArray(crossover.getKid().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getKid().getGenesAsArray());
	}
	
	@Test
	public void testLOXEndKid1() {
		
		crossover = new LOX(fitnessFct, c1, c2, 5, 7);
		
		int [] expected = new int[] {0,3,1,5,4,2,7,6};
		Printer.printArray(crossover.getKid().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getKid().getGenesAsArray());
	}
	
	@Test
	public void testLOXEndKid2() {
		
		crossover = new LOX(fitnessFct, c2, c1, 5, 7);
		
		int [] expected = new int[] {0,1,5,3,2,4,6,7};
		Printer.printArray(crossover.getKid().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getKid().getGenesAsArray());
	}
	
	@Test
	public void testLOXStartKid1() {
		
		crossover = new LOX(fitnessFct, c1, c2, 0, 2);
		
		int [] expected = new int[] {0,1,4,3,2,5,6,7};
		Printer.printArray(crossover.getKid().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getKid().getGenesAsArray());
	}
	
	@Test
	public void testLOXStartKid2() {
		
		crossover = new LOX(fitnessFct, c2, c1, 0, 2);
		
		int [] expected = new int[] {0,3,1,4,5,2,7,6};
		Printer.printArray(crossover.getKid().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getKid().getGenesAsArray());
	}

}
