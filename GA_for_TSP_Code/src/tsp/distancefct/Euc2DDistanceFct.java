package tsp.distancefct;

/**
 * Represents a distance function which calculates the
 * Euclidean distances between the cities.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class Euc2DDistanceFct extends DistanceFunction {


	@Override
	public double[][] fillDistTable(double[][] coordinates, int dimOfDistTable) {
		
		if(coordinates.length != dimOfDistTable) {
			throw new IllegalArgumentException("Inconsistent dimension!");
		}
		
		double[][] distances = new double[dimOfDistTable][dimOfDistTable];
		
		for (int i = 0; i < dimOfDistTable; i++) {
			for (int j = 0; j < dimOfDistTable; j++) {
				if(i != j) {
					double diffX = Math.abs(coordinates[i][0] - coordinates[j][0]);
					double diffY = Math.abs(coordinates[i][1] - coordinates[j][1]);
					
					double result = Math.hypot(diffX, diffY);
					
					distances[i][j] = result;					
				} else {
					distances[i][j] = 0;
				}				
				
			}
		}
		return distances;
	}

}
