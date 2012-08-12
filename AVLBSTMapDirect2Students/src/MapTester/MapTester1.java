package MapTester;

import java.util.Random;

import mapInterfaces.Entry;
import mapInterfaces.Map;
import directAVLBSTMap.AVLBSTMap;

public class MapTester1 {

	public static void main(String[] args) {
		
		
		Map<Integer, String> map = 
			new AVLBSTMap<Integer, String>(new IntegerKeyValidator(), new NumberComparator1()); 
		
		final int N = 10;
		Random rnd = new Random();
		
//		for (int i=1; i<=200; i++) { 
//
//			if (rnd.nextBoolean())
//			{
//				int x = rnd.nextInt(N); 
//				addToMap(map, x); 
//			}
//			else {
//				int x = rnd.nextInt(N); 
//				removeFromMap(map, x); 
//			}
//			showMap("Map in Inorder is: ", map); 
//
//		}
		
		add(map, 60);
		add(map, 40);
		add(map, 20);
		add(map, 8);
		add(map, 25);
		add(map, 50);
		add(map, 80);
		add(map, 70);
		add(map, 4);
		add(map, 10);
		add(map, 28);
				
		addToMap(map, 13);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 20);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 40);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 25);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 50);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 28);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 10);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 8);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
		removeFromMap(map, 80);
		showMap("Map in Inorder is: ", map);
		isAVL(map);
		
	}
	
	private static <K, V> void isAVL(final Map<K,V> m) {
		if(((AVLBSTMap<K,V>) m).isAVL())
			System.out.println("\nThe internal tree is AVL");
		else
			System.out.println("\nThe internal tree is not AVL");
		
	}

	private static void removeFromMap(Map<Integer, String> map, int x) {
		System.out.println("\nRemoving value " + x + " from map."); 
		map.remove(x); 
	}

	private static void addToMap(Map<Integer, String> map, int x) {
		System.out.println("\nAdding value " + x + " to map."); 
		map.put(x, ""); 
	}
	
	private static void add(Map<Integer, String> map, int x) {
		System.out.println("Adding value " + x + " to map."); 
		map.put(x, ""); 
	}

	private static <K,V> void showMap(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		AVLBSTMap<K,V> bstm = (AVLBSTMap<K,V>) m; 
		bstm.showNodes(); 
	}
	private static <K,V> void showMapValues(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		for (V value : m.values()) 
			System.out.println(value); 
	}

	private static <K,V> void showMapKeys(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		for (K key : m.keys()) 
			System.out.println(key); 
	}

	private static <K,V> void showMapEntries(final String msg, final Map<K,V> m) { 
		System.out.println("\n" + msg); 
		for (Entry<K,V> e : m.entries()) 
			System.out.println(e); 
	}

}
