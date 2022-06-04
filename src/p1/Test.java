package p1;

/**
 * Testing Purposes Class
 * @author Jordi Linares
 *
 */
public class Test {
	
	public static void main(String[] args) {

		/****ARRAY METHODS****/
		
		//Trying out Array methods
		System.out.println("ARRAY METHODS: ");
		System.out.println();

		//Creating an array with length 16
		Array<String> arr = new Array<>(16);
		
		//Using add method
		arr.add("hi");
		arr.add("hej");
		arr.add("hola");
		arr.add("hello");
		
		System.out.println("->Creating an array and adding a few elements: ");
		
		//Using toString to print all the elements
		System.out.println(arr.toString());
		System.out.println();

		//Using get method
		System.out.println("->Getting element at position 2: "+ arr.get(2));
		System.out.println();
		
		//Using set method
		arr.set(2, "adeu");		
		System.out.println("->Setting element at position 2 to 'adeu': ");
		System.out.println(arr.toString());
		System.out.println();

		
		//Testing Array Iterator
		System.out.println("->Testing Array Iterator to print every element of the Array");
		for(String s: arr) {
			System.out.println(s);
		}
		System.out.println();

		
		//Clearing the Array
		arr.clear();
		System.out.println("->Clearing the array:");
		System.out.println(arr.toString());
		

		/****HASHMAP METHODS****/
		
		//Creating a new [String(key) -> Integer(value)] HashMap
		System.out.println("\n\nHASHMAP METHODS: ");
		System.out.println();
		System.out.println("->Creating a new hashmap and adding 12 elements");

		HashMap<String,Integer> h = new HashMap<>();

		h.put("Paco", 19);
		h.put("Jordi", 26);
		h.put("Luis", 33);
		h.put("Pep", 32);
		h.put("Sergi", 34);
		h.put("Josep", 12);
		h.put("Oscar", 67);
		h.put("Adrian", 36);
		h.put("Toni", 39);
		h.put("Jorge", 21);		
		h.put("Ivan", 45);
		h.put("Marc", 23);
		
		printMap(h);
		System.out.println("\n");

		//Testing get
		System.out.println("->Getting the value with key 'Oscar' : " + h.get("Oscar") );
		System.out.println("->Getting the value with key 'Oscarr' : " + h.get("Oscarr") );

		//Testing contains 
		System.out.println("\n->Checking if hashmap contains 'Oscar': " + h.contains("Oscar"));
		
		//Testing rehash
		System.out.println("\n->Adding some more elements to force a reHash ");
		h.put("Jose", 30);
		h.put("Manel", 39);
		h.put("John", 10);
		
		printMap(h);

		//Testing remove
		System.out.println("\n\n->Removing 'Oscar': " + h.remove("Oscar"));
		System.out.println("->Removing 'Jordi': " + h.remove("Jordi"));
		System.out.println("->Removing 'Pepe': " + h.remove("Pepe"));
		
		printMap(h);
		
		//Testing HashMap Iterator
		System.out.println("\n\n->With the Iterator, looping the hashmap and printing all its keys");
		for(String key: h)
			System.out.print(key + ", ");
		
		//Clearing the hashmap
		System.out.println("\n\n->Finally, clearing the hashmap");
		h.clear();
		printMap(h);
	}

	private static void printMap(HashMap<String, Integer> h) {
		System.out.println(h.toString());
		System.out.print("Size = " +h.size());
		System.out.print(", Capacity = " +h.capacity());
	}

}
