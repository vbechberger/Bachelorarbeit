package test.heuristics;


import org.junit.Assert;
import org.junit.Test;

import heuristics.ConstructionHeuristic;
import heuristics.DistanceInsertion;
import heuristics.DistanceInsertionType;
import util.Printer;

public class FarthestInsertionTest {
private ConstructionHeuristic heuristic;
	

	@Test
	public void testFarthestInsertion1() {
		
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
			   								   {8, 0, 6, 7, 10}, 
			   								   {4, 6, 0, 5, 6}, 
			   								   {9, 7, 5, 0, 4}, 
			   								   {9, 10, 6, 4, 0}}; 
		
		heuristic = new DistanceInsertion(distances, 0, DistanceInsertionType.FARTHEST);
		
		int [] expectedTour = new int[]{0, 1, 3, 4, 2};
		Printer.printArray(heuristic.getTour());
		
		Assert.assertArrayEquals(expectedTour, heuristic.getTour());
	}

}
