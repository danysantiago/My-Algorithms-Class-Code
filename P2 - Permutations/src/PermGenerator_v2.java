import interfaces.P_ADT;

import java.util.ArrayList;

import tree.MyTree;
import tree.TreeNode;
import exceptions.InvalidStackException;



public class PermGenerator_v2 implements P_ADT {
	
	int[] a;
	int n;
	
	MyTree<Integer> t;
	int posIndex;
	ArrayList<TreeNode<Integer>> posList = //List containing external nodes of tree that are permutations
			new ArrayList<TreeNode<Integer>>();

	public PermGenerator_v2(int[] a, int n) {
		this.a = a;
		this.n = n;
		generateTree();
	}

	private void generateTree() {
		t = new MyTree<Integer>(); //Create Tree
		TreeNode<Integer> r = new TreeNode<Integer>(); //Creates Dummy Root
		t.setRoot(r); //Add dummy root
		int treeDepth = a.length; //The depth of the tree will be that of the array size
		ArrayList<Integer> usedElements = new ArrayList<Integer>(); //List of elements already used on the tree construction
		recAdder(r, treeDepth, usedElements); //Recursively create tree
		this.reset(); //Reset it
	}
	
	
	private void recAdder(TreeNode<Integer> r , int depth, ArrayList<Integer> usedElements){
		if(depth == 0){ //If depth reached save external node
			posList.add(r);
		} else {
			for(int i = 0; i < n; i++){//Iterate trough node adding children
				TreeNode<Integer> c = new TreeNode<Integer>(a[i],r);
				if(elemetIsUsed(a[i], usedElements)){ //If child is repeated from one of it's parent don't add it
					continue;
				} else { //If it not repeated
					r.addChild(c); //Add child
					usedElements.add(a[i]); //Add such child to the list of those used
					recAdder(c, depth-1, usedElements); //Try to add children to the newly added child
					usedElements.remove(usedElements.size()-1); //Once recursion returns remove added child from used list
				}
			}
		}
	}

	//Check if the child that is about to be added has already been used
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
		//Exception if throw already returned last valid sequence
		if(posIndex >= posList.size())
			throw new InvalidStackException("No more Sequences to generate!");
		
		//To get permutation travel from the external node back to the root of the tree
		//creating the permutation from the elements of the nodes visited
		int[] s = new int[a.length];
		TreeNode<Integer> p = posList.get(posIndex);
		for(int i = a.length; i > 0; i--){
			s[i-1] = p.element();
			p = p.getParent();
		}
		posIndex++;
		
		return s;
	}

	public void reset() {
		posIndex = 0;
	}

}
