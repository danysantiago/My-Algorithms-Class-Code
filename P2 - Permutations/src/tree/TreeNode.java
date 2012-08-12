package tree;


import java.util.ArrayList;

//My Tree Node used on my Tree implementation.
public class TreeNode<E> {

	private E data; 
	private TreeNode<E> parent; 
	private ArrayList<TreeNode<E>> children = new ArrayList<TreeNode<E>>();
	
	public TreeNode() {} 
	
	public TreeNode(E data, TreeNode<E> parent) { 
		this.data = data; 
		this.parent = parent; 
	}
	
	public TreeNode(TreeNode<E> p, E e) { 
		parent = p; 
		data = e; 
	}
	
	public E element() {
		return data;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public TreeNode<E> getParent() {
		return parent;
	}

	public void setParent(TreeNode<E> parent) {
		this.parent = parent;
	}

	public ArrayList<TreeNode<E>> getChildren(){
		return children;
	}
	
	public void addChild(TreeNode<E> c){
		children.add(c);
	}

	public void clean() {
		data = null; 
		parent = null; 
	}


}
