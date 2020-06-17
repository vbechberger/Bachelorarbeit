package test.crossover;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.crossover.Crossover;
import genetic.crossover.ERX;
import tsp.Solution;

public class ERXTest {
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;
	
	@Test
	public void testERX1() {
		
		fitnessFct = new DummyFitnessFct(5);
		
		int [] tour1 = new int[] {0,2,3,1,4};	
		c1 = new Chromosome(fitnessFct, new Solution(5, tour1));
		
		int [] tour2 = new int[] {3,1,4,2,0};
		c2 = new Chromosome(fitnessFct, new Solution(5, tour2));
		
		Random rand = new Random(1);
		crossover = new ERX(fitnessFct, c1, c2, rand);
		
		int [] expected = new int[] {1,3,2,4,0};
		Chromosome kid1 = crossover.getOffspring();
		kid1.print();
		Assert.assertArrayEquals(expected, kid1.getGenesAsArray());
	}
	
	@Test
	public void testERX2() {
		
		fitnessFct = new DummyFitnessFct(6);
		
		int [] tour1 = new int[] {0, 2, 3, 4, 1, 5};	
		c1 = new Chromosome(fitnessFct, new Solution(6, tour1));
		
		int [] tour2 = new int[] {2, 4, 5, 0, 1, 3};
		c2 = new Chromosome(fitnessFct, new Solution(6, tour2));
		
		Random rand = new Random(1);
		crossover = new ERX(fitnessFct, c1, c2, rand);
		
		int [] expected = new int[] {5, 0, 2, 4, 3, 1};
		Chromosome kid1 = crossover.getOffspring();
		kid1.print();
		Assert.assertArrayEquals(expected, kid1.getGenesAsArray());
	}

}
