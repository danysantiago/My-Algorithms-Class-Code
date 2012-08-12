package interfaces;
import tree.TreeNode;
import exceptions.BoundaryViolationException;
import exceptions.EmptyTreeException;

//My Tree ADT, erased a few method, only declared those that will be used in the Seq and Perm Generators
public interface Tree<T>{
	int size(); 
	boolean isEmpty(); 
	TreeNode<T> root() throws EmptyTreeException; 
	TreeNode<T> parent(TreeNode<T> v) throws BoundaryViolationException; 
	Iterable<TreeNode<T>> children(TreeNode<T> v);
	boolean isInternal(TreeNode<T> v); 
	boolean isExternal(TreeNode<T> v); 
	boolean isRoot(TreeNode<T> v); 
}
