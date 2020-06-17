package test.crossover;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.*;
import genetic.crossover.Crossover;
import genetic.crossover.OBX;
import tsp.Solution;
import util.*;

public class OBXTest {
	

	private static FitnessFunction fitnessFct;
	
	private Crossover crossover;
	
	private static Chromosome c1;
	private static Chromosome c2;
	private static Set<Integer> indices = new HashSet<Integer>();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		fitnessFct = new DummyFitnessFct(8);
		
		int [] tour1 = new int[] {0,1,4,5,3,2,7,6};
		c1 = new Chromosome(fitnessFct, new Solution(8, tour1));
		
		int [] tour2 = new int[] {0,3,1,2,5,4,6,7};
		c2 = new Chromosome(fitnessFct, new Solution(8, tour2));
		
		indices.add(2);
		indices.add(4);
		indices.add(5);
	}

	@Test
	public void testOBXKid1() {
		
		crossover = new OBX(fitnessFct, c1, c2, indices);
		
		int [] expected = new int[] {0,4,1,3,5,2,6,7};
		Printer.printArray(crossover.getOffspring().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}
	
	@Test
	public void testOBXKid2() {
		
		crossover = new OBX(fitnessFct, c2, c1, indices);
		
		int [] expected = new int[] {0,1,5,4,3,2,7,6};
		Printer.printArray(crossover.getOffspring().getGenesAsArray());
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}

}
