import interfaces.P_ADT;
import exceptions.InvalidStackException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		long stime = System.nanoTime();
//		int[] a = {1,2,3,4,5};
//		PermGenerator_v2 permG2 = new PermGenerator_v2(a, 5);
//		printPerm(permG2);
//		System.out.println(System.nanoTime() - stime);
		
		
		int[] a = {1,2,3,4,5};
		PermGenerator_v1 permG1 = new PermGenerator_v1(a, 5);
		PermGenerator_v2 permG2 = new PermGenerator_v2(a, 5);
		
		long stime;
		long etime;
		
		stime = System.nanoTime();
		printPerm(permG1);
		etime = System.nanoTime();
		System.out.println("Elapsed time: "+(etime-stime));
		
		stime = System.nanoTime();
		printPerm(permG2);
		etime = System.nanoTime();
		System.out.println("Elapsed time: "+(etime-stime));
		
//		int[] b = {1,2,3,4,5,6,7,8};
//		EightQueens eq = new EightQueens(b, b.length);
//		while(eq.hasMore()){
//			int[] s;
//			try {
//				s = eq.next();
//				for(int i = 0; i < s.length; i++){
//					System.out.print((i+1)+"-"+s[i]+",");
//				}
//				System.out.println();
//			} catch (InvalidStackException e) {
//				e.printStackTrace();
//			}
//			
//		}

	}
	
	public static void printPerm(P_ADT permGen){
		int[] s;
		while(permGen.hasMore()){
			try {
				s = permGen.next();
//				for(int i : s)
//					System.out.print(i +", ");
//				System.out.println();
			} catch (InvalidStackException e) {
				e.printStackTrace();
			}
		}
	}

}
