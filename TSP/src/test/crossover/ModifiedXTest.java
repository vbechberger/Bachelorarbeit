package test.crossover;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.Simulation;
import genetic.crossover.CrossoverType;
import util.Printer;

public class ModifiedXTest {
	private Simulation simulation;
	private static Chromosome c1;
	private static Chromosome c2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int [] tour1 = new int[] {1,2,5,6,4,3,8,7};
		c1 = new Chromosome(tour1);
		
		int [] tour2 = new int[] {1,4,2,3,6,5,7,8};
		c2 = new Chromosome(tour2);
	}

	@Test
	public void testModifiedXMiddleKid1() {
		
		simulation = new Simulation(CrossoverType.MODIFIED, c1, c2, 2);
		simulation.start();
		
		int [] expected = new int[] {1,2,4,3,6,5,7,8};
		Printer.printArray(simulation.getKid1().getGenes());
		Assert.assertArrayEquals(expected, simulation.getKid1().getGenes());
	}
	
	@Test
	public void testModifiedXMiddleKid2() {
		
		simulation = new Simulation(CrossoverType.MODIFIED, c1, c2, 2);
		simulation.start();
		
		int [] expected = new int[] {1,4,2,5,6,3,8,7};
		Printer.printArray(simulation.getKid2().getGenes());
		Assert.assertArrayEquals(expected, simulation.getKid2().getGenes());
	}
	
	@Test
	public void testModifiedXStartKid1() {
		
		simulation = new Simulation(CrossoverType.MODIFIED, c1, c2, 0);
		simulation.start();
		
		int [] expected = new int[] {1,4,2,3,6,5,7,8};
		Printer.printArray(simulation.getKid1().getGenes());
		Assert.assertArrayEquals(expected, simulation.getKid1().getGenes());
	}

	@Test
	public void testModifiedXStartKid2() {
		
		simulation = new Simulation(CrossoverType.MODIFIED, c1, c2, 0);
		simulation.start();
		
		int [] expected = new int[] {1,2,5,6,4,3,8,7};
		Printer.printArray(simulation.getKid2().getGenes());
		Assert.assertArrayEquals(expected, simulation.getKid2().getGenes());
	}
	
	@Test
	public void testModifiedXEndKid1() {
		
		simulation = new Simulation(CrossoverType.MODIFIED, c1, c2, 7);
		simulation.start();
		
		int [] expected = new int[] {1,2,5,6,4,3,8,7};
		Printer.printArray(simulation.getKid1().getGenes());
		Assert.assertArrayEquals(expected, simulation.getKid1().getGenes());
	}
	
	@Test
	public void testModifiedXEndKid2() {
		
		simulation = new Simulation(CrossoverType.MODIFIED, c1, c2, 7);
		simulation.start();
		
		int [] expected = new int[] {1,4,2,3,6,5,7,8};
		Printer.printArray(simulation.getKid2().getGenes());
		Assert.assertArrayEquals(expected, simulation.getKid2().getGenes());
	}
	
}
