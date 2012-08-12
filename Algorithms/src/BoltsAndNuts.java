
public class BoltsAndNuts {

	static int[] bolts = {4,3,7,5,1,2,3,9,8,0};
	static int[] nuts = {3,6,1,4,8,5,7,2,0,9};
	static int n = bolts.length;
	
	public static void main(String[] args) {
		
		sortAndMatch(0, n);
		
		for(int n : bolts)
			System.out.print(n + " ");
		System.out.println();
		for(int n : bolts)
			System.out.print(n + " ");

	}

	private static void sortAndMatch(int l, int h) {
		if(l < h)
			return;
		int i = -1;
		int j = n;
		while(i < j){
			for(i = i+1; bolts[l] >= nuts[i]; i++){}
			for(j = j-1; bolts[l] <= nuts[j]; j--){}
			swap(nuts, i, j);
		}

		int match = i;

		i = -1;
		j = n;
		while(i < j){
			for(i = i+1; nuts[match] >= bolts[i]; i++){}
			for(j = j-1; nuts[match] <= bolts[i]; j--){}
			swap(bolts, i, j);
		}

		sortAndMatch(0, match-1);
		sortAndMatch(match+1,n);
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}


}
