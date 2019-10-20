package genetic;

import java.util.ArrayList;
import java.util.Collections;

public class TSPInstance {
	
	private String name;	
	
	private String comment;
	
	private InstanceType type;
	
	private int dimension = -1;
	
	private EdgeWeightType edgeWeightType;
	
	private DistanceTable distanceTable;
	
	private int[][] coordinates;

	public TSPInstance(String name, String comment, InstanceType type,
						int dimension, int[][] coordinates, EdgeWeightType edgeWeightType) {
		this.name = name;
		this.comment = comment;
		this.type = type;
		setDimension(dimension);
		this.coordinates = coordinates;
		this.edgeWeightType = edgeWeightType;
		distanceTable = new DistanceTable(this.coordinates, this.dimension);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	




	/**Getter methods*/

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public InstanceType getType() {
		return type;
	}

	public int getDimension() {
		return dimension;
	}
	
	public int[][] getCoordinates() {
		return coordinates;
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

	public void setType(InstanceType type) {
		this.type = type;
	}

	public void setDimension(int dimension) {
		if (dimension <= 0) {
			throw new IllegalArgumentException("The dimension has to be positive!");			
		}
		this.dimension = dimension;
	}
	
	public void setCoordinates(int[][] coordinates) {
		this.coordinates = coordinates;
	}

	public void setEdgeWeightType(EdgeWeightType edgeWeightType) {
		this.edgeWeightType = edgeWeightType;
	}
	
	/*public boolean isValid() {
		
		
		ArrayList<Integer> tempTour = new ArrayList<Integer>();

		for (int i = 0; i < tour.size(); i++) {
			tempTour.add(tour.get(i));
		}
		Collections.sort(tempTour);

		for (int i = 0; i < tempTour.size(); i++) {
			if (tempTour.get(i) != i) {
				throw new IllegalStateException("The city " + i + " is missed!");
			}
		}
		tempTour = null;

		System.out.println("The instance is valid!");
		return true;

	}*/
	
	
}
