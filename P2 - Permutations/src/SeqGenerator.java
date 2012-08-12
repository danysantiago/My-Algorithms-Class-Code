import interfaces.P_ADT;

import java.util.ArrayList;

import tree.MyTree;
import tree.TreeNode;
import exceptions.InvalidStackException;



public class SeqGenerator implements P_ADT {
	
	int[] a;
	int n;
	
	MyTree<Integer> t;
	int posIndex; //Current index of the list containing the external nodes for the sequence, kinda like the index in an Iterator
	ArrayList<TreeNode<Integer>> posList = //List containing external nodes of tree
			new ArrayList<TreeNode<Integer>>();

	public SeqGenerator(int[] a, int n) {
		this.a = a;
		this.n = n;
		generateTree();
	}

	private void generateTree() {
		t = new MyTree<Integer>(); //Create Tree
		TreeNode<Integer> r = new TreeNode<Integer>(); //Creates Dummy Root
		t.setRoot(r); //Add dummy root
		int treeDepth = a.length; //The depth of the tree will be that of the array size
		recAdder(r, treeDepth); //Recursively create tree
		this.reset();
	}
	
	//This method simply adds the children to the tree while at the same time saving the external nodes
	//because with them one will be able to create the sequence.
	private void recAdder(TreeNode<Integer> r , int depth){
		if(depth == 0){ //If depth reached save external node
			posList.add(r);
		} else {
			//If depth hasen't been reached
			for(int i = 0; i < n; i++){ //Iterate trough node adding children
				TreeNode<Integer> c = new TreeNode<Integer>(a[i],r);
				r.addChild(c);
				recAdder(c, depth-1); //While also adding the children of the recently added child
			}
		}
	}

	public boolean hasMore() {
		return posIndex < posList.size();
	}

	public int[] next() throws InvalidStackException {
		if(posIndex == posList.size()) //Exception if throw already returned last valid sequence
			throw new InvalidStackException("No more Sequences to generate!");
		
		//To get sequence travel from the external node back to the root of the tree
		//creating the sequence from the elements of the nodes visited
		int[] s = new int[a.length];
		TreeNode<Integer> p = posList.get(posIndex);
		for(int i = a.length; i > 0; i--){
			s[i-1] = p.element();
			p = p.getParent();
		}
		//Increment the position index so that the following next() operation is done with
		//the another external node, and thus creating a different sequence
		posIndex++;
		return s;
	}

	public void reset() {
		//To reset generator simply set the position index back to 0
		posIndex = 0;
	}

}
