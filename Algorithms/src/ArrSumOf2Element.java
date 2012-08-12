import java.util.ArrayList;
import java.util.Collections;


public class ArrSumOf2Element {
	
	public static void main(String[] args){
		int[] ints = {5, 9, 1, 3, 4, 1, 9, 10, 12, 3, 1, 0, 7};
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < ints.length; i++)
			arr.add(ints[i]);
		
		Collections.sort(arr);
		
		System.out.println(arrHasSum(arr, 7));
	}

	private static boolean arrHasSum(ArrayList<Integer> arr, int s) {
		for(int i = 0; i < arr.size(); i++){
			if(arr.get(i) > s)
				break;
			else {
				int pos = Collections.binarySearch(arr, s-arr.get(i));
				if(pos >= 0 && pos < arr.size() && pos != i){
					if(arr.get(i) + arr.get(pos) == s)
						return true;
				}
			}
		}
		
		return false;
	}
}
