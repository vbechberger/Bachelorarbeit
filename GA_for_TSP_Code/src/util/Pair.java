package util;

/**
 * Represents a generic pair.
 * @author Valeriya Bechberger(vsakharova@uos.de)
 *
 * @version 2.0
 * @since 2020-06-11
 *
 * @param <T> the type of the first value.
 * @param <K> the type of the second value.
 */
public class Pair<T, K> {
	
	/**
	 * The first element of the pair.
	 */
	private T first;
	
	/**
	 * The second element of the pair.
	 */
	private K second;

	/**
	 * Constructor.
	 * 
	 * @param first is the given value for the first element.
	 * @param second is the given value for the second element.
	 */
	public Pair(T first, K second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * 
	 * @return the first element of the pair.
	 */
	public T getFirst() {
		return this.first;
	}
	
	/**
	 * 
	 * @return the second element of the pair.
	 */
	public K getSecond() {
		return this.second;
	}

}
