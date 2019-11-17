package genetic;

import util.SaveCopy;

public class DistanceTable extends FitnessFunction{
	
	private int dimension = -1;
	
	private double[][] distances;

	public DistanceTable(DistTableFiller distTableFiller, double[][] coordinates, int dimension) {
		
		distances = distTableFiller.fillDistTable(coordinates, dimension);
		setDimension(dimension);
	}
	
	public DistanceTable(double[][] weights, int dimension) {

		distances = new double[dimension][dimension];
		SaveCopy.copy(distances, weights);
		setDimension(dimension);
		//Printer.printArray2D(weights);
		//Printer.printArray2D(distances);
	}
	
	
	private void setDimension(int dimension) {
		if (dimension <= 0) {
			throw new IllegalArgumentException("The dimension has to be positive!");			
		}		
		this.dimension = dimension;
	}



	public double[][] getDistances() {
		return distances;
	}
	
	public double getDistanceBtwCities(int firstCity, int secCity) {
		return distances[firstCity][secCity];
	}

	private double calcDistanceOfTour(int[] tour) {
		
		double sum = 0;
		
		for (int i = 0; i < tour.length - 1; i++) {
			int start = tour[i];
			int end = tour[i + 1];
			sum = sum + distances[start][end];
		}
		
		int start = tour[tour.length - 1];
		int end = tour[0];
		sum = sum + distances[start][end];	
		
		if (sum <= 0) {
			throw new IllegalStateException("The distance of "
					+ "tour is smaller or equal than 0!");
		}
				
		return sum;
	}


	@Override
	public double calcFitness(int[] genes) {
		return (-1) * calcDistanceOfTour(genes);
	}


	@Override
	public int getDimention() {
		return dimension;
	}
	
	public void printFirstCol() {
		for (int i = 0; i < distances.length; i++) {
			System.out.println(distances[i][0]);
		}
	}

}
