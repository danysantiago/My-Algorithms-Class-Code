
public class BTGeneration {

	static int[] inOrder = {9,3,1,0,4,2,7,6,8,5};
	static int[] postOrder = {9,1,4,0,3,6,7,5,8,2};
	static int n = inOrder.length;
	
	public static void main(String[] args){
		Node r = construct(0, n-1, 0, n-1);
		
		inOrder(r);
		System.out.println();
		postOrder(r);
		System.out.println();
		preOrder(r);
		
	}
	
	private static Node construct(int iLow, int iHigh, int pLow, int pHigh){
		if((pHigh - pLow) < 0)
			return null;
		if((pHigh - pLow) == 0)
			return new Node(postOrder[pHigh]);
		Node r = new Node(postOrder[pHigh]);
		int iIndex = 0;
		for(int i = iHigh; i >= iLow; i--){
			if(inOrder[i] == postOrder[pHigh]){
				iIndex = i;
				break;
			}
		}
		int rightDif = iHigh - iIndex;
		r.addRight(construct(iIndex+1, iHigh, pHigh-rightDif, pHigh-1));
		r.addLeft(construct(iLow, iIndex-1, pLow, pHigh-rightDif-1));
		return r;
	}
	
	private static void postOrder(Node r) {
		if(r != null){
			postOrder(r.getLeftChild());
			postOrder(r.getRightChild());
			System.out.print(r.getElement() + " ");
		}
	}
	
	private static void preOrder(Node r) {
		if(r != null){
			System.out.print(r.getElement() + " ");
			preOrder(r.getLeftChild());
			preOrder(r.getRightChild());
		}
	}

	private static void inOrder(Node r) {
		if(r != null){
			inOrder(r.getLeftChild());
			System.out.print(r.getElement() + " ");
			inOrder(r.getRightChild());
		}
	}

	private static class Node{
		private int e;
		private Node lc;
		private Node rc;
		
		public Node(int e){
			this.e = e;
		}
		
		public int getElement() {
			return e;
		}

		public Node addRight(Node r){
			rc = r;
			return this;
		}
		
		public Node addLeft(Node l){
			lc = l;
			return this;
		}
		
		public Node getLeftChild(){
			return lc;
		}
		
		public Node getRightChild(){
			return rc;
		}

		@Override
		public String toString() {
			return "Node [e=" + e + "]";
		}
		
		
	}

}


