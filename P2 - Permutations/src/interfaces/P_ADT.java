package interfaces;
import exceptions.InvalidStackException;


public interface P_ADT {
	boolean hasMore();
	int[] next() throws InvalidStackException;
	void reset();
}
