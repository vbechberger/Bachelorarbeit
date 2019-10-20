package genetic;

public class DistanceTable extends FitnessFunction{
	
	private int dimension = -1;
	
	private double[][] distances;

	public DistanceTable(int[][] coordinates, int dimension) {
		setDimension(dimension);
		load(coordinates);
	}
	
	
	private void load(int[][] coordinates) {
		
		distances = new double[dimension][dimension];
		
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if(i != j) {
					double diffX = Math.abs(coordinates[i][0] - coordinates[j][0]);
					double diffY = Math.abs(coordinates[i][1] - coordinates[j][1]);
					
					//Math.hypot() prevents intermediate overflow or underflow
					double result = Math.hypot(diffX, diffY);
					
					distances[i][j] = result;					
				} else {
					distances[i][j] = 0;
				}				
				
			}
		}				
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

}
