package directAVLBSTMap;

import java.util.Comparator;

import exceptions.InvalidKeyException;
import mapInterfaces.BTPosition;
import mapInterfaces.Entry;
import mapInterfaces.KeyValidator;
import mapInterfaces.Map;

public class AVLBSTMap<K, V> implements Map<K, V> {

	private BTPosition<Entry<K,V>> root; 
	private int size; 
	private KeyValidator<K> kv; 
	private Comparator<K> cmp; 
		
	public AVLBSTMap(KeyValidator<K> kv, Comparator<K> cmp) { 
		this.kv = kv; 
		this.cmp = cmp; 
		root = null; 
		size = 0; 
	}
	
	/**
	 * Returns the size of the map: number of elements. 
	 */
	public int size() {
		return size; 
	}

	/** 
	 * Determines if the map is empty 
	 * @return true if empty; false, otherwise.
	 */
	public boolean isEmpty() {
		return size == 0; 
	}

	/**
	 * Constructs an iterable collection containing all the values 
	 * store in the map collection. 
	 * @return the iterable collection of values.
	 */
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Constructs an iterable collection containing all the keys 
	 * stored in the map collection. 
	 * @return the iterable collection of keys.
	 */
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Constructs an iterable collection containing all the key-
	 * value entries in the map collection. 
	 * @return the iterable collection of entries.
	 */
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Searches a specified entry in the collection.
	 * @param key uniquely identifies the entry to look for
	 * @return null if entry with given key is not found; 
	 * otherwise, it returns reference to the entry having the
	 * particular element. 
	 * @throws InvalidKeyException whenever the key is not valid. 
	 */
	public V get(K key) throws InvalidKeyException {
		BTPosition<Entry<K, V>> rn = recGet(root, key); 
		if (rn != null) 
			return rn.element().getValue(); 
		return null;
	}

	/**
	 * Internal method to search for a particular entry in a 
	 * binary search tree.
	 * @param r root of the tree to search in 
	 * @param key the search key that guides the search
	 * @return null if not found. If found, it returns reference
	 * to the node containing entry with the given key.
	 */
	private BTPosition<Entry<K, V>> recGet(BTPosition<Entry<K, V>> r, K key) {
		if (r == null) 
			return null; 
		else { 
			int rc = cmp.compare(key, r.element().getKey()); 
			if (rc == 0)
				return r; 
			else if (rc < 0)
				return recGet(r.getLeft(), key); 
			else
				return recGet(r.getRight(), key); 
		}
	}

	
	/**
	 * If the collection does not have an entry with key ÒequalÓ to 
	 * the given key, then it adds a new entry containing the 
	 * given key-value pair and return null. I it already exists, 
	 * it replaces with value the existing value of the entry with 
	 * key equal to key and return the old value
	 * @param key key of the new element. 
	 * @param value value of the new element - the value to map
	 * with the given key
	 * @return null if no entry exists in the collection with the 
	 * given key value; otherwise it returns reference to the value
	 * being replaced.
	 * @throws InvalidKeyException whenever the key is not valid.
	 */
	public V put(K key, V value) throws InvalidKeyException {
		kv.validateKey(key); 
		BTPosition<Entry<K, V>> rn = recGet(root, key); 
		if (rn != null) {
			V vtr = rn.element().getValue(); 
			rn.element().setValue(value); 
			return vtr; 
		}
		else { 
			root = recPut(root, key, value); 
			root.resetHeight(); 
			size ++; 
			return null; 
		}
	}

	/**
	 * Internal method used to recursively look for the place to add the
	 * new node with the new value
	 * @param r the root of the tree where the search, and eventual insertion, 
	 * takes place
	 * @param key the key used for the search : the key of the new entry 
	 * to add. 
	 * @param value the value of the new entry
	 * @return reference to the resulting AVL tree after modifying, 
	 *   as needed, the subtree originally rooted at r
	 */
	private BTPosition<Entry<K,V>> recPut(BTPosition<Entry<K,V>> r, K key, V value) {
		if (r == null)  // r is a placeholder node
		{ 
			return new BTNode<Entry<K,V>>(new MyEntry<K, V>(key, value)); 
		}
		else { 
			int rc = cmp.compare(key, r.element().getKey()); 
			if (rc < 0) { 
				r.setLeft(recPut(r.getLeft(), key, value));
			}
			else {
				r.setRight(recPut(r.getRight(), key, value)); 
			}
			
			// check if r is unbalanced, and balance it if necessary
			if(isUnbalanced(r))
				r = rebalance(r);
			
			r.resetHeight();
			return r;
		}
	}

