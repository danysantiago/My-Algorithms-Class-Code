package mapInterfaces;

public interface BTPosition<E> extends Position<E> {
	void setElement(E nuevo); 
	BTPosition<E> getLeft(); 
	void setLeft(BTPosition<E> v); 
	BTPosition<E> getRight(); 
	void setRight(BTPosition<E> v); 
	int getHeight(); 
	void resetHeight();
	void copyFrom(BTPosition<E> other);
	void clean();
	int leftChildHeight();
	int rightChildHeight();
}
