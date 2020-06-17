package test.mutation;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import genetic.mutation.MutationType;
public class MutationTypeTest {
	

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeIndex1() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);

		type.computeFirstIndex(random, -1, 0);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeIndex2() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);

		type.computeFirstIndex(random, 0, -1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBothIndicesNegative() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);

		type.computeFirstIndex(random, -1, -1);
		
	}
	
	@Test
	public void testIndex1Equals0() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);

		int index1 = type.computeFirstIndex(random, 0, 0);
		
		Assert.assertTrue(index1 == 0);
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testIndexLastEqualsIndexStartWhichTakesTheLastPosition() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);
		
		type.computeLastIndex(random, 8, 7);
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testIndexMiddleEqualsIndexStartWhichTakesTheLastPosition() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);
		
		type.computeMiddleIndex(random, 8, 7);
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testIndexMiddleErrorWithStartIndexAtPreLastPosition() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);
		
		type.computeMiddleIndex(random, 8, 6);
		
	}
	
	
	@Test
	public void testIndexMiddleCorrectTakesThePrelastPosition() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);
		
		int indexMiddle = type.computeMiddleIndex(random, 8, 5);
		
		Assert.assertTrue(indexMiddle == 6);
		
	}
	@Test
	public void testIndexLastCorrectTakesTheLastPosition() {
		
		MutationType type = MutationType.DISPLACEMENT;
		
		Random random = new Random(1);
		
		int indexLast = type.computeLastIndex(random, 8, 6);
		
		Assert.assertTrue(indexLast == 7);
		
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testTooSmallChromosomeForSteps() {
		
		MutationType type = MutationType.SHIFT;
		
		Random random = new Random(1);
		
		type.setNumberOfSteps(random, 2);
			
	}
	
	
	
	
	
	
	
}
