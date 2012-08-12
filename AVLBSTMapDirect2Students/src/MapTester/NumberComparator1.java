package MapTester;

import java.util.Comparator; 

public class NumberComparator1 implements Comparator<Integer> {

	public int compare(Integer a, Integer b) { 
		return a.compareTo(b); 
	}
}
