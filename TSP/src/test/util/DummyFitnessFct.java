package test.util;

import genetic.FitnessFunction;

public class DummyFitnessFct extends FitnessFunction{
	
	private int dimension = -1;
	
	

	public DummyFitnessFct(int dimension) {
		setDimension(dimension);
	}
	
	public double calcFitness(int[] genes) {
		return 0;
	}
	
	public int getDimention() {
		return this.dimension;
	}
	
	private void setDimension(int dimension) {
		if (dimension <= 0) {
			throw new IllegalArgumentException("The dimension has to be positive!");			
		}		
		this.dimension = dimension;
	}

}
