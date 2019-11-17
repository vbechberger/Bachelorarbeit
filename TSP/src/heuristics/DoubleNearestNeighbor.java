package heuristics;

import java.util.LinkedList;
import util.SaveCopy;

public class DoubleNearestNeighbor extends NeighborHeuristic{
	
	public DoubleNearestNeighbor(double[][] distances, int startCityNumber) {
		super(distances, startCityNumber);
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
		//not interface Deque or List, because i need the functionality of both 
		//at the same time
		LinkedList<Integer> tempTour = new LinkedList<Integer>();
		
		tempTour.add(startCityNumber);
		usedCities.add(startCityNumber);
		 
		int firstCityOfTour = startCityNumber;
		int lastCityOfTour = startCityNumber;
		int nextCity = startCityNumber;
		//count how many cities are already in the tour
		int count = 1;
		while(count < tour.length) {
			
			/*find the city which is the nearest to the
			 * city, which was visited first*/
			int cityNearestToFirstCity = findTheNearestCityTo(firstCityOfTour);
			
			/*find the city which is the nearest to the
			 * city, which was visited last*/
			int cityNearestToLastCity = findTheNearestCityTo(lastCityOfTour);
			
			double firstDistance = distances[firstCityOfTour][cityNearestToFirstCity];
			double secDistance = distances[lastCityOfTour][cityNearestToLastCity];
			
			/*if the distances are equal, put at the beginning*/
			if(firstDistance <= secDistance) {
				
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
	
		
		//Now the tour does not necessary starts at the start city number
		//if not, it has to be shifted
		
		int index = tempTour.indexOf(startCityNumber);
			
		if(index != 0) {			 
			shiftTourToStartWithChosenStartCity(index, tempTour);							 
		} else {
			SaveCopy.copy(tour, tempTour);
		}
		
	}

	private void shiftTourToStartWithChosenStartCity(int index, LinkedList<Integer> tempTour) {
			 LinkedList<Integer> list1, list2;
			 
			 //this part goes at the beginning of the result tour
			 list1 = new LinkedList<Integer>(tempTour.subList(index, tempTour.size()));
			 
			 //this part goes at the rest part
			 list2 = new LinkedList<Integer>(tempTour.subList(0, index));
			 
			 for(int i = 0; i < list1.size(); i++) {
				 tour[i] = list1.get(i);				 
			 }
			 
			 int pos = tour.length - index;
			 int it = 0;
			 for(int i = pos; i < tour.length; i++) {
				 tour[i] = list2.get(it);
				 it++;
			 }			 		
	}

}
