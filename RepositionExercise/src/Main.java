
public class Main {
	
	static long sTime;

	static int n = 5; //Amount of persons
	static int m = 3; //Amount of tasks
	
	//int[][] w = new int[m][n]; //Associated Cost Matrix
	static int[][] w = {{1,4,3},
				 		{1,3,1},
				 		{2,3,1},
				 		{1,2,1},
				 		{3,1,1}};
	
	static //int[] t = new int[m]; //Workers per Task Need Array
	int[] t = {1,3,1};
	
	static int[] A = new int[n+1]; //Assignment array
	static int[] minA = new int[n+1]; //Minimum cost assignment array
	
	public static void main(String[] args) {
		
		minA[0] = Integer.MAX_VALUE;
		
		//Part 1
		// A[0] = sum(w[i][A[i+1]-1],i,n-1)
		
		sTime = System.nanoTime();
		sequence(A, 1, m); //Part 2
		for(int a : minA)
			System.out.print(a + " ");
		System.out.println("\nElapsed time: " + (System.nanoTime() - sTime));
		
		sTime = System.nanoTime();
		efficient_sequence(A, 1, m); //Part 3
		for(int a : minA)
			System.out.print(a + " ");
		System.out.println("\nElapsed time: " + (System.nanoTime() - sTime));

	}
	
	public static void sequence(int[] p, int lvl, int m ){
		if(lvl == p.length){
			for(int i = 0; i < n; i++)
				p[0] += w[i][p[i+1]-1];
			if(minA[0] > p[0])
				minA = p.clone();
			p[0] = 0;
		} else {
			for(int j=1; j <= m; j++){
				p[lvl] = j;
				sequence(p, lvl+1, m);
			}
		}
	}
	
	public static void efficient_sequence(int[] p, int lvl, int m ){
		if(lvl == p.length){
			minA = p.clone();
			p[0] = 0;
		} else {
			for(int j=1; j <= m; j++){
				if(verify(p, lvl))
					continue;
				p[lvl] = j;
				t[lvl-1]--;
				sequence(p, lvl+1, m);
				t[lvl-1]++;
			}
		}
	}

	private static boolean verify(int[] p, int lvl) {
		if(t[lvl-1] == 0 || p[0] >= minA[0])
			return true;
		return false;
	}

}
