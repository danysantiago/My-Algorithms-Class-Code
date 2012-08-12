
import exceptions.InvalidStackException;

//ADT specified in the project's instructions
public interface P_ADT {
	boolean hasMore();
	int[] next() throws InvalidStackException;
	void reset();
}
