package test.heuristics;


import org.junit.Assert;
import org.junit.Test;

import heuristics.CheapestInsertion;
import heuristics.ConstructionHeuristic;
import util.Printer;

public class CheapestInsertionTest {

	private ConstructionHeuristic heuristic;
	

	@Test
	public void testCheapestInsertion1() {
		
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
			   								   {8, 0, 6, 7, 10}, 
			   								   {4, 6, 0, 5, 6}, 
			   								   {9, 7, 5, 0, 4}, 
			   								   {9, 10, 6, 4, 0}}; 
		
		heuristic = new CheapestInsertion(distances, 0);
		
		int [] expectedTour = new int[]{0, 1, 3, 4, 2};
		Printer.printArray(heuristic.getTour());
		
		Assert.assertArrayEquals(expectedTour, heuristic.getTour());
	}

}
