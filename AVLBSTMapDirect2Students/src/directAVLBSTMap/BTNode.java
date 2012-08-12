package directAVLBSTMap;

import mapInterfaces.BTPosition;

public class BTNode<E> implements BTPosition<E> {
	private E element; 
	private BTPosition<E> left, right; 
	private int height; 
	
	public BTNode() {
		element = null; 
		left = right = null; 
		height = 0; 
	}
	
	public BTNode(E element) { 
		this.element = element; 
		left = right = null; 
		height = 0; 
	}

	public BTNode(E element, 
				BTPosition<E> left, BTPosition<E> right) { 
		this.element = element; 
		this.left = left; 
		this.right = right; 
		this.resetHeight(); 
	}
	
	public E element() { 
		return element; 
	}
	
	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public BTPosition<E> getLeft() {
		return left;
	}

	public void setLeft(BTPosition<E> left) {
		this.left = left;
	}

	public BTPosition<E> getRight() {
		return right;
	}

	public void setRight(BTPosition<E> right) {
		this.right = right;
	}

	public void clean() { 
		element = null; 
		left = right = null; 
	}

	public int getHeight() {
		return height;
	}

	public void resetHeight() {
		int lh = leftChildHeight(), rh = rightChildHeight(); 		
		height = Math.max(lh, rh) + 1; 
	}
	
	public int leftChildHeight() { 
		if (left == null)
			return -1; 
		else 
			return left.getHeight(); 
	}
	
	public int rightChildHeight() { 
		if (right == null)
			return -1; 
		else 
			return right.getHeight(); 

	}

	public void copyFrom(BTPosition<E> other) {
		this.element = other.element(); 
		this.left = other.getLeft(); 
		this.right = other.getRight(); 
		this.height = other.getHeight(); 
	}

}
