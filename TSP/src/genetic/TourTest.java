package genetic;

import org.junit.Assert;
import org.junit.Test;

public class TourTest {
	
	
	@Test
	public void testAdjToPath() {
		int[] adjRepr = new int[]{1, 4, 0, 2, 3};
		
		AdjTour adjTour = new AdjTour(5, adjRepr);		
		
		int [] expectedPath = new int[]{0, 1, 4, 3, 2};
		
		Assert.assertArrayEquals(expectedPath, adjTour.transformToPathTour().tour);
	}
	
	@Test
	public void testPathToAdj() {
		
		int[] pathRepr = new int[]{0, 1, 4, 3, 2};
		
		PathTour pathTour = new PathTour(5, pathRepr);	
		
		int [] expectedPath = new int[]{1, 4, 0, 2, 3};
		
		Assert.assertArrayEquals(expectedPath, pathTour.transformIntoAdj().tour);
	}
	
	@Test	
	public void chechIfAdjTourIsHamiltonCycle() {
		int[] adjRepr = new int[]{1, 4, 0, 2, 3};
		
		new AdjTour(5, adjRepr);		
	}
	
	@Test(expected = IllegalArgumentException.class)	
	public void chechIfAdjTourIsNotHamiltonCycle() {
		int[] adjRepr = new int[]{3, 4, 0, 2, 1};
		
		new AdjTour(5, adjRepr);		
	}

}
