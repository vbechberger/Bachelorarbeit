package test.crossover;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import genetic.Chromosome;
import genetic.DistanceTable;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.crossover.Crossover;
import genetic.crossover.HX;
import test.util.DummyFitnessFct;
//import util.Printer;

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
		
		crossover = new HX(fitnessFct, c1, c2, distTable, 0);
		
		int [] expected = new int[] {0, 1, 3, 2, 4};
		Chromosome kid = crossover.getKid();
		//kid.print();
		Assert.assertArrayEquals(expected, kid.getGenesAsArray());
	}

}
