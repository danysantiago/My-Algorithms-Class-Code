package tree;

import java.util.ArrayList;

import interfaces.Tree;
import exceptions.BoundaryViolationException;
import exceptions.EmptyTreeException;

//My Tree implementation based on the my Tree ADT, very simple, just what was needed.
public class MyTree<T> implements Tree<T> {
	
	int size;
	TreeNode<T> root;
	
	public void setRoot(TreeNode<T> r){
		this.root = r;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public TreeNode<T> root() throws EmptyTreeException {
		return root;
	}

	public TreeNode<T> parent(TreeNode<T> v) throws BoundaryViolationException {
		return v.getParent();
	}

	public ArrayList<TreeNode<T>> children(TreeNode<T> v) {
		return v.getChildren();
	}

	public boolean isInternal(TreeNode<T> v) {
		return v.getChildren().size() > 0;
	}

	public boolean isExternal(TreeNode<T> v) {
		return !isInternal(v);
	}

	public boolean isRoot(TreeNode<T> v) {
		return v == root;
	}

}
