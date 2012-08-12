import interfaces.P_ADT;

import java.util.ArrayList;

import tree.MyTree;
import tree.TreeNode;
import exceptions.InvalidStackException;


//This class is basically the same as PermGenerator_v2 with the slight change that at the Tree generating method
//on external nodes that are permutation and satisfy that the queens don't kill each other, are saved in the list
//of external nodes.
public class EightQueens implements P_ADT {
	
	int[] a;
	int n;
	
	MyTree<Integer> t;
	int posIndex;
	ArrayList<TreeNode<Integer>> posList = new ArrayList<TreeNode<Integer>>();

	public EightQueens(int[] a, int n) {
		this.a = a;
		this.n = n;
		generateTree();
	}

	private void generateTree() {
		t = new MyTree<Integer>();
		TreeNode<Integer> r = new TreeNode<Integer>();
		t.setRoot(r);
		int treeDepth = a.length;
		ArrayList<Integer> usedElements = new ArrayList<Integer>();
		recAdder(r, treeDepth, usedElements);
		removeInvalidPerm(); //This is the new method added
		this.reset();
	}
	
	//Method to remove permutation that donsen't satisfy the queens not killing each other.
	private void removeInvalidPerm() {
		for(int i = 0; i < posList.size(); i++){ //Travels the external node list
			int[] s = getSeq(i); //Gets permutation
			if(queensKill(s)){//If permutation is not valid, remove it
				posList.remove(i--);
			}
		}
	}

	//To check if permutation is correct one only has to simply check for "right-directioned diagonal kills", 
	//the reason for this is that because the sequence being check is a permutation there are no repeated elements,
	//and thus horizontal checks don't have to be made, vertical checks also don't have to be check because of the nature of
	//the array, only 1 element per index, and considering the index is the column of the chess board then only one queen per 
	//column. Moreover only diagonal checks that are headed to the right are needed to be verify because left one were already 
	//verified in the process of checking the column before the one currently being checked. That is, by verifying the columns from
	//left to right (array index incrementing) then one only has to check for consecutive forward position (right part of chess board), 
	//kinda like verifying if and element is repeated in an array in a brute-force sense.
	private boolean queensKill(int[] s) {
		for(int col = 0; col < s.length-1; col++){
			int row = s[col];
			for(int i = col+1, count = 1; i < s.length; i++, count++){
				if(s[i] == (row+count) || s[i] == (row-count))
					return true;
			}
			
		}
		return false;
	}

	private void recAdder(TreeNode<Integer> r , int depth, ArrayList<Integer> usedElements){
		if(depth == 0){
			posList.add(r);
		} else {
			for(int i = 0; i < n; i++){
				TreeNode<Integer> c = new TreeNode<Integer>(a[i],r);
				if(elemetIsUsed(a[i], usedElements)){
					continue;
				} else {
					r.addChild(c);
					usedElements.add(a[i]);
					recAdder(c, depth-1, usedElements);
					usedElements.remove(usedElements.size()-1);
				}
			}
		}
	}

	private boolean elemetIsUsed(int i, ArrayList<Integer> usedElements) {
		for(int e : usedElements)
			if(e == i)
				return true;
		return false;
	}

	public boolean hasMore() {
		return posIndex < posList.size();
	}

	public int[] next() throws InvalidStackException {
		if(posIndex >= posList.size())
			throw new InvalidStackException("No more Sequences to generate!");
		
		int[] s = getSeq(posIndex);
		posIndex++;
		
		return s;
	}
	
	private int[] getSeq(int posIndex){
		int[] s = new int[a.length];
		TreeNode<Integer> p = posList.get(posIndex);
		for(int i = a.length; i > 0; i--){
			s[i-1] = p.element();
			p = p.getParent();
		}
		
		return s;
	}

	public void reset() {
		posIndex = 0;
	}

}
