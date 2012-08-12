package directAVLBSTMap;

import mapInterfaces.BTPosition;
import mapInterfaces.Entry;

/**
 * A pair that contains a node and the relationship
 * with its parent
 * @author pirvos
 *
 */
public class NodeRwPPair<E> {
	private BTPosition<E> node; 
	private int rwp;  
	// the relationship with its parent, if any
	// 0 => no parent, -1 => left child,  1 => right child
	
	public NodeRwPPair() {}
	
	public NodeRwPPair(BTPosition<E> node, int rwp) { 
		this.node = node; 
		this.rwp = rwp; 
	}
	
	public BTPosition<E> getNode() { 
		return node; 
	}
	
	public boolean isLeftChild() { 
		return rwp == -1; 
	}

	public boolean isRightChild() { 
		return rwp == 1; 
	}
}
