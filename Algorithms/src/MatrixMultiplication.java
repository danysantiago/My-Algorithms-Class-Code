

public class MatrixMultiplication {
	
	static int cutOffCriteria = 64;
	static int on;

	public static void main(String[] args){
		long sTime;
		
		for(int i = 1; i <= 12; i++){
		int n = (int) Math.pow(2, i);
		on = n;
		
		Matrix A = new Matrix(n);
		A.fillRandomly();
		
		Matrix B = new Matrix(n);
		B.fillRandomly();
		
		System.out.print(n + ", ");
		
		sTime = System.nanoTime();
		Matrix C1 = A.multiply(B);
		System.out.print(System.nanoTime() - sTime);
		
		System.out.print(", ");
		
		sTime = System.nanoTime();
		Matrix C2 = strassenMultiplication(A, B);
		System.out.print(System.nanoTime() - sTime);
		
		System.out.println();
		}
			
	}
	
	public static Matrix strassenMultiplication(Matrix A, Matrix B){
		int n = A.size();
		int n2 = A.getRealN();
		
		if(n <= cutOffCriteria){
			return A.multiply(B);
		} else {
			Matrix A00 = A.getSubMatrix(0, 0, n2/2);
			Matrix A01 = A.getSubMatrix(0, n2/2, n2/2);
			Matrix A10 = A.getSubMatrix(n2/2, 0, n2/2);
			Matrix A11 = A.getSubMatrix(n2/2, n2/2, n2/2);
			
			Matrix B00 = B.getSubMatrix(0, 0, n2/2);
			Matrix B01 = B.getSubMatrix(0, n2/2, n2/2);
			Matrix B10 = B.getSubMatrix(n2/2, 0, n2/2);
			Matrix B11 = B.getSubMatrix(n2/2, n2/2, n2/2);
			
			Matrix m1 = strassenMultiplication(A00.sum(A11), B00.sum(B11));
			Matrix m2 = strassenMultiplication(A10.sum(A11), B00);
			Matrix m3 = strassenMultiplication(A00, B01.substract(B11));
			Matrix m4 = strassenMultiplication(A11, B10.substract(B00));
			Matrix m5 = strassenMultiplication(A00.sum(A01), B11);
			Matrix m6 = strassenMultiplication(A10.substract(A00), B00.sum(B01));
			Matrix m7 = strassenMultiplication(A01.substract(A11), B10.sum(B11));
			
			Matrix C00 = m1.sum(m4).substract(m5).sum(m7);
			Matrix C01 = m3.sum(m5);
			Matrix C10 = m2.sum(m4);
			Matrix C11 = m1.sum(m3).substract(m2).sum(m6);
			
			Matrix C = new Matrix(n);
			C.fillFromSubMatrix(C00, 0, 0);
			C.fillFromSubMatrix(C01, 0, n2/2);
			C.fillFromSubMatrix(C10, n2/2, 0);
			C.fillFromSubMatrix(C11, n2/2, n2/2);
			
			return C;
		}
			
	}
}


