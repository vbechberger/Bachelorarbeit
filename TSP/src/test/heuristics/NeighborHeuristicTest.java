package test.heuristics;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import heuristics.DoubleNearestNeighbor;
import heuristics.NearestNeighbor;
import heuristics.NeighborHeuristic;
import util.Printer;

public class NeighborHeuristicTest {
	
	private NeighborHeuristic nh;
	private static double distances[][];

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		distances = new double[][] {{0, 91, 80, 259, 70, 121},
			   						{91, 0, 77, 175, 27, 84}, 
			   						{80, 77, 0, 232, 47, 29}, 
			   						{259, 175, 232, 0, 189, 236}, 
			   						{70, 27, 47, 189, 0, 55}, 
			   						{121, 84, 29, 236, 55, 0}};
		
	}

	@Test
	public void testNearestNeighbor() {
											   
		nh = new NearestNeighbor(distances, 2);
		
		int [] expectedTour = new int[]{2, 5, 4, 1, 0, 3};
		Printer.printArray(nh.getTour());
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}
	
	@Test
	public void testDoubleNearestNeighbor() {
											   
		nh = new DoubleNearestNeighbor(distances, 2);
		
		int [] expectedTour = new int[]{2, 4, 1, 0, 3, 5};
		Printer.printArray(nh.getTour());
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}

}
