package p1;

import java.util.*;

/**
 * Array Class
 * 
 * @author Jordi Linares
 *
 * @param <E>
 */
public class Array<E> implements Iterable<E> {

	// Attributes

	private E[] myArray;

	private int size; // Size here are all the non-null elements in the array

	private int length;

	private static final int initialLength = 100;

	/**
	 * First Constructor
	 */
	// Constructors
	public Array() {
		this(initialLength);
	}

	/**
	 * Second Constructor
	 */
	@SuppressWarnings("unchecked")
	public Array(int length) {
		this.length = length;
		this.size = 0;
		myArray = (E[]) new Object[length];
	}

	// Methods
	/**
	 * Adds an element to the Array
	 * 
	 * @param element
	 */
	// Adding an element to the Array
	public void add(E element) {
		if (size == length)
			grow();

		for (int i = 0; i <= size; i++) {
			if (myArray[i] == null)
				myArray[i] = element;
		}
		size++;
	}

	// Multiplying by 1.5 the array's length to make it bigger (creating a new
	// array)
	private void grow() {
		length = (length * 3) / 2;
		myArray = Arrays.copyOf(myArray, length);
	}

	/**
	 * Returns an element given a position
	 * 
	 * @param index
	 * @return returns the element
	 * @throws ArrayIndexOutOfBoundsException
	 */
	// Getting an element given a position
	public E get(int index) throws ArrayIndexOutOfBoundsException {
		if (index >= size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		else
			return myArray[index];
	}

	/**
	 * Sets an element given a position
	 * 
	 * @param index
	 * @param element
	 * @throws ArrayIndexOutOfBoundsException
	 */
	// Setting an element given a position
	public void set(int index, E element) throws ArrayIndexOutOfBoundsException {
		if (index >= size || index < 0)
			throw new ArrayIndexOutOfBoundsException();
		else
			myArray[index] = element;
	}

	/**
	 * Size getter
	 * 
	 * @return returns size
	 */
	// Size & Length getters
	public int getSize() {
		return this.size;
	}

	/**
	 * Length getter
	 * 
	 * @return returns length
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Sets the array to null
	 */
	// Setting all elements to null
	public void clear() {
		// sets all to null
		Arrays.fill(myArray, 0, size, null);
		size = 0;
	}

	/**
	 * Prints the array in the appropriate format
	 */
	// ToString method
	@Override
	public String toString() {
		String s = " [";
		for (int i = 0; i < size; i++) {
			if(i==0) 
				s = s + myArray[i];
			else {
				s = s + ", "+ myArray[i];
			}
		}
		return s + "]";
	}

	/**
	 * Array Iterator
	 */
	// Array Iterator
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator = new Iterator<E>() {
			/**
			 * Index of the Array
			 */
			private int index = 0;

			/**
			 * Checks if there's a next element
			 */
			@Override
			public boolean hasNext() {
				return index < size && myArray[index] != null;
			}

			/**
			 * Returns next element and increases index
			 */
			@Override
			public E next() {
				E e;
				e = myArray[index];
				index++;
				return e;

			}

		};
		return iterator;
	}

}