package test.crossover;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.*;
import genetic.crossover.Crossover;
import genetic.crossover.CrossoverOBX;
import util.*;

public class OBXTest {
	
	private Crossover crossover;
	
	private static Chromosome c1;
	private static Chromosome c2;
	private static ArrayList<Integer> indices = new ArrayList<Integer>();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		int [] tour1 = new int[] {1,2,5,6,4,3,8,7};
		c1 = new Chromosome(tour1);
		
		int [] tour2 = new int[] {1,4,2,3,6,5,7,8};
		c2 = new Chromosome(tour2);
		
		indices.add(2);
		indices.add(4);
		indices.add(5);
	}

	@Test
	public void testOBXKid1() {
		
		crossover = new CrossoverOBX(c1, c2, indices);
		crossover.start();
		
		int [] expected = new int[] {1,5,2,4,6,3,7,8};
		Printer.printArray(crossover.getKid1().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid1().getGenes());
		
	}
	
	@Test
	public void testOBXKid2() {
		
		crossover = new CrossoverOBX(c1, c2, indices);
		crossover.start();
		
		int [] expected = new int[] {1,2,6,5,4,3,8,7};
		Printer.printArray(crossover.getKid2().getGenes());
		Assert.assertArrayEquals(expected, crossover.getKid2().getGenes());
		
	}

}
