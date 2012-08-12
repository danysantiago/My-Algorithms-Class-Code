import java.util.ArrayList;
import java.util.HashMap;

import exceptions.InvalidStackException;


/**
 * Old implementation of PermGenerator_v2
 * In this implementation all permutations are generated when the object is created
 * and they are all saved in an ArrayList. Because they were all generated at the same time
 * to follow's the ADT specification one only has to iterate trough the permutation list
 * returning the permutation at which the curIndex is in the next() method.
 * 
 * This method was discarded because all the work was being done at the construction of the object
 * and that is not the main idea, for example, if one only wishes to get the first 3 permutation of a
 * n sized array of 10 then this implementation would take excessively a lot of time at the constructor.
 * Even though the other implementation might take longer at the next() method but it dosen't have to generate all
 * permutations at once.
 * @author Dany
 *
 */
public class PermGenerator_v2_OLD implements P_ADT {
	
	private int n;
	private int curIndex;
	private ArrayList<int[]> a;

	public PermGenerator_v2_OLD(int n) {
		super();
		this.n = n;
		a = new ArrayList<int[]>();
		int[] arr = new int[n];
		perm(arr, 0);
		reset();
	}
	
	private HashMap<Integer,Integer> eUsed = new HashMap<Integer,Integer>();
	private void perm(int[] p, int lvl) {
		if(lvl == p.length){
			a.add(p.clone());
		} else {
			for(int j=1; j<=n; j++){
				if(eUsed.containsKey(j))
					continue;
				p[lvl]=j;
				eUsed.put(j, j);
				perm(p, lvl+1);
				eUsed.remove(j);
			}

		}
			
	}

	public boolean hasMore() {
		return curIndex < a.size();
	}
	
	public int[] next() throws InvalidStackException {
		if(!hasMore())
			throw new InvalidStackException("No more permutations!");

		return a.get(curIndex++);
	}

	public void reset() {
		curIndex = 0;
	}

}
