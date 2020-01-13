package test.crossover;

//import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import genetic.Chromosome;
import genetic.FitnessFunction;
import genetic.Solution;
import genetic.crossover.AlternateEdgesX;
import genetic.crossover.Crossover;
import test.util.DummyFitnessFct;
//import util.Printer;


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
		
		crossover = new AlternateEdgesX(fitnessFct, c1, c2, 0);
		
		/*for(int i = 0; i < 10000; i ++) {
			((AlternateEdgesX)crossover).setRandom(i);
			Chromosome kid1 = crossover.getKid1();
			int [] expected = new int[] {1,4,0,2,3};
			if(Arrays.equals(expected, kid1.getGenesInPath())) {
				Printer.printInt(i);
			}
		}*/
		
		
		//Printer.printString("not found");
		
		
		
		((AlternateEdgesX)crossover).setRandom(0);
		
		Chromosome kid1 = crossover.getKid();
		//kid1.print();
		
		int [] expectedPath = new int[] {0, 1, 4, 2, 3};
		//int [] expected = new int[] {1,4,3,0,2};
		Assert.assertArrayEquals(expectedPath, kid1.getGenesAsArray());
	}
	

	@Test	
	public void testIfFirstCityIsGivenAs0WithDeterministicFlow() {
		
		fitnessFct = new DummyFitnessFct(6);
		
		int [] path1 = new int[] {0, 1, 2, 3, 4, 5};	
		c1 = new Chromosome(fitnessFct, new Solution(6, path1));
		
		int [] path2 = new int[] {0, 1, 4, 3, 5, 2};
		c2 = new Chromosome(fitnessFct, new Solution(6, path2));
		
		crossover = new AlternateEdgesX(fitnessFct, c1, c2, 0);
		
		int [] expectedPath = new int[] {0, 1, 4, 5, 2, 3};
		//int [] expected = new int[] {1,4,3,0,5,2};
		Chromosome kid1 = crossover.getKid();
		//kid1.print();
		Assert.assertArrayEquals(expectedPath, kid1.getGenesAsArray());
	}
	
	@Test	
	public void testIfFirstCityIsNotGivenWithDeterministicFlow() {
		fitnessFct = new DummyFitnessFct(6);
		
		int [] path1 = new int[] {0, 1, 2, 3, 4, 5};
		c1 = new Chromosome(fitnessFct, new Solution(6, path1));
		
		int [] path2 = new int[] {0, 1, 4, 3, 5, 2};
		c2 = new Chromosome(fitnessFct, new Solution(6, path2));
		
		crossover = new AlternateEdgesX(fitnessFct, c1, c2);
		
		//set the firstIndex = 2 (it happens with rand = 42)
		((AlternateEdgesX)crossover).setRandom(42);
		
		int [] expectedPath = new int[] {0, 1, 4, 2, 3, 5};
		Chromosome kid = crossover.getKid();
		//kid1.print();
		Assert.assertArrayEquals(expectedPath, kid.getGenesAsArray());
		
	}
	


}
