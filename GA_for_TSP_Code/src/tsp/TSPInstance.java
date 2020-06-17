package tsp;

/**
 * Represents a TSP instance from the TSPLIB.
 * 
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class TSPInstance {
	
	/**Name of the TSP instance*/
	private String name;	
	
	/**A comment to the TSP instance*/
	private String comment;
	
	/**The dimension of the TSP instance
	 * which corresponds to the number of the cities in the tour.*/
	private int dimension = -1;
	
	/**
	 * The type of the edge weights 
	 * which defines which type of distance function 
	 * will be used.
	 */
	private EdgeWeightType edgeWeightType;
	
	/**
	 * The distance table with the distances between all the cities.
	 */	
	private DistanceTable distanceTable;
	
	/**
	 * Constructor.
	 * 
	 * Creates a TSP instance.
	 * @param name is the name of the TSP instance.
	 * @param comment is the given comments.
	 * @param dimension is the dimension.
	 * @param data is the given data about the cities of the tour.
	 * @param edgeWeightType is the type of the edge weights.
	 */
	public TSPInstance(String name, String comment,
						int dimension, double[][] data, EdgeWeightType edgeWeightType) {
		this.name = name;
		this.comment = comment;
		setDimension(dimension);
		this.edgeWeightType = edgeWeightType;
		
		/*
		 * In case of EXPLICIT edge weight type, the distances (weights) 
		 * are given explicitly in the form of the distance matrix.
		 */
		if(edgeWeightType.equals(EdgeWeightType.EXPLICIT)) {
			/*So, we can just copy them*/
			distanceTable = new DistanceTable(data, this.dimension);;
		} else {
			
			/*In other cases, we have to fill in the distance table
			 * using a special distance function
			 * which is defined by the type of edge weights.
			 */
			distanceTable = new DistanceTable(edgeWeightType.getDistTableFiller(), data, this.dimension);		
		}		
	}
	

	/**Getter methods*/

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public int getDimension() {
		return dimension;
	}

	public EdgeWeightType getEdgeWeightType() {
		return edgeWeightType;
	}

	public DistanceTable getDistanceTable() {
		return distanceTable;
	}
	
	
	/**Setter methods*/

	public void setName(String name) {
		this.name = name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDimension(int dimension) {
		if (dimension <= 0) {
			throw new IllegalArgumentException("The dimension has to be positive!");			
		}
		this.dimension = dimension;
	}

	public void setEdgeWeightType(EdgeWeightType edgeWeightType) {
		this.edgeWeightType = edgeWeightType;
	}
	
}
