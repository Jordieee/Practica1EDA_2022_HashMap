package p1;

/**
 * Map Interface implemented by HashMap class
 * 
 * @author Jordi Linares
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> extends Iterable<K> {
	/**
	 * Puts a node (element) into the hashmap
	 * 
	 * @param key
	 * @param value
	 */
	void put(K key, V value);

	/**
	 * Gets a value given a key
	 * 
	 * @param key
	 * @return returns value if the key is found, null if it isn't
	 */
	V get(K key);

	/**
	 * Removes a node given a key and returns its value
	 * 
	 * @param key
	 * @return returns value if the key is found, null if it isn't
	 */
	V remove(K key);

	/**
	 * Checks if the hashmap has the given key
	 * 
	 * @param key
	 * @return returns true if it contains, false if it doesn't
	 */
	boolean contains(K key);

	/**
	 * Size getter
	 * 
	 * @return returns size
	 */
	int size();

	/**
	 * Checks if hashmap is empty
	 * 
	 * @return returns true if hashmap is empty, false if it isn't
	 */
	boolean isEmpty();

	/**
	 * Sets hashmap to null
	 */
	void clear();
}
