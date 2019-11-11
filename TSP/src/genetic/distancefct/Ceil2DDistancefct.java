package genetic.distancefct;

/**
 * Represents an ceil2D edge weight type, which requires 
 * that the 2-dimensional Euclidean distances 
 * is rounded up to the next integer
 * @author valeriyabechberger
 *
 */
public class Ceil2DDistancefct extends Euc2DDistanceFct {

	@Override
	public double[][] fillDistTable(double[][] coordinates, int dimOfDistTable) {
		
		if(coordinates.length != dimOfDistTable) {
			throw new IllegalArgumentException("TODO: make comment");
		}
		
		double[][] temp = super.fillDistTable(coordinates, dimOfDistTable);
		
		double[][] distances = new double[dimOfDistTable][dimOfDistTable];
		
		for(int i = 0; i < dimOfDistTable; i ++) {
			for(int j = 0; j < dimOfDistTable; j++) {
				if (i != j) {
					distances[i][j] = Math.ceil(temp[i][j]);
				} else {
					distances[i][j] = 0;
				}				
			}
		}	
		return distances;
	}

}
