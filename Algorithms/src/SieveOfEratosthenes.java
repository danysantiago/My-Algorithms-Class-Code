
public class SieveOfEratosthenes {

	/**
	 * Sieve of Eratosthenes Algorithm to get the list of primer numbers that are before n
	 */
	public static void main(String[] args) {
		int n = 100; //Input n
		int[] A = new int[n]; //List, all number != 0 will be primer numbers 
		
		for(int p = 2; p < n; p++)
			A[p] = p;
		for(int p = 2; p <= Math.sqrt(n); p++){
			if(A[p] != 0){
				int j = p*p;
				while(j < n){
					A[j] = 0;
					j=j+p;
				}
			}
		}
		for(int a : A)
			if(a != 0)
				System.out.print(a +" ");

	}

}
