import exceptions.InvalidStackException;


/**
 * This class is basically a copy of PermGenerator_V2, it's works exactly the same except for a new method
 * that checks if the generated permutation is correct for the Queens problem, because everything is basically the
 * same only the next() and queensKill() method are explained
 * @author Dany
 *
 */
public class EightQueens implements P_ADT {
	
	private boolean hasMore;
	private int n;
	private int[] a;

	public EightQueens(int n) {
		super();
		this.n = n;
		a = new int[n];
		eUsed = new boolean[n];
		hasMore = true;
		reset();
		try {
			next();
		} catch (InvalidStackException e) {
			e.printStackTrace();
		}
	}

	public boolean hasMore() {
		return hasMore;
	}
	
	public int[] next() throws InvalidStackException {
		int[] atr = a.clone();
		if(hasMore()){
			do {
				try {
					nextSeq(a, n-1);
					nextPerm(a, 0);
					if(!done && areAllOnes(a))
						hasMore = false;
					done = false;
				} catch (IndexOutOfBoundsException e) {
					hasMore = false;
				}
			} while(queensKill(a));
			return atr;
		} else {
			throw new InvalidStackException("No more permutations!");
		}
	}

	private boolean done;
	private boolean eUsed[];
	private void nextPerm(int[] p, int lvl) {
		if(lvl == p.length){
			done = true;
		} else {
			int j = p[lvl];
			while(j<=n && !done){
				if(lvl > 0 && eUsed[j-1]){
					j++;
					continue;
				}
				p[lvl]=j;
				eUsed[j-1] = true;
				nextPerm(p, lvl+1);
				eUsed[j-1] = false;
				j++;
			}
			if(j > n && !done)
				p[lvl] = 1;
		}	
	}
	
	private boolean areAllOnes(int[] p) {
		for(int i : p)
			if(i != 1)
				return false;
		return true;
	}
	
	
	//To check if permutation is correct one only has to simply check for "right-directioned diagonal kills" of the Queens, 
	//the reason for this is that because the sequence being check is a permutation there are no repeated elements,
	//and thus horizontal checks don't have to be made, vertical checks also don't have to be check because of the nature of
	//the array, only 1 element per index, and considering the index is the column of the chess board then only one queen per 
	//column. Moreover only diagonal checks that are headed to the right are needed to be verify because left one were already 
	//verified in the process of checking the column before the one currently being checked. That is, by verifying the columns from
	//left to right (array index incrementing) then one only has to check for consecutive forward position (right part of chess board), 
	//kinda like verifying if and element is repeated in an array in a brute-force sense.
	//This method is O(n^2)
	private boolean queensKill(int[] s) {
		//The column number is the array index
		for(int col = 0; col < s.length-1; col++){ //Iterate over columns by iterating trough the array containing the permutation
			int row = s[col]; //The row number is the element's value
			for(int col2 = col+1; col2 < s.length; col2++){ //Check the right side of the array (Right columns of board)
				//Because we are only checking for diagonal attacks then we must only check if there is a queen at
				// at the same row plus/minus the columns moved to the right from the original column (col2-col)
				if(s[col2] == (row+(col2-col)) || s[col2] == (row-(col2-col)))
					return true;
			}
			
		}
		return false;
	}
	
	private void nextSeq(int[] p, int lvl) {
		if(p[lvl] == n) {
			p[lvl] = 1;
			nextSeq(p, lvl-1);
		} else {
			p[lvl]++;
		}	
	}

	public void reset() {
		for(int i = 1; i <= n; i++){
			a[i-1] = i;
		}
		hasMore = true;
	}

}