	/**
	 * Rebalances bst tree to comply with the AVL property. The tree
	 * is assumed to satisfy the AVL property in each of its subtrees, 
	 * except for the root node
	 * @param r the root of the tree to balance
	 * @return the root of the resulting AVL tree
	 */
	private BTPosition<Entry<K, V>> rebalance(BTPosition<Entry<K, V>> r) 
	{
		NodeRwPPair<Entry<K,V>> px, py; 
		BTPosition<Entry<K, V>> z=r, y, x; 
		py = highestChild(z); 
		px = highestChild(py.getNode()); 
		y = py.getNode(); 
		x = px.getNode(); 
		if (py.isLeftChild() && px.isLeftChild()) {
			return rr(z, y); 
		}
		else if (py.isLeftChild() && px.isRightChild()) { 
			rl(y ,x);
			return rr(z,x);
		}
		else if (py.isRightChild() && px.isRightChild()) {
			return rl(z, y);
		}
		else {   // right and left is the only option here
			rr(y,x);
			return rl(z,x);
		}
	}

	/**
	 * Rotate left to rebalance to comply with AVL property at
	 * the given tree
	 * @param a the root of the tree to apply rotation to
	 * @param b the right node of a in the tree.
	 * @return b, the root of the resulting tree after rotation
	 */
	private BTPosition<Entry<K, V>> rl(BTPosition<Entry<K, V>> a, BTPosition<Entry<K, V>> b) {
		a.setRight(b.getLeft()); 
		b.setLeft(a); 
		a.resetHeight(); 
		b.resetHeight(); 
		return b; 
	}

	/**
	 * Rotate right to rebalance to comply with AVL property at
	 * the given tree
	 * @param a the root of the tree to apply rotation to
	 * @param b the left node of a in the tree.
	 * @return b, the root of the resulting tree after rotation
	 */
	private BTPosition<Entry<K, V>> rr(BTPosition<Entry<K, V>> a, BTPosition<Entry<K, V>> b) {
		a.setLeft(b.getRight());
		b.setRight(a);
		a.resetHeight();
		b.resetHeight();
		return b; 
	}

	private boolean isUnbalanced(BTPosition<Entry<K, V>> r) {
		if (r != null) {
			int lh = r.leftChildHeight(); 
			int rh = r.rightChildHeight(); 
			return Math.abs(lh - rh) > 1;
		}
		else 
			return false; 
	}

	
	/**
	 * Return value in the entry currently in the collection having
	 * the given key value, if one exists; otherwise it returns 
	 * null. 
	 * @param key identifies the entry to look for.
	 * @return null if no entry is  found having the given key as 
	 * its key. Otherwise, it returns reference to the value paired 
	 * to the given key.
	 * @throws InvalidKeyException whenever the key is not valid. 
	 */
	public V remove(K key) { 
		BTPosition<Entry<K,V>> ptr = recGet(root, key); 
		if (ptr == null) 
			return null; 
		else {
			V vtr = ptr.element().getValue(); 
			root = recRemove(root, key); 
			
			size--; 
			return vtr; 
		}
	}

	/**
	 * Searches for the element corresponding to target
	 * inside the tree whose root is given. If found, 
	 * it is removed from the tree and the tree is partially
	 * reorganized as needed in order for it to continue
	 * being an AVL bst. If not found, the tree is not 
	 * altered. 
	 * @param r the root of the tree where the operation
	 * is applied. 
	 * @param key the key of the element being searched for deletion. 
	 * See description of the corresponding parameter in
	 * the documentation given in method remove. 
	 * @return reference to the resulting tree after deletion
	 * and rebalancing (if needed). 
	 */
	private BTPosition<Entry<K, V>> recRemove(BTPosition<Entry<K, V>> r, K key) 
	{
		// pre: the subtree having root r is bst and contains a
		// node having an entry with the given key
		int cp = cmp.compare(r.element().getKey(), key); 
		if (cp == 0) { 
			return deleteEntryAtNode(r); 
		}
		else if (cp < 0)
			r.setRight(recRemove(r.getRight(), key)); 
		else 
			r.setLeft(recRemove(r.getLeft(), key)); 

		// check if r is unbalanced, and balance it if necessary
		if(isUnbalanced(r))
			r = rebalance(r);
		
		r.resetHeight();
		return r;	
	}


