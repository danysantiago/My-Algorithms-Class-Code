import exceptions.InvalidStackException;


public class SeqGenerator implements P_ADT {
	
	private boolean hasMore; //Boolean indicator for hasMore sequences
	private int n; //Size of the array
	private int[] a; //Array to create sequences
	

	//Constructor
	//Only parameter is array size
	public SeqGenerator(int n) {
		super();
		this.n = n;
		a = new int[n]; //Create array
		hasMore = true; //Initialize indicator
		reset(); //Reset the array (Makes all elements '1')
	}

	//Method to return is there are more sequences to generate
	public boolean hasMore() {
		return hasMore;
	}

	//Method that returns the next sequence in Lexicographical order
	//This method works by returning the current array as sequence and then proceeding to replace
	//the internal array (a) for the next sequence. In other words, it's one sequence ahead.
	public int[] next() throws InvalidStackException {
		int[] atr = a.clone(); //Sequence to return
		if(hasMore()){ //Check if there are more sequences
			try {
				nextSeq(a, n-1); //Proceed to next sequence
			} catch (IndexOutOfBoundsException e) {
				//If an IndexOutOfBoundsException is thrown during the nextSeq method
				//then the last sequence was already generated and is currently at the array (a)
				//If so, set the indicator of hasMore to false
				hasMore = false;
			}
			return atr; //Return sequence
		} else {
			//Throws exception if the final sequence was already returned
			throw new InvalidStackException("No more sequences!");
		}
	}
	
	//Recursive method to generate the next sequence, the sequence is generate in the
	//internal array (a)
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
		for(int i = 0; i < n; i++)
			a[i] = 1;
		hasMore = true;
	}

}
