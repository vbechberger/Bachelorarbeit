package test.selection;

import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import genetic.Chromosome;
import genetic.Population;
import genetic.selection.TournamentSelection;
import tsp.DistanceTable;
import tsp.Solution;

public class TournamentSelectionTest {
	
	private static TournamentSelection game;
	private static Population pop;
	private static Chromosome c1;
	private static Chromosome c2;
	private static Chromosome c3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
			   								   {8, 0, 6, 7, 10}, 
			   								   {4, 6, 0, 5, 6}, 
			   								   {9, 7, 5, 0, 4}, 
			   								   {9, 10, 6, 4, 0}};
			   
		DistanceTable distTable = new DistanceTable(distances, distances.length);
		
		pop = new Population(3);
		
		int [] tour1 = new int[] {0,1,2,3,4};		
		c1 = new Chromosome(distTable, new Solution(5, tour1));
		pop.addChromosome(c1);
				
		int [] tour2 = new int[] {3,2,1,4,0};
		c2 = new Chromosome(distTable, new Solution(5, tour2));
		 
		pop.addChromosome(c2); 
		
		int [] tour3 = new int[] {1,4,2,3,0};
		c3 = new Chromosome(distTable, new Solution(5, tour3));
		
		pop.addChromosome(c3);
		
		
	}

	@Test
	public void test() {
		Random random = new Random(42);
		game = new TournamentSelection(random, pop);
		game.makeParticipantsNumberFixed(3);
		
		
		Assert.assertEquals(game.getWinner(), c1);
	}


}
