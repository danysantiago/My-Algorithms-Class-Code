package MapTester;

import java.util.Comparator; 

public class NumberComparator2 implements Comparator<Integer> {

	public int compare(Integer a, Integer b) { 
		return b.compareTo(a); 
	}
}
