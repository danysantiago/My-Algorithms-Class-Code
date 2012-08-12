import exceptions.InvalidStackException;


public class Main {

	//Testing class
	public static void main(String[] args) {
		long stime;

//		stime = System.nanoTime();
//		SeqGenerator seqG = new SeqGenerator(3);
//		printSeq(seqG);
//		System.out.println("Elapsed time: " + (System.nanoTime() - stime) + " ns");

		System.out.println("PermGenerator 1: ");
		stime = System.nanoTime();
		PermGenerator_v1 permG1 = new PermGenerator_v1(3);
		printSeq(permG1);
		System.out.println("Elapsed time: " + (System.nanoTime() - stime) + " ns");
		
		System.out.println("\nPermGenerator 2: ");
		stime = System.nanoTime();
		PermGenerator_v2 permG2 = new PermGenerator_v2(3);
		printSeq(permG2);
		System.out.println("Elapsed time: " + (System.nanoTime() - stime) + " ns");
		
		
		System.out.println("\nSolution to the 8x8 Queens Problem:");
		stime = System.nanoTime();
		EightQueens EQ = new EightQueens(8);
		printSeq(EQ);
		System.out.println("Elapsed time: " + (System.nanoTime() - stime) + " ns");


	}
	
	public static void printSeq(P_ADT permGen){
		int[] s;
		while(permGen.hasMore()){
			try {
				s = permGen.next();
				for(int i : s)
					System.out.print(i +" ");
				System.out.println();
			} catch (InvalidStackException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void printArr(int[] arr){
		for(int i : arr)
			System.out.print(i +", ");
		System.out.println();
	}

}
