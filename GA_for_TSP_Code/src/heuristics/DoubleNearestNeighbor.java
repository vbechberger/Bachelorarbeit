package heuristics;

import java.util.LinkedList;

import util.Pair;
import util.SafeCopy;

/**
 * Represents the double nearest neighbor heuristic.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 */
public class DoubleNearestNeighbor extends ConstructionHeuristic{
	
	public DoubleNearestNeighbor(double[][] distances, int startCityNumber) {
		super(new NearestStrategy(), distances, startCityNumber);
	}

	@Override
	/**
	 * Looks for the city which is the nearest to the
	 * first city of the tour or to its last city.
	 * The corresponding city will be added before
	 * the first city in the first case 
	 * or after the last city in the second case.
	 * 
	 * @return the tour
	 */
	protected void findTour() {
		

		LinkedList<Integer> tempTour = new LinkedList<Integer>();
		
		tempTour.add(startCityNumber);
		usedCities.add(startCityNumber);
		 
		int firstCityOfTour = startCityNumber;
		int lastCityOfTour = startCityNumber;
		int nextCity = startCityNumber;
		
		//count how many cities are already in the tour
		int count = 1;
		while(count < resultingTour.length) {
			
			/*find the city which is the nearest to the
			 * city, which was visited first*/
			Pair<Integer, Double> cityAndDist1 = findOptNeighborAndDistTo(firstCityOfTour);
			int cityNearestToFirstCity = cityAndDist1.getFirst();
			
			/*find the city which is the nearest to the
			 * city, which was visited last*/
			Pair<Integer, Double> cityAndDist2 = findOptNeighborAndDistTo(lastCityOfTour);
			int cityNearestToLastCity = cityAndDist2.getFirst();
			
			double firstDistance = distances[firstCityOfTour][cityNearestToFirstCity];
			double secDistance = distances[lastCityOfTour][cityNearestToLastCity];
			
			/*if the distances are equal, put it after the last city*/
			if(firstDistance < secDistance) {
				
				nextCity = cityNearestToFirstCity;
				/*Save this city in the tour*/
				tempTour.addFirst(cityNearestToFirstCity);
				
				/*update the first city of the tour*/
				firstCityOfTour = nextCity;
				
			} else {
				nextCity = cityNearestToLastCity;
				/*Save this city in the tour*/
				tempTour.addLast(cityNearestToLastCity);
				
				/*update the last city of the tour*/
				lastCityOfTour = nextCity;
			}
			
			usedCities.add(nextCity);			
			count++;
		}
			
		/*Now the tour does not necessary starts at the start city number
		 *if not, it has to be shifted.
		 */
		
		int index = tempTour.indexOf(startCityNumber);
			
		if(index != 0) {			 
			shiftTourToStartWithChosenStartCity(index, tempTour);							 
		} else {
			SafeCopy.copy(resultingTour, tempTour);
		}		
	}

	/**
	 * Shifts the given tour so that it starts with the specified index.
	 * @param index is the given index.
	 * @param tempTour is the given tour.
	 */
	private void shiftTourToStartWithChosenStartCity(int index, LinkedList<Integer> tempTour) {
			 LinkedList<Integer> list1, list2;
			 
			 //this part goes at the beginning of the result tour
			 list1 = new LinkedList<Integer>(tempTour.subList(index, tempTour.size()));
			 
			 //this part goes at the rest part
			 list2 = new LinkedList<Integer>(tempTour.subList(0, index));
			 
			 for(int i = 0; i < list1.size(); i++) {
				 resultingTour[i] = list1.get(i);				 
			 }
			 
			 int pos = resultingTour.length - index;
			 int it = 0;
			 for(int i = pos; i < resultingTour.length; i++) {
				 resultingTour[i] = list2.get(it);
				 it++;
			 }			 		
	}

}
