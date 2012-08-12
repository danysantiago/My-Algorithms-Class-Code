

public class RodCuttingProblem {
	
	private static int[] p = {0,1,5,8,9,10,17,17,20};
	
	public static void main(String[] args){
		int n = 8;	
		System.out.println(cutRod(n));
	}
	
	private static int cutRod(int n){
		int[] r = new int[n+1];
		r[0] = 0;
		for(int i = 1; i <= n; i++){
			int max = 0;
			for(int k = 1; k <= i; k++){
				max = Math.max(max, r[i-k] + p[k]);
			}
			r[i] = max;	
		}
		return r[n];	
	}
}
