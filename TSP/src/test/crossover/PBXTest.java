package test.crossover;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.crossover.Crossover;
import genetic.crossover.CrossoverPBX;
import test.util.DummyFitnessFct;
import util.Printer;

public class PBXTest {
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;
	private static ArrayList<Integer> indices = new ArrayList<Integer>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			
			fitnessFct = new DummyFitnessFct(8);
		
			int [] tour1 = new int[] {1,2,5,6,4,3,8,7};
			c1 = new Chromosome(fitnessFct, tour1);
			
			int [] tour2 = new int[] {1,4,2,3,6,5,7,8};
			c2 = new Chromosome(fitnessFct, tour2);
			
			indices.add(2);
			indices.add(4);
			indices.add(5);
		
	}
	
	@Test
	public void testPBXKid1() {
		
		crossover = new CrossoverPBX(fitnessFct, c1, c2, indices);
		crossover.start();
		
		int [] expected = new int[] {1,2,5,6,4,3,7,8};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
		
	}

	@Test
	public void testPBXKid2() {
		
		crossover = new CrossoverPBX(fitnessFct, c1, c2, indices);
		crossover.start();
		
		int [] expected = new int[] {1,4,2,3,6,5,8,7};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
		
	}
}
