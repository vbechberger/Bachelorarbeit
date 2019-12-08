package test.heuristics;


import org.junit.Assert;
import org.junit.Test;

import heuristics.ConstructionHeuristic;
import heuristics.DistanceInsertion;
import heuristics.DistanceInsertionType;
import util.Printer;

public class NearestInsertionTest {
	
	private ConstructionHeuristic heuristic;
	//private static double distances[][];


	@Test
	public void testNearestInsertion1() {
		
		double[][] distances = new double[][] {{0, 91, 80, 259, 70, 121},
											   {91, 0, 77, 175, 27, 84}, 
											   {80, 77, 0, 232, 47, 29}, 
											   {259, 175, 232, 0, 189, 236}, 
											   {70, 27, 47, 189, 0, 55}, 
											   {121, 84, 29, 236, 55, 0}};
											   
		heuristic = new DistanceInsertion(DistanceInsertionType.NEAREST, distances, 2);
		
		int [] expectedTour = new int[]{2, 0, 4, 1, 3, 5};
		Printer.printArray(heuristic.getTour());
		
		Assert.assertArrayEquals(expectedTour, heuristic.getTour());
	}
	
	
	@Test
	public void testNearestInsertion2() {
		
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
			   								   {8, 0, 6, 7, 10}, 
			   								   {4, 6, 0, 5, 6}, 
			   								   {9, 7, 5, 0, 4}, 
			   								   {9, 10, 6, 4, 0}}; 
											   
		heuristic = new DistanceInsertion(DistanceInsertionType.NEAREST, distances, 0);
		
		int [] expectedTour = new int[]{0, 4, 3, 1, 2};
		Printer.printArray(heuristic.getTour());
		
		Assert.assertArrayEquals(expectedTour, heuristic.getTour());
	}
	
	

}
