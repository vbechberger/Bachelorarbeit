package test.crossover;


import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.crossover.Crossover;
import genetic.crossover.HX;
import tsp.DistanceTable;
import tsp.Solution;

public class HXTest {
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;
	private static DistanceTable distTable;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		distTable = new DistanceTable(new double[][] {{0, 8, 4, 9, 9},
													 {8, 0, 6, 7, 10}, 
													 {4, 6, 0, 5, 6}, 
													 {9, 7, 5, 0, 4}, 
													 {9, 10, 6, 4, 0}}, 5);

	}
	
	@Test	
	public void test() {
		
		fitnessFct = new DummyFitnessFct(5);
		
		int [] path1 = new int[] {0, 1, 3, 2, 4};	
		c1 = new Chromosome(fitnessFct, new Solution(5, path1));
		
		int [] path2 = new int[] {0, 1, 4, 2, 3};
		c2 = new Chromosome(fitnessFct, new Solution(5, path2));
		
		Random rand = new Random();
		
		crossover = new HX(fitnessFct, c1, c2, distTable, 0, rand);
		
		int [] expected = new int[] {0, 1, 3, 2, 4};
		Chromosome kid = crossover.getOffspring();
	
		Assert.assertArrayEquals(expected, kid.getGenesAsArray());
	}

}
