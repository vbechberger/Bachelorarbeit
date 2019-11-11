package genetic.distancefct;

/**
 * The edge weight of the type ATT corresponds 
 * to a special “pseudo-Euclidean” distance function.
 * @author valeriyabechberger
 *
 */
public class ATTDistanceFct extends DistanceFunction {

	@Override
	public double[][] fillDistTable(double[][] coordinates, int dimOfDistTable) {
		
		if(coordinates.length != dimOfDistTable) {
			throw new IllegalArgumentException("TODO: make comment");
		}
		
		double[][] distances = new double[dimOfDistTable][dimOfDistTable];
		
		for(int i = 0; i < dimOfDistTable; i ++) {
			for(int j = 0; j < dimOfDistTable; j++) {
				
				if(i != j) {
					
					//calculate differences between points in each dimension
					double diffX = Math.abs(coordinates[i][0] - coordinates[j][0]);
					double diffY = Math.abs(coordinates[i][1] - coordinates[j][1]);
					
					//TODO: To clarify
					double r = Math.sqrt((diffX * diffX + diffY * diffY) / 10.0);
					
					double t = Math.round(r);

					if (t < r) {
						distances[i][j] = t + 1.0;
					} else {
						distances[i][j] = t;
					}
					
				} else {
					distances[i][j] = 0;
				}
			}
		}
		return distances;
	}

}
