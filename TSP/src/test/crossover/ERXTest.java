package test.crossover;

import org.junit.Assert;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.crossover.Crossover;
import genetic.crossover.ERX;
import test.util.DummyFitnessFct;
//import util.Printer;

public class ERXTest {
	private static FitnessFunction fitnessFct;
	private Crossover crossover;
	private static Chromosome c1;
	private static Chromosome c2;

	@Test
	public void testERXWithGivingFirstCity() {
		
		fitnessFct = new DummyFitnessFct(8);
		
		int [] tour1 = new int[] {0,2,4,5,3,1,7,6};	
		c1 = new Chromosome(fitnessFct, new Solution(8, tour1));
		
		int [] tour2 = new int[] {0,3,1,2,5,4,6,7};
		c2 = new Chromosome(fitnessFct, new Solution(8, tour2));
		
		crossover = new ERX(fitnessFct, c1, c2, 0);
		
		//with seed = 1, for this example, the index will be always 0,
		// which corresponds to the last index in the list
		//of potential candidates, because it is sorted reversely
		((ERX)crossover).setRandom(1);
		
		int [] expected = new int[] {0,7,6,4,5,3,1,2};
		Chromosome kid1 = crossover.getKid();
		//kid1.print();
		Assert.assertArrayEquals(expected, kid1.getGenesAsArray());
	}
	
	@Test
	public void testERXWithoutGivingFirstCity() {
		
		fitnessFct = new DummyFitnessFct(5);
		
		int [] tour1 = new int[] {0,2,3,1,4};	
		c1 = new Chromosome(fitnessFct, new Solution(5, tour1));
		
		int [] tour2 = new int[] {3,1,4,2,0};
		c2 = new Chromosome(fitnessFct, new Solution(5, tour2));
		
		crossover = new ERX(fitnessFct, c1, c2);
		
		//with seed = 1, for this example, the index will be always 0,
		// which corresponds to the last index in the list
		//of potential candidates, because it is sorted reversely
		((ERX)crossover).setRandom(1);
		
		int [] expected = new int[] {1,4,2,3,0};
		Chromosome kid1 = crossover.getKid();
		kid1.print();
		Assert.assertArrayEquals(expected, kid1.getGenesAsArray());
	}


}
