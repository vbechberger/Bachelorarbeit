package test.heuristics;


import org.junit.Assert;
import org.junit.Test;

import heuristics.DoubleNearestNeighbor;
import heuristics.NearestNeighbor;
import heuristics.ConstructionHeuristic;

public class NeighborHeuristicTest {
	
	private ConstructionHeuristic nh;

	@Test
	public void testNearestNeighborSixCities() {
		
		double[][] distances = new double[][] {{0, 91, 80, 259, 70, 121},
				{91, 0, 77, 175, 27, 84}, 
				{80, 77, 0, 232, 47, 29}, 
				{259, 175, 232, 0, 189, 236}, 
				{70, 27, 47, 189, 0, 55}, 
				{121, 84, 29, 236, 55, 0}};
				
		nh = new NearestNeighbor(distances, 2);
		
		int [] expectedTour = new int[]{2, 5, 4, 1, 0, 3};
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}
	
	@Test
	public void testDoubleNearestNeighborSixCities() {
		
		double[][] distances = new double[][] {{0, 91, 80, 259, 70, 121},
			{91, 0, 77, 175, 27, 84}, 
			{80, 77, 0, 232, 47, 29}, 
			{259, 175, 232, 0, 189, 236}, 
			{70, 27, 47, 189, 0, 55}, 
			{121, 84, 29, 236, 55, 0}};
											   
		nh = new DoubleNearestNeighbor(distances, 2);
		
		/*example from the script. We get the same tour. The tour 
		 * starting from city 2 is read in script from another 
		 * side there*/
		int [] expectedTour = new int[]{2, 5, 3, 0, 1, 4};
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}
	
	@Test
	public void testNearestNeighborFiveCitiesStart0() {
					
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
											   {8, 0, 6, 7, 10}, 
											   {4, 6, 0, 5, 6}, 
											   {9, 7, 5, 0, 4}, 
											   {9, 10, 6, 4, 0}}; 
											   
				
		nh = new NearestNeighbor(distances, 0);
		
		int [] expectedTour = new int[]{0, 2, 3, 4, 1};
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}
	

	@Test
	public void testNearestNeighborFiveCitiesStart2() {
					
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
											   {8, 0, 6, 7, 10}, 
											   {4, 6, 0, 5, 6}, 
											   {9, 7, 5, 0, 4}, 
											   {9, 10, 6, 4, 0}}; 
											   
				
		nh = new NearestNeighbor(distances, 2);
		
		int [] expectedTour = new int[]{2, 0, 1, 3, 4};
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}
	
	
	@Test
	public void testDoubleNearestNeighborFiveCitiesStart0() {
					
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
											   {8, 0, 6, 7, 10}, 
											   {4, 6, 0, 5, 6}, 
											   {9, 7, 5, 0, 4}, 
											   {9, 10, 6, 4, 0}}; 
											   
				
		nh = new DoubleNearestNeighbor(distances, 0);
		
		int [] expectedTour = new int[]{0, 2, 3, 4, 1};
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}
	
	@Test
	public void testDoubleNearestNeighborFiveCitiesStart2() {
					
		double[][] distances = new double[][] {{0, 8, 4, 9, 9},
											   {8, 0, 6, 7, 10}, 
											   {4, 6, 0, 5, 6}, 
											   {9, 7, 5, 0, 4}, 
											   {9, 10, 6, 4, 0}}; 
											   
				
		nh = new DoubleNearestNeighbor(distances, 2);
	
		int [] expectedTour = new int[]{2, 0, 1, 4, 3};
		
		Assert.assertArrayEquals(expectedTour, nh.getTour());
	}
	
	
	
	

}
