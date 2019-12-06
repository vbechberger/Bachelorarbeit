package util;

import java.util.List;

public class Checker {
	
	public static void isNullOrEmpty(List<Integer> list) {
		if(list == null || list.size() == 0) {
			throw new IllegalArgumentException("The list is null or empty!");
		} 		
	}

}
