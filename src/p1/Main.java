package p1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Main Class
 * 
 * @author Jordi Linares
 *
 */
public class Main {
	// Pair Generic Class
	private static class Pair<T1, T2> {
		T1 t1;
		T2 t2;

		// Constructor
		public Pair(T1 t1, T2 t2) {
			this.t1 = t1;
			this.t2 = t2;
		}
	}

	// Method to create a list of Pairs given a String line, which will store the
	// column and the word
	private static List<Pair<Integer, String>> wordsPosition(String line) {
		List<Pair<Integer, String>> result = new ArrayList<>();
		int start = 0, end = 0;

		// Looping the line
		while (end < line.length()) {
			// While there's no blank space, we keep moving
			while (end < line.length() && line.charAt(end) != ' ')
				end++;

			// Adding to result the new Pair
			result.add(new Pair<>(start, line.substring(start, end)));

			// Setting start and end to their correct values in order to keep reading
			start = end + 1;
			while (start < line.length() && line.charAt(start) == ' ')
				start++;
			end = start;
		}

		return result;
	}

	/**
	 * Main Code
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Checking if the args are correct
		if (args.length != 1) {
			System.out.println("Invalid command syntax: command txtfile");
			System.exit(0);
		}
		BufferedReader in = null;

		// Checking if file is okay
		try {
			in = new BufferedReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("Unknown file error");
			System.exit(0);
		}

		String line;
		HashMap<String, Array<Position>> map = new HashMap<>();
		int row = 1;

		// Reading the file
		try {
			while ((line = in.readLine()) != null) {
				// Replacing all but the regular expression into blank spaces, and then
				// lowercasing everything
				line = line.replaceAll("[^a-zA-Z0-9À-ÿ]", " ");
				line = line.trim().toLowerCase();

				// Creating the list of words
				List<Pair<Integer, String>> words = wordsPosition(line);

				// Creating the array of positions
				Array<Position> positions;

				// Using for each loop to
				for (Pair<Integer, String> p : words) {
					// Creating a position storing row & column of the word
					Position pos = new Position(row, p.t1 + 1);

					// We check if there's already an non-null array of positions for the word we
					// want
					positions = map.get(p.t2);

					// If positions is null, we create it
					if (positions == null)
						positions = new Array<>();

					// And we add the new position into the array
					positions.add(pos);

					// Finally we put that node (word + array of positions) into the hashmap
					map.put(p.t2, positions);

				}
				row++;
			}
		} catch (IOException e) {
			System.out.println("Unknown read error");
			System.exit(0);
		}

		// Finally, printing the hashmap
		System.out.println(map.toString());

	}

}