	private NodeRwPPair<Entry<K,V>> highestChild(BTPosition<Entry<K, V>> v) {
		if (v.leftChildHeight() > v.rightChildHeight())
			return new NodeRwPPair<Entry<K,V>>(v.getLeft(), -1); 
		else
			return new NodeRwPPair<Entry<K,V>>(v.getRight(), 1); 	
	}

	/**
	 * Removes from the collection, the entry currently at 
	 * the the given node. The entry is removed, and 
	 * perhaps substituted by some other entry in the 
	 * subtree whose root is the node given. At the end,
	 * the subtree having the given node as its root has 
	 * one data node less, continues satisfying the bst property, 
	 * and the only missing element is the one originally 
	 * at the given node.
	 * @param r the node containing the entry to remove.
	 * @return reference to the root node of the resulting
	 *  tree
	 */
	private BTPosition<Entry<K, V>> deleteEntryAtNode(BTPosition<Entry<K, V>> r) {
		BTPosition<Entry<K, V>> nc = null; 
		if (r.getLeft() == null) { 
			nc = r.getRight(); 
			r.clean(); 
			return nc; 
		} else if (r.getRight() == null) { 
			nc = r.getLeft(); 
			r.clean(); 
			return nc;
		} else { 
			BTPosition<Entry<K, V>> minE = minimumElementNode(r.getRight()); 
			r.setElement(minE.element()); 
			r.setRight(recRemove(r.getRight(), minE.element().getKey()));

			// check if r is unbalanced, and balance it if necessary
			if(isUnbalanced(r))
				r = rebalance(r);
			
			r.resetHeight();
			return r;
			
		}
	}

	/**
	 * Returns reference to the node containing the minimum
	 * value among all the values in the bst tree whose root
	 * is given as parameter. The tree is assumed to be bst. 
	 * @param r the root of the tree where the operation
	 * takes place.
	 * @return the root of the resulting tree. 
	 */
	private BTPosition<Entry<K, V>> minimumElementNode(BTPosition<Entry<K, V>> r) {
		// pre: right != null
		BTPosition<Entry<K, V>> ntret = r; 
		
		if(r.getLeft() != null)
			 ntret = minimumElementNode(r.getLeft());
		
		return ntret;
	}
		
	/////////////////////////////////////////////////////////////////
	// INTERNAL CLASS ///////////////////////////////////////////////
	// Internal class for entries....
	private static class MyEntry<K1, V1> implements Entry<K1, V1> { 
		private K1 key; 
		private V1 value; 
		public MyEntry(K1 key, V1 value) { 
			this.key = key; 
			this.value = value; 
		}
		public K1 getKey() {
			return key;
		}
		public V1 getValue() {
			return value;
		}
		public void setKey(K1 key) {
            this.key = key; 
		}
		public void setValue(V1 value) {
			this.value = value; 
		}
		public String toString() { 
			return "[" + key + ", " + value + "]"; 
		}
	}
	
	
	/////////////////////////////////////////////////////////////
	// for testing purposes......
	public void showNodes() { 
		inorderShowNodes(root); 
	}

	private void inorderShowNodes(BTPosition<Entry<K, V>> r) {
		if (r != null) { 
 			inorderShowNodes(r.getLeft()); 
			System.out.println(r.element()
				+" => Height = " + r.getHeight());
			inorderShowNodes(r.getRight()); 
		}
		
	}

	//For testing purposes, checks if internal tree is AVL;
	public boolean isAVL() {
		return recBalanceCheck(root, true);
	}

	private boolean recBalanceCheck(BTPosition<Entry<K, V>> r, boolean isBalanced) {
		if(r != null && isBalanced){
			isBalanced = !isUnbalanced(r);
			if(isBalanced)
				recBalanceCheck(r.getLeft(), isBalanced);
			else
				return isBalanced;
			if(isBalanced)
				recBalanceCheck(r.getRight(), isBalanced);
			else
				return isBalanced;
		}
		
		return isBalanced;
	}

}
