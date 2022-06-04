package p1;

import java.util.Iterator;

/**
 * HashMap Class
 * 
 * @author Jordi Linares
 *
 * @param <K> Generic key
 * @param <V> Generic value
 */
public class HashMap<K, V> implements Map<K, V> {

	// Local Variables, where capacity is the length of the array (nº cubetas), and
	// size is the number of elements (nodes)
	/**
	 * Length of the array of nodes
	 */
	private int capacity;
	/**
	 * Number of non-null elements (nodes)
	 */
	private int size;
	/**
	 * LoadFactor
	 */
	private float loadFactor;
	/**
	 * Array of Nodes
	 */
	private Node<K, V>[] arrayNode;

	// Constructors
	/**
	 * First Constructor with no parameters
	 */
	public HashMap() {
		this(16, 0.75f);
	}

	/**
	 * Second Constructor with parameter capacity
	 * 
	 * @param capacity
	 */
	public HashMap(int capacity) {
		this(capacity, 0.75f);
	}

	/**
	 * Third Constructor with capacity and loadFactor as parameters
	 * 
	 * @param capacity
	 * @param loadFactor
	 */
	@SuppressWarnings("unchecked")
	public HashMap(int capacity, float loadFactor) {
		this.capacity = powerOfTwo(capacity);
		this.loadFactor = loadFactor;
		arrayNode = (Node<K, V>[]) new Node[capacity];
		size = 0;
	}

	// Methods

	// Public

	// Inserting a Node into the HashMap
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void put(K key, V value) {

		// Checking if a reHash is needed
		int maxElements = (int) (capacity * loadFactor);
		if (size + 1 > maxElements)
			reHash();

		// Checking if the hashmap contains the key
		if (contains(key)) {
			int pos = hash(key);
			Node<K, V> node = arrayNode[pos];

			setValue(key, value, node);
		}

		// The hashmap doesn't contain the key, so we add it
		else {
			size++;
			Node<K, V> node = new Node<K, V>(key, value);
			int pos = hash(key);

			if (arrayNode[pos] == null)
				arrayNode[pos] = node;
			else {
				node.next = arrayNode[pos];
				arrayNode[pos] = node;
			}
		}
	}

	// Getting a value searching by key
	/**
	 * {@inheritDoc}
	 */
	@Override
	public V get(K key) {
		int pos = hash(key);
		Node<K, V> node = arrayNode[pos];

		while (node != null) {
			if (node.key.equals(key))
				return node.value;
			node = node.next;
		}
		return null;
	}

	// Removing a node by key
	/**
	 * {@inheritDoc}
	 */
	@Override
	public V remove(K key) {
		V v = null;

		// Returning null if the key doesn't exist
		if (!contains(key))
			return v;

		int pos = hash(key);

		// If the node we want to remove is the first one..
		if (arrayNode[pos].key.equals(key)) {
			v = arrayNode[pos].value;
			arrayNode[pos] = arrayNode[pos].next;
		}

		else {
			Node<K, V> node = arrayNode[pos];

			// Iterating till we find the correct node
			while (node != null && node.next != null) {
				if (node.next.key.equals(key)) {
					v = node.next.value;
					node.next = node.next.next;
				} else
					node = node.next;
			}
		}

		size--;

		return v;

	}

	// ToString Method, with the appropriate format to print exactly what we want
	/**
	 * Prints the hashmap in the appropriate format
	 */
	@Override
	public String toString() {
		String result = "";

		for (int i = 0; i < capacity; i++) {
			Node<K, V> node = arrayNode[i];

			while (node != null) {
				result = result + "\n" + node.key + ":" + node.value;
				node = node.next;
			}

		}
		return result;
	}

	// Checking if the hashmap contains a key or not
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(K key) {
		if (get(key) != null)
			return true;
		else
			return false;
	}

	// Size & Capacity Getters
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Capacity getter
	 * 
	 * @return returns capacity
	 */
	public int capacity() {
		return capacity;
	}

	// Checking if hashmap is null
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	// Setting the hashmap to null
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		for (int i = 0; i < capacity; i++)
			arrayNode[i] = null;
		size = 0;
	}

	// Returning a new Iterator for the hashmap
	/**
	 * Creates and returns a new CIterator for the hashmap
	 */
	@Override
	public Iterator<K> iterator() {
		return new CIterator();
	}

	// Private Methods

	// Hash Code by key

	private int hash(K key) {
		return key.hashCode() & (capacity - 1);
	}

	// ReHashing, making the hashmap double its capacity

	@SuppressWarnings("unchecked")
	private void reHash() {
		capacity *= 2;

		Node<K, V>[] aux = (Node<K, V>[]) new Node[capacity];

		// Iterating through all the keys of the hashmap using for each loop from
		// Iterator
		for (K key : this) {
			capacity /= 2;
			V value = get(key);
			capacity *= 2;
			Node<K, V> node = new Node<K, V>(key, value);
			int pos = hash(key);

			putNode(aux, node, pos);
		}
		arrayNode = aux;
	}
	// Putting a node into the hashmap given a position (hashcode)

	private void putNode(Node<K, V>[] aux, Node<K, V> node, int pos) {
		if (aux[pos] == null)
			aux[pos] = node;
		else {
			node.next = aux[pos];
			aux[pos] = node;
		}
	}

	// Setting the value by key

	private void setValue(K key, V value, Node<K, V> node) {
		while (node != null) {
			if (node.key.equals(key))
				node.value = value;
			node = node.next;
		}
	}

	// Converting a number into a power of two in an efficient way (used in third
	// constructor to set the hashmap capacity)

	private int powerOfTwo(int capacity) {
		int x = capacity - 1;
		x |= x >> 1;
		x |= x >> 2;
		x |= x >> 4;
		x |= x >> 8;
		x |= x >> 16;

		return x + 1;
	}

	// Static Node Inner Class

	private static class Node<K, V> {

		// Attributes

		K key;

		V value;

		Node<K, V> next;

		// Constructor

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}

		// Equals Method

		public boolean equals(Object o) {
			if (o instanceof Node<?, ?> == false)
				return false;
			@SuppressWarnings("unchecked")
			Node<K, V> node = (Node<K, V>) o;
			return node.key.equals(key);
		}
	}

	// Iterator Inner Class

	private class CIterator implements Iterator<K> {

		// Attributes

		private int nodeCounter;

		private int index; // nodeCounter will count every node in the hashmap

		private Node<K, V> first;

		// Constructor

		public CIterator() {
			nodeCounter = 0;
			index = 0;
			first = arrayNode[index];
		}

		// Methods

		// Checking if there's a next value

		@Override
		public boolean hasNext() {
			return nodeCounter < size;
		}

		// Iterator moving method, returns the key of the current node and moves onto
		// the next one if there is a next node

		@Override
		public K next() {

			// If there's a next element in the node array...
			if (hasNext()) {

				// If first isn't null... we don't need to change the index
				if (first != null) {
					K key = first.key;
					first = first.next;
					nodeCounter++;
					return key;
				}

				// If first is null... we increment index till we find a non-null node
				else {
					index++;
					while (arrayNode[index] == null)
						index++;
					first = arrayNode[index];
					K key = first.key;
					nodeCounter++;
					first = first.next;
					return key;
				}
			} else
				return null;
		}

	}

}