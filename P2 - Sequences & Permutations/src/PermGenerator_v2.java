import exceptions.InvalidStackException;


public class PermGenerator_v2 implements P_ADT {
	
	private boolean hasMore; //Boolean indicator for hasMore permutations
	private int n; //Size of the array
	private int[] a; //Array to create sequences and permutations

	//Constructor
	//Only parameter is array size
	public PermGenerator_v2(int n) {
		super();
		this.n = n;
		a = new int[n];
		eUsed = new boolean[n];
		hasMore = true;
		reset();
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
		if(hasMore()){
			try {
				nextSeq(a, n-1); //Proceed one sequence so the nextPerm() method dosen't exit with the same permutation in the array a
				nextPerm(a, 0); //Generate the next permutation
				//If nextPerm exists without the sentinel being active it means that it has already searched
				//trough all sequences looking for a permutation, if so confirm this being checking if the array a
				//has all elements in '1'. If all of the mentioned is true then set the indicator of hasMore to false
				//because there will be no next permutations
				//Even though the areAllOnes method is O(n), the if statement is O(1) because if the left condition of the AND
				//is false the right one dosen't have to be checked. Java is a 'short-circuits' language.
				//Actually the method areAllOnes is only executed once in all the next() calls, and that is that once instance 
				//that nextPerm() exists without the sentinel being active.
				if(!done && areAllOnes(a)) 
					hasMore = false;
				done = false; //Set sentinel so next call of nextPerm() can proceed
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

	private boolean done; //Sentinel to finish the recursion
	private boolean eUsed[]; //Keeps track of the elements already used
	//This method generates the next permutation following the idea of jumping paths in the 'virtual tree'
	//by checking the elements already used. Once a permutation is found a sentinel is activated and all the
	//recursive calls exit. Once this method is called again it starts off basically where it left off, this is
	//done by initializing j to the element value at such level.
	private void nextPerm(int[] p, int lvl) {
		if(lvl == p.length){ //If reached a leaf in the 'virtual tree' activate the sentinel to exit all recursion calls
			//By reaching this part of the code the permutation is stored at the array given in the parameter, which is
			//the class's array a
			done = true;
		} else { //Final level has not been reached
			int j = p[lvl]; //Sets the number to start trying generating permutations
			while(j<=n && !done){ //While neither the max number and the final level has not been reached, keep trying
				//If we are on the first level we don't need to check for repeated elements
				//If we are not on first level check for previously used elements in the recursive call
				if(lvl > 0 && eUsed[j-1]){
					j++;
					continue;
				}
				p[lvl]=j; //Set the array with the element
				eUsed[j-1] = true; //eUsed.put(j, j); //Set element value as used
				nextPerm(p, lvl+1); //Recursively get permutation over the lower levels
				eUsed[j-1] = false; //Once the recursive calls exists we are back at this level and remove the value from being used
				j++; //Keep trying more values
			}
			//If the upper while exists without done being active it means that we have reached the max value
			//in the current level, if so set back this levels value to '1' so the method exists and continues
			//with the upper levels trying back this level but with a different set of used elements
			if(j > n && !done)
				p[lvl] = 1;
		}	
	}
	
	//Method to check if all elements of the array are '1'
	//This method is O(n)
	private boolean areAllOnes(int[] p) {
		for(int i : p)
			if(i != 1)
				return false;
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

	//Reset method, sets the indicator to true and replaces the array
	//with the first possible permutation
	public void reset() {
		for(int i = 1; i <= n; i++){
			a[i-1] = i;
		}
		hasMore = true;
	}

}
