package genetic.distancefct;

/**
 * 
 * http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/tsp95.pdf
 * 
 * If the traveling salesman problem is a geographical problem, 
 * then the nodes correspond topoints on the earth and the distance 
 * between two points is their distance on the idealizedsphere 
 * with radius 6378.388 kilometers.
 * 
 * The node coordinates give the geographical latitude and longitude 
 * of the corresponding point on the Earth.
 * Latitude and longitude are given in the formDDD.MM,
 * where DDD are the degrees and MM the minutes. 
 * A positive latitude is assumed to be “North”, 
 * negative latitude means “South”. 
 * Positive longitude means “East”, 
 * negative latitude is assumed to be “West”. 
 * 
 * Calculates the distances (approximately) between points which are given as 
 * the geographical coordinates in degrees.minutes format
 * @author valeriyabechberger
 *
 */
public class GeoDistanceFct extends DistanceFunction {
	
	private final double EARTHRADIUS = 6378.388; 

	@Override
	public double[][] fillDistTable(double[][] coordinates, int dimOfDistTable) {
		
		if(coordinates.length != dimOfDistTable) {
			throw new IllegalArgumentException("TODO: make comment");
		}
		
		double[][] distances = new double[dimOfDistTable][dimOfDistTable];
		
		/**Take latitude coordinates*/
		double[] latitudeInDegrees = copyNteRowIn1DArray(coordinates, 0);
		
		/**Take longitude coordinates*/
		double[] longitudeInDegrees = copyNteRowIn1DArray(coordinates, 1);
				
		/**Convert latitude and longitude data into radians*/
		
		double[] latitudeInRadians = convertCoordInRadians(latitudeInDegrees);		
		double[] longitudeInRadians = convertCoordInRadians(longitudeInDegrees);
		
		/**Compute the distance in km between the nodes i and j*/
		
		for(int i = 0; i < dimOfDistTable; i++) {
			for(int j = 0; j < dimOfDistTable; j++) {
				if(i != j) {
					double q1 = Math.cos(longitudeInRadians[i] - longitudeInRadians[j]);
					double q2 = Math.cos(latitudeInRadians[i] - latitudeInRadians[j]);
					double q3 = Math.cos(latitudeInRadians[i] + latitudeInRadians[j]);
				
					distances[i][j] = EARTHRADIUS * Math.acos( 0.5 * ((1.0 + q1) * q2 - (1.0 - q1) * q3) ) + 1.0;
				} else {
					distances[i][j] = 0;
				}
				 
			}
		}
		
		
		return distances;
	}
	
	
	
	/**
	 * Takes the row with the specified number from the original 2D array. 
	 * @param coordinates The given 2D array with coordinstes.
	 * @param rowNumber The number of the row which is to be copied from the original 2D array.
	 * @return 1D array with coordinates of the specified dimension.
	 */
	private double[] copyNteRowIn1DArray(double[][] coordinates, int rowNumber) {
		
		if(rowNumber >= coordinates[0].length) {
			throw new IllegalArgumentException("The given 2D array has not "
												+ "a row with the specified number: " 
												+ rowNumber);
		}
		
		int dimension = coordinates.length;
		
		double[] array = new double[dimension];
		
		for(int i = 0; i < dimension; i++) {
			array[i] = coordinates[i][rowNumber];
		}
		
		return array;
		
	}
	
	/**
	 * Converts coordinates given in degrees.minutes format into radians.
	 * @param coordinatesInDegrees Coordinates given in degrees.minutes format.
	 * @return 1D array with coordinates in radians.
	 */
	private double[] convertCoordInRadians(double[] coordinatesInDegrees) {
		int dimension = coordinatesInDegrees.length;
		double[] coordinatesInRadians = new double[dimension];
		
		for(int i = 0; i < dimension; i++) {
			
			/**All data is given in degrees.minutes format,
			 * therefore it must be converted into radians.
			 */ 
			  
			 /** First, the coordinates must first be converted into decimal format:
			 * Ddecimal = Degree + Minutes/60 
			 */
			
			int degrees = (int) Math.floor(coordinatesInDegrees[i]);
			//System.out.println("Next degree: " + degrees);
			//int min = (int)((coordinatesInDegrees[i] - degrees) * 100);
			double min = (coordinatesInDegrees[i] % degrees) * 100;
			//System.out.println("Next min: " + min);
			double dDecimal = degrees + min/60.0;
			//System.out.println("////////////////");
			
						
			/** Then convert decimal coordinates into radians 
			 * by multiplying each one by PI/180
			 */
			coordinatesInRadians[i] = Math.PI * dDecimal / 180.0;
					
		}
				
		return coordinatesInRadians;
	}

}
