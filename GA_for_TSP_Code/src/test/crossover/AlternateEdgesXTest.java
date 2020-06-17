package test.crossover;

import java.util.Random;

//import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import genetic.Chromosome;
import genetic.DummyFitnessFct;
import genetic.FitnessFunction;
import genetic.crossover.AlternateEdgesX;
import genetic.crossover.Crossover;
import tsp.Solution;


public class AlternateEdgesXTest {
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;

	
	@Test	
	public void testIfFirstCityIsGivenAs0NonDeterministic() {
		
		fitnessFct = new DummyFitnessFct(5);
		
		int [] path1 = new int[] {0, 1, 2, 3, 4};	
		c1 = new Chromosome(fitnessFct, new Solution(5, path1));
		
		int [] path2 = new int[] {0, 1, 4, 2, 3};
		c2 = new Chromosome(fitnessFct, new Solution(5, path2));
		
		crossover = new AlternateEdgesX(fitnessFct, c1, c2, 0, new Random(0));
				
		Chromosome kid1 = crossover.getOffspring();
		
		int [] expectedPath = new int[] {0, 1, 4, 2, 3};
		Assert.assertArrayEquals(expectedPath, kid1.getGenesAsArray());
	}
	

	@Test	
	public void testIfFirstCityIsGivenAs0WithDeterministicFlow() {
		
		fitnessFct = new DummyFitnessFct(6);
		
		int [] path1 = new int[] {0, 1, 2, 3, 4, 5};	
		c1 = new Chromosome(fitnessFct, new Solution(6, path1));
		
		int [] path2 = new int[] {0, 1, 4, 3, 5, 2};
		c2 = new Chromosome(fitnessFct, new Solution(6, path2));
		
		crossover = new AlternateEdgesX(fitnessFct, c1, c2, 0, new Random(0));
		
		int [] expectedPath = new int[] {0, 1, 4, 5, 2, 3};

		Chromosome kid1 = crossover.getOffspring();

		Assert.assertArrayEquals(expectedPath, kid1.getGenesAsArray());
	}
	


}
