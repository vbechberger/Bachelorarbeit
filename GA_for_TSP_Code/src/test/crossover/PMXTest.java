package test.crossover;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.crossover.Crossover;
import genetic.crossover.PMX;
import tsp.Solution;

public class PMXTest {
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

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testPMXKid1CutMiddle() {
		
		crossover = new PMX(fitnessFct, c1, c2, 2, 4);		
		
		int [] expected = new int[] {0,2,4,5,3,1,6,7};
		
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}
	
	@Test
	public void testPMXKid2CutMiddle() {
		
		crossover = new PMX(fitnessFct, c2, c1, 2, 4);		
		
		int [] expected = new int[] {0,4,1,2,5,3,7,6};
		
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}
	
	@Test
	public void testPMXKid1IndexEnd() {
		
		crossover = new PMX(fitnessFct, c1, c2, 5, 7);		
		
		int [] expected = new int[] {0,3,1,4,5,2,7,6};
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}
	
	@Test
	public void testPMXKid2IndexEnd() {
		
		crossover = new PMX(fitnessFct, c2, c1, 5, 7);		
		
		int [] expected = new int[] {0,1,2,5,3,4,6,7};
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}
	
	@Test
	public void testPMXKid1IndexStart() {
		
		crossover = new PMX(fitnessFct, c1, c2, 0, 2);		
		
		int [] expected = new int[] {0,1,4,2,5,3,6,7};
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}
	
	@Test
	public void testPMXKid2IndexStart() {
		
		crossover = new PMX(fitnessFct, c2, c1, 0, 2);		
		
		int [] expected = new int[] {0,3,1,5,4,2,7,6};
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());		
	}
	
	
	@Test
	public void testPMXKid1MultReplacement() {
		
		
		FitnessFunction fitnessFct = new DummyFitnessFct(6);
		
		Chromosome c1 = new Chromosome(fitnessFct, new Solution(6, new int[] {0,5,1,2,4,3}));
		Chromosome c2 = new Chromosome(fitnessFct, new Solution(6, new int[] {1,2,5,4,3,0}));
		
		
		
		crossover = new PMX(fitnessFct, c1, c2, 1, 3);		
		
		int [] expected = new int[] {4,5,1,2,3,0};
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}
	
	@Test
	public void testPMXKid2MultReplacement() {
		
		FitnessFunction fitnessFct = new DummyFitnessFct(6);
		
		Chromosome c1 = new Chromosome(fitnessFct, new Solution(6, new int[] {0,5,1,2,4,3}));
		Chromosome c2 = new Chromosome(fitnessFct, new Solution(6, new int[] {1,2,5,4,3,0}));
		
		
		crossover = new PMX(fitnessFct, c2, c1, 1, 3);		
		
		int [] expected = new int[] {0,2,5,4,1,3};
		Assert.assertArrayEquals(expected, crossover.getOffspring().getGenesAsArray());
		
	}

}
