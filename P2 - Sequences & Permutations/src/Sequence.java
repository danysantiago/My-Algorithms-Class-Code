

public class Sequence {

	//Java Main method to execute the sequence method.
	public static void main(String[] args) {
		long stime = System.nanoTime();
		int[] a = {1,1,1};
		sequence(a, 0, 3);
		System.out.println("Elapsed time: " + (System.nanoTime() - stime) + " ns");
		
	}

	/**
	 * Method to print all the numeric sequences of a n sized array
	 * @param p array
	 * @param lvl level in the "virtual tree"
	 * @param n array's size
	 */
	private static void sequence(int[] p, int lvl, int n) {
		if(lvl == p.length){
			for(int i : p)
				System.out.print(i +", ");
			System.out.println();
		} else {
			for(int j=1; j<=n; j++){
				p[lvl]=j;
				sequence(p, lvl+1, n);
			}

		}
			
	}

}
