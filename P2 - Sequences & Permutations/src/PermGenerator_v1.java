import exceptions.InvalidStackException;


public class PermGenerator_v1 implements P_ADT {
	
	private boolean hasMore; //Boolean indicator for hasMore permutations
	private int n; //Size of the array
	private int[] a; //Array to create sequences and permutations
	

	//Constructor
	//Only parameter is array size
	public PermGenerator_v1(int n) {
		super();
		this.n = n;
		a = new int[n]; //Create array
		hasMore = true; //Initialize indicator
		reset(); //Reset the array
	}

	//Method to return is there are more permutations to generate
	public boolean hasMore() {
		return hasMore;
	}

	//Method that returns the next permutation in Lexicographical order
	//This method works by returning the current array as permutation and then proceeding to replace
	//the internal array (a) for the next permutation. In other words, it's one permutation ahead.
	public int[] next() throws InvalidStackException {
		int[] atr = a.clone(); //Permutation to return (I assume this method is O(n) )
		if(hasMore()){ //Check if there are more permutations
			try {
				do {
					nextSeq(a, n-1); //Generate the next sequence
				} while(!isPerm(a)); //Keep generating sequences until the sequence is a permutation
			} catch (IndexOutOfBoundsException e) {
				//If an IndexOutOfBoundsException is thrown during the nextSeq method
				//then the last sequence was already generated and thus the last permutation
				//is at the array (a).
				hasMore = false;
			}
			return atr;
		} else {
			//Throws exception if the final sequence was already returned
			throw new InvalidStackException("No more permutations!");
		}
	}
	
	//Checks if the array is a permutation by searching trough the array
	//and checking all elements are unique
	//This method is O(n^2)
	private boolean isPerm(int[] arr) {
		for(int i = 0; i < arr.length; i++){
			for(int j = i+1; j < arr.length; j++)
				if(arr[i] == arr[j])
					return false;
		}
		return true;
	}

	//Recursive method to generate the next sequence, the sequence is generate in the
	//internal array (a).
	//This method is:
	//Worst case: O(n)
	//Best case: O(1)
	private void nextSeq(int[] p, int lvl) {
		//Check if the current level has reached it's max value, if so set that level's element to '1'
		//and recursively call the method to the next lower level (lower because it's started at the max)
		if(p[lvl] == n) {
			p[lvl] = 1;
			nextSeq(p, lvl-1);
		} else {
			//If current level is not at it's max value simply increment it's current element value
			p[lvl]++;
		}
			
	}

	//Reset method, sets the indicator to true and replaces all elements
	//of the array to '1's
	public void reset() {
		for(int i = 1; i <= n; i++)
			a[i-1] = i;
		hasMore = true;
	}

}
