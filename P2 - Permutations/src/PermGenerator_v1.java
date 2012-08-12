import interfaces.P_ADT;

import java.util.ArrayList;

import tree.MyTree;
import tree.TreeNode;
import exceptions.InvalidStackException;



public class PermGenerator_v1 implements P_ADT {
	
	int[] a;
	int n;
	
	MyTree<Integer> t;
	int posIndex;
	ArrayList<TreeNode<Integer>> posList = //List containing external nodes of tree
			new ArrayList<TreeNode<Integer>>();

	public PermGenerator_v1(int[] a, int n) {
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
		this.reset(); //Reset it
	}
	
	//This method simply adds the children to the tree while at the same time saving the external nodes
	//because with them one will be able to create the sequence that may be a permutation.
	private void recAdder(TreeNode<Integer> r , int depth){
		if(depth == 0){ //If depth reached save external node
			posList.add(r);
		} else {
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
		//Exception if throw already returned last valid sequence
		if(posIndex >= posList.size())
			throw new InvalidStackException("No more Sequences to generate!");
		
		//Saves the position of the external node that is about to be returned
		int postr = posIndex;
		
		//Travels trough the external node list looking for the next valid permutation
		//Once found the position in the external nodes array is saved as the position index
		boolean isPerm = false;
		int[] s = null;
		while(!isPerm){
			if(posIndex < posList.size()-1) {
				posIndex++;
			} else {
				posIndex++;
				break;
			}
			s = new int[a.length];
			TreeNode<Integer> p = posList.get(posIndex);
			for(int i = a.length; i > 0; i--){
				s[i-1] = p.element();
				p = p.getParent();
			}
			
			isPerm = isPerm(s);
		}
		
		//Gets the sequence from external node, this sequence is a valid permutation
		s = new int[a.length];
		TreeNode<Integer> p = posList.get(postr);
		for(int i = a.length; i > 0; i--){
			s[i-1] = p.element();
			p = p.getParent();
		}
		
		return s;
	}

	//Check if giving sequence is a permutation, for the sequence to be a permutation
	//no element must be repeated
	private boolean isPerm(int[] s) {
		for(int i = 0; i < s.length; i++){
			for(int j = i+1; j < s.length; j++)
				if(s[i] == s[j])
					return false;
		}
		return true;
	}

	public void reset() {
		//Resseting this generator is actually finding the first valid sequence that is a permutation
		posIndex = -1;
		boolean isPerm = false;
		int[] s = null;
		while(!isPerm){
			posIndex++;
			s = new int[a.length];
			TreeNode<Integer> p = posList.get(posIndex);
			for(int i = a.length; i > 0; i--){
				s[i-1] = p.element();
				p = p.getParent();
			}
			
			isPerm = isPerm(s);
		}
	}

}
